package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Tri on 2/8/2017.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="api")
public class TestService {


    @GetMapping
    public String testGet(){
        return "abaadsa";
    }


}
