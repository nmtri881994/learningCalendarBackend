package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.LopMonHoc;
import vn.bkdn.cntt.entity.TKB_LichHocTheoTuan;
import vn.bkdn.cntt.repository.TKB_LichHocTheoTuanRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XuanVinh on 4/10/2017.
 */

@Service
public class TKB_LichHocTheoTuanServiceImpl implements TKB_LichHocTheoTuanService {

    @Autowired
    private TKB_LichHocTheoTuanRepository tkb_lichHocTheoTuanRepository;

    @Override
    public List<TKB_LichHocTheoTuan> findLichHocTheoTuanByThuIdAndGiangDuongId(int thuId, int giangDuongId) {
        return tkb_lichHocTheoTuanRepository.findLichHocTheoTuanByThuIdAndGiangDuongId(thuId, giangDuongId);
    }

    @Override
    public TKB_LichHocTheoTuan findOne(int tkbTuanId) {
        return tkb_lichHocTheoTuanRepository.findOne(tkbTuanId);
    }

    @Override
    public void updateWeekCalendar(TKB_LichHocTheoTuan tkb_lichHocTheoTuan) {
        tkb_lichHocTheoTuanRepository.updateWeekCalendar(tkb_lichHocTheoTuan.getGiangDuong().getId(),
                tkb_lichHocTheoTuan.getTkb_thu().getId(), tkb_lichHocTheoTuan.getTkb_tietDauTien().getId(),
                tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getId(), tkb_lichHocTheoTuan.getTuanBatDau(),
                tkb_lichHocTheoTuan.getTuanKetThuc(), tkb_lichHocTheoTuan.getId());
    }

    @Override
    public void addWeekCalendar(TKB_LichHocTheoTuan tkb_lichHocTheoTuan) {
        tkb_lichHocTheoTuanRepository.save(tkb_lichHocTheoTuan);
    }

    @Override
    public void deleteCalendar(int calendarId) {
        tkb_lichHocTheoTuanRepository.delete(calendarId);
    }

    @Override
    public int getLopMonHocIdByTKB_LichHocTheoTuanId(int tkb_lichHocTheoTuanId) {
        return tkb_lichHocTheoTuanRepository.getLopMonHocIdByTKB_LichHocTheoTuanId(tkb_lichHocTheoTuanId);
    }

    @Override
    public boolean canAddOrEditWeekCalendar(TKB_LichHocTheoTuan tkb_lichHocTheoTuan, List<LopMonHoc> lopMonHocs) {
        List<TKB_LichHocTheoTuan> tkb_lichHocTheoTuans = new ArrayList<>();
        for (LopMonHoc lopMonHoc:
             lopMonHocs) {
            tkb_lichHocTheoTuans.addAll(lopMonHoc.getTkb_lichHocTheoTuans());
        }
        tkb_lichHocTheoTuans.removeIf(tkb_lichHocTheoTuan1 -> tkb_lichHocTheoTuan1.getTkb_thu().getId()!=tkb_lichHocTheoTuan.getTkb_thu().getId());

        tkb_lichHocTheoTuans.remove(tkb_lichHocTheoTuan);
        int weekCalendarSameTime = 0;
        for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan1:
             tkb_lichHocTheoTuans) {
            boolean condition1 = tkb_lichHocTheoTuan1.getTkb_tietCuoiCung().getThuTu() < tkb_lichHocTheoTuan.getTkb_tietDauTien().getThuTu();
            boolean condition2 = tkb_lichHocTheoTuan1.getTkb_tietDauTien().getThuTu() > tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getThuTu();
            if(!(condition1 || condition2)){
                weekCalendarSameTime ++;
            }
        }

        if(weekCalendarSameTime<3){
            return true;
        }else{
            return false;
        }
    }
}
