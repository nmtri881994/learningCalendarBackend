package vn.bkdn.cntt.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.bkdn.cntt.Service.*;
import vn.bkdn.cntt.entity.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Tri on 3/24/2017.
 */

@RestController
@RequestMapping(value = "/api/calendar")
public class CalendarController {

    @Autowired
    private NamHocService namHocService;

    @Autowired
    private KhoaService khoaService;

    @Autowired
    private KiHoc_NamHocService kiHoc_namHocService;

    @Autowired
    private Khoa_KhoaHocService khoa_khoaHocService;

    @Autowired
    private LopMonHocService lopMonHocService;

    @Autowired
    private TKB_ThuService tkb_thuService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/learning-year/{date}")
    public ResponseEntity<NamHoc> getNamHocByDate(@PathVariable String date) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = dateFormat.parse(date);

        //GetLearningYear of input date
        List<NamHoc> namHocs = namHocService.findAll();
        NamHoc namHocOfDate = new NamHoc();
        for (NamHoc namHoc :
                namHocs) {
            DateTime inputDateTime = new DateTime(utilDate);
            DateTime startLearningYearDateTime = new DateTime(dateFormat.parse(namHoc.getNgayBatDau().toString()));
            DateTime endLearningYearDateTime = new DateTime(dateFormat.parse(namHoc.getNgayKetThuc().toString()));

            if (Days.daysBetween(startLearningYearDateTime, inputDateTime).getDays() >= 0 && Days.daysBetween(inputDateTime, endLearningYearDateTime).getDays() >= 0) {
                namHocOfDate = namHoc;
                break;
            }
        }

