package vn.bkdn.cntt.Service;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.entity.DMLopMonHoc;

import java.util.List;

/**
 * Created by Tri on 7/10/2017.
 */

@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public XSSFWorkbook exportSemesterCalendar(List<DMLopMonHoc> lopMonHocs, String kiHoc, String namHoc) {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet = xssfWorkbook.createSheet("Thời khóa biểu năm học "+namHoc+" "+kiHoc );

    }
}
