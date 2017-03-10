package vn.bkdn.cntt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.Lop;
import vn.bkdn.cntt.repository.LopRepository;

import java.util.List;

/**
 * Created by XuanVinh on 3/7/2017.
 */
@Service
public class LopServiceImpl implements LopService {

    @Autowired
    private LopRepository lopRepository;

    @Override
    public List<Lop> findAll() {
        return lopRepository.findAll();
    }
}
