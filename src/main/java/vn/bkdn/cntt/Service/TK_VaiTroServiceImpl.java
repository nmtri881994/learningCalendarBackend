package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.TK_VaiTro;
import vn.bkdn.cntt.repository.TK_VaiTroRepository;

/**
 * Created by XuanVinh on 3/18/2017.
 */

@Service
public class TK_VaiTroServiceImpl implements TK_VaiTroService {

    @Autowired
    private TK_VaiTroRepository vaiTroRepository;

    @Override
    public TK_VaiTro getVaiTroByName(String tenVaiTro) {
        return vaiTroRepository.findByTenVaiTro(tenVaiTro);
    }
}
