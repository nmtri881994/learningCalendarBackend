package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.DMLoaiPhong;
import vn.bkdn.cntt.repository.DMLoaiPhongRepository;

import java.util.List;

/**
 * Created by Tri on 7/27/2017.
 */

@Service
public class DMLoaiPhongServiceImpl implements DMLoaiPhongService {

    @Autowired
    private DMLoaiPhongRepository dmLoaiPhongRepository;

    @Override
    public List<DMLoaiPhong> findAll() {
        return dmLoaiPhongRepository.findAll();
    }
}
