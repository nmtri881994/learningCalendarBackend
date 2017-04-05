package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.Khoa;
import vn.bkdn.cntt.repository.KhoaRepository;

import java.util.List;

/**
 * Created by Tri on 4/5/2017.
 */

@Service
public class KhoaServiceImpl implements KhoaService {

    @Autowired
    private KhoaRepository khoaRepository;

    @Override
    public List<Khoa> findAll() {
        return khoaRepository.findAll();
    }

    @Override
    public Khoa findOne(int khoaId) {
        return khoaRepository.findOne(khoaId);
    }
}
