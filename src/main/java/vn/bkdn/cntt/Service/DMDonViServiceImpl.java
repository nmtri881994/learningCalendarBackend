package vn.bkdn.cntt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.DMDonVi;
import vn.bkdn.cntt.repository.DMDonViRepository;

import java.util.List;

/**
 * Created by Tri on 7/26/2017.
 */

@Service
public class DMDonViServiceImpl implements DMDonViService {

    @Autowired
    private DMDonViRepository dmDonViRepository;

    @Override
    public DMDonVi save(DMDonVi dmDonVi) {
        return dmDonViRepository.save(dmDonVi);
    }

    @Override
    public List<DMDonVi> findAllFaculty() {
        return dmDonViRepository.findAllFaculty();
    }

    @Override
    public void delete(int id) {
        dmDonViRepository.delete(id);
    }

    @Override
    public Boolean editKhoa(int id, String ma, String ten) {
        try {
            dmDonViRepository.editKhoa(id, ma, ten);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public DMDonVi findOne(int id) {
        return dmDonViRepository.findOne(id);
    }
}
