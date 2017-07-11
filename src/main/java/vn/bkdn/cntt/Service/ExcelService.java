package vn.bkdn.cntt.Service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vn.bkdn.cntt.entity.DMLopMonHoc;

import java.util.List;

/**
 * Created by Tri on 7/10/2017.
 */
public interface ExcelService {
    XSSFWorkbook exportSemesterCalendar(List<DMLopMonHoc> lopMonHocs, String kiHoc, String namHoc);
}
