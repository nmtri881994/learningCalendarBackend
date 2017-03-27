package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TKB_Tiet;
import vn.bkdn.cntt.repository.TKB_TietRepository;

import java.util.List;

/**
 * Created by XuanVinh on 3/28/2017.
 */

@Service
public class TKB_TietServiceImpl implements  TKB_TietService{

    @Autowired
    private TKB_TietRepository tkb_tietRepository;

    @Override
    public List<TKB_Tiet> findAll() {
        return tkb_tietRepository.findAll();
    }
}
