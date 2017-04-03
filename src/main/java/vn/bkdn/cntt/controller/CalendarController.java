package vn.bkdn.cntt.controller;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Tri on 3/24/2017.
 */

@RestController
@RequestMapping(value = "/api/calendar")
public class CalendarController {

    @Autowired
    private NamHocService namHocService;

    @Autowired
    private GiaoVienService giaoVienService;

    @Autowired
    private TKB_LichHocTheoNgayService tkb_lichHocTheoNgayService;

    @Autowired
    private TKB_TietService tkb_tietService;

    @Autowired
    private LopMonHocService lopMonHocService;

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

    @GetMapping(value = "/available-lessons/{roomId}/{date}")
    public ResponseEntity<List<TKB_Tiet>> getAvailableLessonForRoomAtDate(@PathVariable int roomId, @PathVariable String date) {
        List<TKB_Tiet> tkb_availableLessons = tkb_tietService.findAll();

        List<TKB_LichHocTheoNgay> tkb_lichHocTheoNgayCuaPhongs = tkb_lichHocTheoNgayService.getLichHocOfRoomByDate(roomId, date);
        for (TKB_LichHocTheoNgay tkb_lichHocTheoNgay:
                tkb_lichHocTheoNgayCuaPhongs) {
            List<TKB_Tiet> tkb_tietNotFrees = tkb_tietService.findByIdGreaterThanAndIdLessThan(tkb_lichHocTheoNgay.getTkb_tietDauTien().getId()-1, tkb_lichHocTheoNgay.getTkb_tietCuoiCung().getId()+1);
            tkb_availableLessons.removeAll(tkb_tietNotFrees);
        }

        return new ResponseEntity<List<TKB_Tiet>>(tkb_availableLessons, HttpStatus.OK);
    }


}
