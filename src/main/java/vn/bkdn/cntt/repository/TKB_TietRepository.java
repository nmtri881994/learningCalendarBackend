package vn.bkdn.cntt.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.Service.TKB_TietService;
import vn.bkdn.cntt.entity.TKB_Tiet;

/**
 * Created by XuanVinh on 3/28/2017.
 */

@Repository
public interface TKB_TietRepository extends JpaRepository<TKB_Tiet, Integer> {

}
