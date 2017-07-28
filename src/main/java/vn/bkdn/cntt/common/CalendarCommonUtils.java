package vn.bkdn.cntt.common;

import org.springframework.beans.factory.annotation.Autowired;
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
    private DMNhanVienService nhanVienService;

    @Autowired
    private TKB_LichHocTheoNgayService tkb_lichHocTheoNgayService;

    @Autowired
    private TKB_TietService tkb_tietService;

    @Autowired
    private DMLopMonHocService lopMonHocService;

    public List<DMLopMonHoc> getClassCalendarByWeek(List<DMLopMonHoc> dmLopMonHocs, String date) throws ParseException {
        java.util.Calendar c = java.util.Calendar.getInstance(Locale.GERMAN);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date chosenDate = dateFormat.parse(date);
        c.setTime(chosenDate);
        int week = c.get(Calendar.WEEK_OF_YEAR);
        int year = chosenDate.getYear();

        Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgays;
        Set<TKB_LichHocTheoNgay> tkb_lichHocTheoNgaysByWeek;

        for (DMLopMonHoc DMLopMonHoc :
                dmLopMonHocs) {
            tkb_lichHocTheoNgays = DMLopMonHoc.getTkb_lichHocTheoNgays();
            tkb_lichHocTheoNgaysByWeek = new HashSet<>();
            for (TKB_LichHocTheoNgay tkb_lichHocTheoNgay :
                    tkb_lichHocTheoNgays) {

                String learningDateString = tkb_lichHocTheoNgay.getNgay().toString();
                Date learningDate = dateFormat.parse(learningDateString);
                c.setTime(learningDate);

                if(year == (tkb_lichHocTheoNgay.getNgay().getYear()-1) && chosenDate.before(learningDate)){
                    if(week == c.get(java.util.Calendar.WEEK_OF_YEAR)){
                        tkb_lichHocTheoNgaysByWeek.add(tkb_lichHocTheoNgay);
                    }
                }

                if(year == (tkb_lichHocTheoNgay.getNgay().getYear()+1) && chosenDate.after(learningDate)){
                    if(week == c.get(java.util.Calendar.WEEK_OF_YEAR)){
                        tkb_lichHocTheoNgaysByWeek.add(tkb_lichHocTheoNgay);
                    }
                }

                if (year == tkb_lichHocTheoNgay.getNgay().getYear()) {
                    if(week == c.get(java.util.Calendar.WEEK_OF_YEAR)){
                            tkb_lichHocTheoNgaysByWeek.add(tkb_lichHocTheoNgay);
                    }
                }
            }
            DMLopMonHoc.setTkb_lichHocTheoNgays(tkb_lichHocTheoNgaysByWeek);
        }

        return dmLopMonHocs;
    }


}
