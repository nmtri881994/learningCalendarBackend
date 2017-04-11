package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TKB_LichHocTheoTuan;
import vn.bkdn.cntt.repository.TKB_LichHocTheoTuanRepository;

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
                tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getId(), tkb_lichHocTheoTuan.getId());
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
}
