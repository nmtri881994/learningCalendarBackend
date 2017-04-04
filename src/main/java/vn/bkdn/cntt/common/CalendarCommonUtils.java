package vn.bkdn.cntt.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import vn.bkdn.cntt.Service.*;
import vn.bkdn.cntt.entity.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by XuanVinh on 3/20/2017.
 */
public class CalendarCommonUtils {

    @Autowired
    private GiaoVienService giaoVienService;

    @Autowired
    private TKB_LichHocTheoNgayService tkb_lichHocTheoNgayService;

    @Autowired
    private TKB_TietService tkb_tietService;

    @Autowired
    private LopMonHocService lopMonHocService;

    public List<LopMonHoc> getClassCalendarByWeek(List<LopMonHoc> lopMonHocs, int year, int week) throws ParseException {
        java.util.Calendar c = java.util.Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgays;
        Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgaysByWeek;

        for (LopMonHoc lopMonHoc:
                lopMonHocs) {
            tkb_lichHocTheoNgays = lopMonHoc.getTkb_lichHocTheoNgays();
            tkb_lichHocTheoNgaysByWeek = new HashSet<>();
            for (TKB_LichHocTheoNgay tkb_lichHocTheoNgay:
                    tkb_lichHocTheoNgays) {
                if(year == tkb_lichHocTheoNgay.getNgay().getYear()){
                    String learningDateString = tkb_lichHocTheoNgay.getNgay().toString();
                    Date learningDate = dateFormat.parse(learningDateString);
                    c.setTime(learningDate);
                    if(week == c.get(java.util.Calendar.WEEK_OF_YEAR)){
                        tkb_lichHocTheoNgaysByWeek.add(tkb_lichHocTheoNgay);
                    }
                }
            }
            lopMonHoc.setTkb_lichHocTheoNgays(tkb_lichHocTheoNgaysByWeek);
        }

        return lopMonHocs;
    }


}
