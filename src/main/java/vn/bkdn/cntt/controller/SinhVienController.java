package vn.bkdn.cntt.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.bkdn.cntt.Service.SinhVienService;
import vn.bkdn.cntt.Service.TaiKhoanHeThongService;
import vn.bkdn.cntt.common.CalendarCommonUtils;
import vn.bkdn.cntt.entity.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Tri on 3/15/2017.
 */

@RestController
@RequestMapping(value = "/api/sinhvien")
public class SinhVienController {

    @Autowired
    private SinhVienService sinhVienService;

    @Autowired
    private TaiKhoanHeThongService taiKhoanHeThongService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/calendar/week/{date}")
    public ResponseEntity<MappingJacksonValue> getCalendarByWeek(@PathVariable String date) throws ParseException {
        //Get year and WeekOFYear
        Calendar c = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = dateFormat.parse(date);
        c.setTime(utilDate);
        int currentYear = utilDate.getYear();
        int currentWeek = c.get(Calendar.WEEK_OF_YEAR);

        //Get all student classes' calendar
        String tenDangNhap = SecurityContextHolder.getContext().getAuthentication().getName();
        SinhVien sinhVien = sinhVienService.findByMaSinhVien(tenDangNhap);
        Set<LopMonHoc_SinhVien> lopMonHoc_sinhViens = sinhVien.getLopMonHoc_sinhViens();
        List<LopMonHoc> lopMonHocs = new ArrayList<>();
        for (LopMonHoc_SinhVien lopMonHoc_sinhVien:
             lopMonHoc_sinhViens) {
            lopMonHocs.add(lopMonHoc_sinhVien.getLopMonHoc());
        }

        //Filter student classes' calendar by input date
        CalendarCommonUtils calendarCommonUtils = new CalendarCommonUtils();
        lopMonHocs = calendarCommonUtils.getClassCalendarByWeek(lopMonHocs, currentYear, currentWeek);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(lopMonHocs);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.LopMonHoc", SimpleBeanPropertyFilter
                        .filterOutAllExcept("id", "giaoVien", "tkb_lichHocTheoNgays", "monHoc"));

        mappingJacksonValue.setFilters(filterProvider);

        return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue, HttpStatus.OK);
    }
}
