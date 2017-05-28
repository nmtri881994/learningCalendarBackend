package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.LopMonHoc;
import vn.bkdn.cntt.entity.TKB_LichHocTheoNgay;
import vn.bkdn.cntt.entity.TKB_Thu;
import vn.bkdn.cntt.repository.TKB_LichHocTheoNgayRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by XuanVinh on 3/27/2017.
 */

@Service
public class TKB_LichHocTheoNgayServiceImpl implements TKB_LichHocTheoNgayService {

    @Autowired
    private TKB_LichHocTheoNgayRepository tkb_lichHocTheoNgayRepository;

    @Autowired
    public TKB_ThuService tkb_thuService;

    @Override
    public TKB_LichHocTheoNgay findOne(int id) {
        return tkb_lichHocTheoNgayRepository.findOne(id);
    }

    @Override
    public boolean updateBuoiHoc(TKB_LichHocTheoNgay tkb_lichHocTheoNgay) {
        try {
            int thuId = 0;
            Calendar c = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date utilDate = dateFormat.parse(tkb_lichHocTheoNgay.getNgay().toString());
            c.setTime(utilDate);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            this.tkb_lichHocTheoNgayRepository.updateBuoiHoc(tkb_lichHocTheoNgay.getNgay(), getThuId(dayOfWeek), tkb_lichHocTheoNgay.getGiangDuong().getId(),
                    tkb_lichHocTheoNgay.getTkb_tietDauTien().getId(), tkb_lichHocTheoNgay.getTkb_tietCuoiCung().getId(),
                    tkb_lichHocTheoNgay.isThiGiuaKy(), tkb_lichHocTheoNgay.isThiCuoiKy(), tkb_lichHocTheoNgay.getGiaoVienNhan(), tkb_lichHocTheoNgay.getGiaoVienGhiChu(),
                    tkb_lichHocTheoNgay.getId());
//            this.tkb_lichHocTheoNgayRepository.updateBuoiHoc2();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<TKB_LichHocTheoNgay> getLichHocOfRoomByDate(int giangDuongId, String ngay) {
        return tkb_lichHocTheoNgayRepository.getLichHocOfRoomByDate(giangDuongId, ngay);
    }

    @Override
    public List<TKB_LichHocTheoNgay> findByLopMonHocAndNgay(LopMonHoc lopMonHoc, java.sql.Date ngay) {
        return tkb_lichHocTheoNgayRepository.findByLopMonHocAndNgay(lopMonHoc, ngay);
    }

    @Override
    public int getClassId(int lessonId) {
        return tkb_lichHocTheoNgayRepository.getClassId(lessonId);
    }

    public int getThuId(int dayOfWeek) {
        TKB_Thu tkb_thu;
        switch (dayOfWeek) {
            case 1:
                tkb_thu = tkb_thuService.findByTen("Chủ nhật");
                break;
            case 2:
                tkb_thu = tkb_thuService.findByTen("Thứ 2");
                break;
            case 3:
                tkb_thu = tkb_thuService.findByTen("Thứ 3");
                break;
            case 4:
                tkb_thu = tkb_thuService.findByTen("Thứ 4");
                break;
            case 5:
                tkb_thu = tkb_thuService.findByTen("Thứ 5");
                break;
            case 6:
                tkb_thu = tkb_thuService.findByTen("Thứ 6");
                break;
            case 7:
                tkb_thu = tkb_thuService.findByTen("Thứ 7");
                break;
            default:
                tkb_thu = null;
                break;
        }
        if (tkb_thu != null) {
            return tkb_thu.getId();
        } else {
            return 0;
        }
    }
}
