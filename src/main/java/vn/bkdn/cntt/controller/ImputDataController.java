package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.bkdn.cntt.Service.DMDonViService;
import vn.bkdn.cntt.Service.DMLoaiDonViService;
import vn.bkdn.cntt.controller.APIEntity.Khoa;
import vn.bkdn.cntt.entity.DMDonVi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Tri on 7/26/2017.
 */

@RestController
@RequestMapping(value = "/api/input-data")
public class ImputDataController {

    @Autowired
    private DMLoaiDonViService dmLoaiDonViService;

    @Autowired
    private DMDonViService dmDonViService;

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/khoa")
    public ResponseEntity<Khoa> inputKhoa(@RequestBody Khoa khoa) {
        DMDonVi newDonVi = dmDonViService.save(new DMDonVi(khoa.getMa(), khoa.getTen(), dmLoaiDonViService.findOne(1)));
        return new ResponseEntity<Khoa>(new Khoa(newDonVi.getId(), newDonVi.getMa(), newDonVi.getTen()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @PostMapping(value = "/edit-khoa")
    public ResponseEntity<Boolean> editKhoa(@RequestBody Khoa khoa) {
        return new ResponseEntity<Boolean>(dmDonViService.editKhoa(khoa.getId(), khoa.getMa(), khoa.getTen()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/all-khoas")
    public ResponseEntity<List<Khoa>> getAllKhoas() {
        List<DMDonVi> dmDonVis = dmDonViService.findAllFaculty();
        List<Khoa> khoas = new ArrayList<>();
        for (DMDonVi dmDonVi :
                dmDonVis) {
            khoas.add(new Khoa(dmDonVi.getId(), dmDonVi.getMa(), dmDonVi.getTen()));
        }

        khoas.sort(Comparator.comparing(Khoa::getMa));
        return new ResponseEntity<List<Khoa>>(khoas, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GIAOVU')")
    @GetMapping(value = "/delete-khoa/{khoaId}")
    public ResponseEntity deleteKhoa(@PathVariable int khoaId) {
        dmDonViService.delete(khoaId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
