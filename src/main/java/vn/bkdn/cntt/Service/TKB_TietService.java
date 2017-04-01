package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.TKB_Tiet;

import java.util.List;

/**
 * Created by XuanVinh on 3/28/2017.
 */
public interface TKB_TietService {
    List<TKB_Tiet> findAll();
    List<TKB_Tiet> findByIdGreaterThanAndIdLessThan(int startLessonId, int endLessonId);
}
