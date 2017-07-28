package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.DMDonVi;
import vn.bkdn.cntt.repository.DMKhoaRepository;

import java.util.List;

/**
 * Created by Tri on 4/5/2017.
 */

@Service
public class DMKhoaServiceImpl implements DMKhoaService {

    @Autowired
    private DMKhoaRepository khoaRepository;

    @Override
    public List<DMDonVi> findAll() {
        return khoaRepository.findAll();
    }

    @Override
    public DMDonVi findOne(int khoaId) {
        return khoaRepository.findOne(khoaId);
    }
}
