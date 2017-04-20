package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.repository.RegisterTimeRepository;

/**
 * Created by XuanVinh on 4/20/2017.
 */

@Service
public class RegisterTimeServiceImpl implements RegisterTimeService {

    @Autowired
    private RegisterTimeRepository registerTimeRepository;


    @Override
    public void udpateRegistering(int registerTimeId, boolean status) {
        registerTimeRepository.updateRegistering(registerTimeId, status);
    }
}
