package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.bkdn.cntt.Service.TKB_TietService;
import vn.bkdn.cntt.entity.TKB_Tiet;

import java.util.List;

/**
 * Created by XuanVinh on 3/28/2017.
 */

@RestController
@RequestMapping(value = "api/tiet")
public class TietController {

    @Autowired
    private TKB_TietService tkb_tietService;

    @GetMapping
    public ResponseEntity<List<TKB_Tiet>> getALlTiet(){
        return new ResponseEntity<List<TKB_Tiet>>(tkb_tietService.findAll(), HttpStatus.OK);
    }
}
