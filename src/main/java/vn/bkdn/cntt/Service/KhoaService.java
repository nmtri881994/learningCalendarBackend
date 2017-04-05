package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.Khoa;

import java.util.List;

/**
 * Created by Tri on 4/5/2017.
 */
public interface KhoaService {
    List<Khoa> findAll();
    Khoa findOne(int khoaId);
}
