package vn.bkdn.cntt.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Hibernate;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.bkdn.cntt.Service.*;
import vn.bkdn.cntt.entity.*;
import vn.bkdn.cntt.jsonEntity.RegisterMessage;

import java.util.*;

/**
 * Created by XuanVinh on 5/28/2017.
 */

@Component
public class ScheduleTask {

    @Autowired
    private ThoiGianDangKyService thoiGianDangKyService;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private KiHoc_NamHocService kiHoc_namHocService;

    @Autowired
    private LopMonHocService lopMonHocService;

    @Autowired
    private NamHocService namHocService;

    @Autowired
    private TKB_LichHocTheoNgayService tkb_lichHocTheoNgayService;

    ObjectMapper mapper = new ObjectMapper();

    @Scheduled(fixedRate = 60000)
    public void registerOperation() throws JsonProcessingException {
        Date today = new Date();
        List<TKB_ThoiGianDangKy> tkbThoiGianDangKies = thoiGianDangKyService.findAll();
        for (TKB_ThoiGianDangKy tkbThoiGianDangKy :
                tkbThoiGianDangKies) {
            Date startTime = tkbThoiGianDangKy.getStartTime();
            Date endTime = tkbThoiGianDangKy.getEndTime();

            if (today.after(startTime) && today.before(endTime)) {
                if (!tkbThoiGianDangKy.isStatus()) {
                    thoiGianDangKyService.udpateRegistering(tkbThoiGianDangKy.getId(), true);

                    RegisterMessage registerMessage = new RegisterMessage(true, tkbThoiGianDangKy.getId());
                    String mess = this.mapper.writeValueAsString(registerMessage);
                    this.template.convertAndSend("/socket/register", mess);
                }
            }
            if (today.after(endTime)) {
                if (tkbThoiGianDangKy.isStatus()) {
                    thoiGianDangKyService.udpateRegistering(tkbThoiGianDangKy.getId(), false);

                    RegisterMessage registerMessage = new RegisterMessage(false, tkbThoiGianDangKy.getId());
                    String mess = this.mapper.writeValueAsString(registerMessage);
                    this.template.convertAndSend("/socket/register", mess);
                }

            }
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
//    @Scheduled(fixedRate = 10000)
    public void translateWeekCalendarToDayCalendar() {
        System.out.println(111111111);
        List<TKB_KiHoc_NamHoc> tkb_kiHoc_namHocs = kiHoc_namHocService.findAll();
        Date today = new Date();
        Calendar c = Calendar.getInstance(Locale.GERMAN);

        for (TKB_KiHoc_NamHoc tkb_kiHoc_namHoc :
                tkb_kiHoc_namHocs) {
            Date startDate = tkb_kiHoc_namHoc.getNgayBatDau();
            if (today.before(startDate)) {
                int days = Days.daysBetween(new DateTime(today), new DateTime(startDate)).getDays();
                if (days >= 1 && days <= 7 && !tkb_kiHoc_namHoc.isDaSinhLich()) {
                    System.out.println(222222);
                    List<DMLopMonHoc> dmLopMonHocs = lopMonHocService.findByKiHoc_NamHocId(tkb_kiHoc_namHoc.getId());
                    TKB_NamHoc tkb_namHoc = namHocService.findOne(kiHoc_namHocService.getNamHocId(tkb_kiHoc_namHoc.getId()));
                    Date learningYearStartDate = tkb_namHoc.getNgayBatDau();
                    for (DMLopMonHoc DMLopMonHoc :
                            dmLopMonHocs) {
                        Hibernate.initialize(DMLopMonHoc.getTkb_lichHocTheoTuans());
                        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                                DMLopMonHoc.getTkb_lichHocTheoTuans()) {
                            for (int i = tkb_lichHocTheoTuan.getTuanBatDau(); i <= tkb_lichHocTheoTuan.getTuanKetThuc(); i++) {
                                int day1s = (i - 1) * 7;
                                c.setTime(learningYearStartDate);
                                c.add(Calendar.DATE, day1s);
                                if (c.get(Calendar.DAY_OF_WEEK) != 1 && c.get(Calendar.DAY_OF_WEEK) != 2) {
                                    c.add(Calendar.DATE, 2 - c.get(Calendar.DAY_OF_WEEK));
                                }

                                if (c.get(Calendar.DAY_OF_WEEK) == 1) {
                                    c.add(Calendar.DATE, 2 - c.get(1));
                                }

                                c.add(Calendar.DATE, tkb_lichHocTheoTuan.getTkb_thu().getId() - 2);
                                TKB_LichHocTheoNgay tkb_lichHocTheoNgay = new TKB_LichHocTheoNgay(new java.sql.Date(c.getTime().getTime()), tkb_lichHocTheoTuan.getTkb_thu(), tkb_lichHocTheoTuan.getDmLopMonHoc(), tkb_lichHocTheoTuan.getDmGiangDuong(), tkb_lichHocTheoTuan.getTkb_tietDauTien(), tkb_lichHocTheoTuan.getTkb_tietCuoiCung(), false, false, "", "");
                                tkb_lichHocTheoNgayService.addLichHocTheoNgay(tkb_lichHocTheoNgay);
                            }
                        }
                    }
                    kiHoc_namHocService.setDaSinhLich(tkb_kiHoc_namHoc.getId());
                }
            }
        }
    }
}
