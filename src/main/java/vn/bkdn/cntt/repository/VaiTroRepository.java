package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.VaiTro;

/**
 * Created by XuanVinh on 3/18/2017.
 */

@Repository
public interface VaiTroRepository extends JpaRepository<VaiTro, Integer> {
    VaiTro findByTenVaiTro(String tenVaiTro);
}
