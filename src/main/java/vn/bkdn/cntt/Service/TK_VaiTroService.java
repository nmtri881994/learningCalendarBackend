package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.TK_VaiTro;

import java.util.List;

/**
 * Created by XuanVinh on 3/18/2017.
 */
public interface TK_VaiTroService {
    TK_VaiTro getVaiTroByName(String tenVaiTro);
    TK_VaiTro findOne(int id);

    List<TK_VaiTro> findAll();
}
