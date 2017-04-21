package vn.bkdn.cntt.Service;

import vn.bkdn.cntt.entity.RegisterTime;

/**
 * Created by XuanVinh on 4/20/2017.
 */
public interface RegisterTimeService {
    void udpateRegistering(int registerTimeId, boolean status);
    RegisterTime findOne(int registerTimeId);
}
