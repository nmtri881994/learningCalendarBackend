package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.TK_VaiTro;

/**
 * Created by XuanVinh on 3/18/2017.
 */

@Repository
public interface VaiTroRepository extends JpaRepository<TK_VaiTro, Integer> {
    TK_VaiTro findByTenVaiTro(String tenVaiTro);
}
