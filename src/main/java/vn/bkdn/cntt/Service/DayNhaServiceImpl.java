package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.DayNha;
import vn.bkdn.cntt.repository.DayNhaRepository;

import java.util.List;

/**
 * Created by XuanVinh on 4/9/2017.
 */

@Service
public class DayNhaServiceImpl implements DayNhaService {

    @Autowired
    private DayNhaRepository dayNhaRepository;

    @Override
    public List<DayNha> findAll() {
        return dayNhaRepository.findAll();
    }
}
