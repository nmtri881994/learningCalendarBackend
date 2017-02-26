package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.bkdn.cntt.Service.TeacherService;
import vn.bkdn.cntt.entity.Teacher;

import java.util.List;

/**
 * Created by Tri on 2/25/2017.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="api/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers(){
        System.out.println("11111");
        return new ResponseEntity<List<Teacher>>(teacherService.findAll(), HttpStatus.OK);
    }
}