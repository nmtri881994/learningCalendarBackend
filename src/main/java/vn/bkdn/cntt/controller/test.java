package vn.bkdn.cntt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import vn.bkdn.cntt.Service.GiaoVienService;
import vn.bkdn.cntt.Service.TaiKhoanHeThongService;
import vn.bkdn.cntt.Service.TaiKhoanHeThong_VaiTroService;
import vn.bkdn.cntt.common.CalendarCommonUtils;
import vn.bkdn.cntt.entity.GiaoVien;
import vn.bkdn.cntt.entity.TaiKhoanHeThong_VaiTro;
import vn.bkdn.cntt.repository.TaiKhoanHeThongRepository;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by XuanVinh on 3/13/2017.
 */

@RestController
@RequestMapping(value = "api/test")
@CrossOrigin(value = "*")
public class test {

    @Autowired
    private TaiKhoanHeThong_VaiTroService taiKhoanHeThong_vaiTroService;

    @Autowired
    private TaiKhoanHeThongRepository taiKhoanHeThongRepository;

    @Autowired
    private GiaoVienService giaoVienService;

    @GetMapping(value = "/1")
    public void getCalendaer() throws ParseException {
        Calendar c = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateFormat.parse("11-03-2017");
        c.setTime(date);
    }

    @GetMapping(value="/taikhoan/{taiKhoanId}")
    public ResponseEntity<List<TaiKhoanHeThong_VaiTro>> getTaiKhoanVaiTro(@PathVariable int taiKhoanId){
        return new ResponseEntity<List<TaiKhoanHeThong_VaiTro>>(taiKhoanHeThong_vaiTroService.getTaiKhoanHeThongVaiTrosByTaiKhoan(taiKhoanHeThongRepository.findOne(taiKhoanId)), HttpStatus.OK);
    }

    @GetMapping(value="/giaovien/{maGiaoVien}")
    public ResponseEntity<GiaoVien> getGiaoVien(@PathVariable String maGiaoVien){
        return new ResponseEntity<GiaoVien>(giaoVienService.findByMaGiaoVien(maGiaoVien), HttpStatus.OK);
    }

    private List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping("/chats")
    public SseEmitter chats(){
        SseEmitter sseEmitter = new SseEmitter();
        emitters.add(sseEmitter);

        sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));
        return sseEmitter;
    }

    @PostMapping(value = "/chat")
    public void postChatMess(@RequestBody String chatMess) throws IOException {
        System.out.println("Received message: "+chatMess);
        for (SseEmitter sseEmitter:
             emitters) {
            sseEmitter.send(SseEmitter.event().name("response").data(chatMess));
        }
    }
}
