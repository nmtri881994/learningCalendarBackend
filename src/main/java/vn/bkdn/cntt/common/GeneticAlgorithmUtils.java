package vn.bkdn.cntt.common;

import vn.bkdn.cntt.Service.LopMonHocService;
import vn.bkdn.cntt.entity.LopMonHoc;
import vn.bkdn.cntt.entity.TKB_LichHocTheoTuan;

import java.util.Set;

/**
 * Created by XuanVinh on 5/3/2017.
 */
public class GeneticAlgorithmUtils {

    private LopMonHocService lopMonHocService;

//    public int soTietChuaCoLichConLaiCuaLopMonHoc(LopMonHoc lopMonHoc){
//        int totalLessons = lopMonHoc.getSoTietLyThuyet() + lopMonHoc.getSoTietThucHanh();
//
//        System.out.println("-----------"+totalLessons);
//
//        int haveClassLessons = 0;
//
//        if(!lopMonHoc.getTkb_lichHocTheoTuans().isEmpty()){
//            for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan:
//                    lopMonHoc.getTkb_lichHocTheoTuans()) {
//                int numberOfWeeks = tkb_lichHocTheoTuan.getTuanKetThuc() - tkb_lichHocTheoTuan.getTuanBatDau() + 1;
//                haveClassLessons += numberOfWeeks*(tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getThuTu() - tkb_lichHocTheoTuan.getTkb_tietDauTien().getThuTu() +1);
//            }
//
//            return totalLessons - haveClassLessons;
//        }else{
//            return totalLessons;
//        }
//
//    }

}