        return new ResponseEntity<NamHoc>(namHocOfDate, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/week-number/{date}")
    public int getNumberOfWeek(@PathVariable String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = dateFormat.parse(date);

        //GetLearningYear of input date
        List<NamHoc> namHocs = namHocService.findAll();
        int days = 0;
        for (NamHoc namHoc :
                namHocs) {
            DateTime inputDateTime = new DateTime(utilDate);
            DateTime startLearningYearDateTime = new DateTime(dateFormat.parse(namHoc.getNgayBatDau().toString()));
            DateTime endLearningYearDateTime = new DateTime(dateFormat.parse(namHoc.getNgayKetThuc().toString()));

            if (Days.daysBetween(startLearningYearDateTime, inputDateTime).getDays() >= 0 && Days.daysBetween(inputDateTime, endLearningYearDateTime).getDays() >= 0) {
                days = Days.daysBetween(startLearningYearDateTime, inputDateTime).getDays();
                break;
            }
        }

        return ((days / 7) + 1);
    }

//    @GetMapping(value = "/available-lessons/{roomId}/{date}")
//    public ResponseEntity<List<TKB_Tiet>> getAvailableLessonForRoomAtDate(@PathVariable int roomId, @PathVariable String date) {
//        List<TKB_Tiet> tkb_availableLessons = tkb_tietService.findAll();
//
//        List<TKB_LichHocTheoNgay> tkb_lichHocTheoNgayCuaPhongs = tkb_lichHocTheoNgayService.getLichHocOfRoomByDate(roomId, date);
//        for (TKB_LichHocTheoNgay tkb_lichHocTheoNgay:
//                tkb_lichHocTheoNgayCuaPhongs) {
//            List<TKB_Tiet> tkb_tietNotFrees = tkb_tietService.findByIdGreaterThanAndIdLessThan(tkb_lichHocTheoNgay.getTkb_tietDauTien().getId()-1, tkb_lichHocTheoNgay.getTkb_tietCuoiCung().getId()+1);
//            tkb_availableLessons.removeAll(tkb_tietNotFrees);
//        }
//
//        return new ResponseEntity<List<TKB_Tiet>>(tkb_availableLessons, HttpStatus.OK);
//    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/khoas")
    public ResponseEntity<List<Khoa>> getAllKhoas() {
        List<Khoa> khoas = khoaService.findAll();
        khoas.sort(Comparator.comparing(Khoa::getTen));

        return new ResponseEntity<List<Khoa>>(khoas, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{khoaId}/khoa-hoc-not-end/{namHocId}/{kiHocId}")
    public ResponseEntity<List<KhoaHoc>> getKhoaHocsNotEndOfKhoa(@PathVariable int khoaId, @PathVariable int namHocId, @PathVariable int kiHocId) {
        Khoa khoa = khoaService.findOne(khoaId);
        KiHoc_NamHoc kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(kiHocId, namHocId);

        Set<Khoa_KhoaHoc> khoa_khoaHocs = khoa.getKhoa_khoaHocs();
        khoa_khoaHocs.removeIf(khoa_khoaHoc -> khoa_khoaHoc.getKiKetThuc().getNgayBatDau().compareTo(kiHoc_namHoc.getNgayBatDau()) < 0);

        List<KhoaHoc> khoaHocs = new ArrayList<>();
        for (Khoa_KhoaHoc khoa_khoaHoc :
                khoa_khoaHocs) {
            khoaHocs.add(khoa_khoaHoc.getKhoaHoc());
        }

        khoaHocs.sort(Comparator.comparing(KhoaHoc::getNam));
        return new ResponseEntity<List<KhoaHoc>>(khoaHocs, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/nganhs/{namHocId}/{kiHocId}/{khoaId}/{khoaHocId}")
    public ResponseEntity<List<Nganh>> getNganhs(@PathVariable int namHocId, @PathVariable int kiHocId, @PathVariable int khoaId, @PathVariable int khoaHocId) {
        Khoa_KhoaHoc khoa_khoaHoc = khoa_khoaHocService.findByKhoaIdAndKhoaHocId(khoaId, khoaHocId);
        KiHoc_NamHoc kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(kiHocId, namHocId);

        List<Nganh> nganhs = new ArrayList<>();
        if (khoa_khoaHoc.getKiPhanNganh().getNgayBatDau().compareTo(kiHoc_namHoc.getNgayBatDau()) <= 0) {
            for (Khoa_KhoaHoc_Nganh khoa_khoaHoc_nganh :
                    khoa_khoaHoc.getKhoa_khoaHoc_nganhs()) {
                nganhs.add(khoa_khoaHoc_nganh.getNganh());
            }

            nganhs.sort(Comparator.comparing(Nganh::getTen));
            return new ResponseEntity<List<Nganh>>(nganhs, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Nganh>>(nganhs, HttpStatus.OK);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/lopMonHocs/{namHocId}/{kiHocId}/{khoaId}/{khoaHocId}/{nganhId}")
    public ResponseEntity<MappingJacksonValue> getLopMonHocs(@PathVariable int namHocId, @PathVariable int kiHocId, @PathVariable int khoaId, @PathVariable int khoaHocId, @PathVariable int nganhId) {
        Khoa_KhoaHoc khoa_khoaHoc = khoa_khoaHocService.findByKhoaIdAndKhoaHocId(khoaId, khoaHocId);
        KiHoc_NamHoc kiHoc_namHoc = kiHoc_namHocService.findKiHocNamHocByKyHocIdAndNamHocId(kiHocId, namHocId);
        List<LopMonHoc> lopMonHocs;
        if (nganhId != 0) {
            lopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocIdAndNganhId(kiHoc_namHoc.getId(), khoa_khoaHoc.getId(), nganhId);
        } else {
            lopMonHocs = lopMonHocService.findByKiHoc_NamHocIdAndKhoa_KhoaHocId(kiHoc_namHoc.getId(), khoa_khoaHoc.getId());
        }

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(lopMonHocs);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.LopMonHoc", SimpleBeanPropertyFilter
                        .filterOutAllExcept("id", "monHoc", "giaoVien", "soTietLyThuyet", "soTietThucHanh", "soLuongToiDa", "soBuoiLyThuyetMotTuan", "soTietLyThuyetMotTuan", "soBuoiThucHanhMotTuan", "soTietThucHanhMotTuan", "tkb_lichHocTheoTuans"));

        mappingJacksonValue.setFilters(filterProvider);

        return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/thus")
    public ResponseEntity<List<TKB_Thu>> getAllThus(){
        List<TKB_Thu> tkb_thus = tkb_thuService.findAll();
        tkb_thus.sort(Comparator.comparing(TKB_Thu::getId));

        return new ResponseEntity<List<TKB_Thu>>(tkb_thus, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/lich-hoc-theo-tuan/{lopMonHodId}")
    public ResponseEntity<List<TKB_LichHocTheoTuan>> getLichHocTheoTuanOfLopMonHoc(@PathVariable int lopMonHodId){
        LopMonHoc lopMonHoc = lopMonHocService.findOne(lopMonHodId);
        List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuansArrayList = new ArrayList<>();
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan:
             lopMonHoc.getTkb_lichHocTheoTuans()) {
            tkb_lichHocTheoTuansArrayList.add(tkb_lichHocTheoTuan);
        }
        tkb_lichHocTheoTuansArrayList.sort(Comparator.comparing(TKB_LichHocTheoTuan::getId));
        return new ResponseEntity<List<TKB_LichHocTheoTuan>>(tkb_lichHocTheoTuansArrayList, HttpStatus.OK);
    }
}
