package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.DMLoaiPhong;
import vn.bkdn.cntt.repository.DayNhaRepository;

import java.util.List;

/**
 * Created by XuanVinh on 4/9/2017.
 */

@Service
public class DMDayNhaServiceImpl implements DMDayNhaService {

    @Autowired
    private DayNhaRepository dayNhaRepository;

    @Override
    public List<DMLoaiPhong> findAll() {
        return dayNhaRepository.findAll();
    }

    @Override
    public DMLoaiPhong findOne(int id) {
        return dayNhaRepository.findOne(id);
    }
}
