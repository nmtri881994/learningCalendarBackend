package vn.bkdn.cntt.Service;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import vn.bkdn.cntt.controller.APIEntity.ExcelTKB;
import vn.bkdn.cntt.entity.DMLopMonHoc;
import vn.bkdn.cntt.entity.TKB_LichHocTheoTuan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tri on 7/10/2017.
 */

@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public XSSFWorkbook exportSemesterCalendar(List<DMLopMonHoc> lopMonHocs, String kiHoc, String namHoc) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Thời khóa biểu năm hoc " + namHoc + " " + kiHoc);
        sheet.setColumnWidth(0, 7000);
        sheet.setColumnWidth(1, 7000);
        sheet.setColumnWidth(2, 7000);
        sheet.setColumnWidth(3, 7000);
        sheet.setColumnWidth(4, 7000);
        sheet.setColumnWidth(5, 7000);
        sheet.setColumnWidth(6, 7000);
        XSSFRow header = sheet.createRow(0);
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);

        header.createCell(0).setCellValue("Thứ 2");
        header.createCell(1).setCellValue("Thứ 3");
        header.createCell(2).setCellValue("Thứ 4");
        header.createCell(3).setCellValue("Thứ 5");
        header.createCell(4).setCellValue("Thứ 6");
        header.createCell(5).setCellValue("Thứ 7");
        header.createCell(6).setCellValue("Chủ nhật");

        header.getCell(0).setCellStyle(cellStyle);
        header.getCell(1).setCellStyle(cellStyle);
        header.getCell(2).setCellStyle(cellStyle);
        header.getCell(3).setCellStyle(cellStyle);
        header.getCell(4).setCellStyle(cellStyle);
        header.getCell(5).setCellStyle(cellStyle);
        header.getCell(6).setCellStyle(cellStyle);

        List<List<ExcelTKB>> tkbSang = new ArrayList<>();
        List<List<ExcelTKB>> tkbChieu = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            tkbSang.add(new ArrayList<ExcelTKB>());
            tkbChieu.add(new ArrayList<ExcelTKB>());
        }

        for (DMLopMonHoc dmLopMonHoc :
                lopMonHocs) {
            for (TKB_LichHocTheoTuan tkb_lichHocTheoTuan :
                    dmLopMonHoc.getTkb_lichHocTheoTuans()) {
                if (tkb_lichHocTheoTuan.getTkb_tietCuoiCung().getThuTu() <= 5) {
                    tkbSang.get(tkb_lichHocTheoTuan.getTkb_thu().getId() - 2).add(new ExcelTKB(dmLopMonHoc.getDmMonHoc(), dmLopMonHoc.getDmNhanVien(), tkb_lichHocTheoTuan));
                } else {
                    tkbChieu.get(tkb_lichHocTheoTuan.getTkb_thu().getId() - 2).add(new ExcelTKB(dmLopMonHoc.getDmMonHoc(), dmLopMonHoc.getDmNhanVien(), tkb_lichHocTheoTuan));
                }
            }
        }

        XSSFRow morningRow = sheet.createRow(1);
        for (int i = 0; i < 7; i++) {
            String value = "";
            for (ExcelTKB excelTKB :
                    tkbSang.get(i)) {
                value += excelTKB.getDmMonHoc().getTen() + "\n" + excelTKB.getDmNhanVien().getHoDem() + " "
                        + excelTKB.getDmNhanVien().getTen() + "\n" + excelTKB.getTkb_lichHocTheoTuan().getDmGiangDuong().getTen()
                        + "\n" + excelTKB.getTkb_lichHocTheoTuan().getTkb_tietDauTien().getTen() + " tới "
                        + excelTKB.getTkb_lichHocTheoTuan().getTkb_tietCuoiCung().getTen() + "\n" + "Tuần "
                        + excelTKB.getTkb_lichHocTheoTuan().getTuanBatDau() + " tới " + excelTKB.getTkb_lichHocTheoTuan().getTuanKetThuc();

                value += "\n\n";
            }
            morningRow.createCell(i).setCellValue(value);
            morningRow.getCell(i).setCellStyle(cellStyle);
        }

        XSSFRow afternoonRow = sheet.createRow(2);
        for (int i = 0; i < 7; i++) {
            String value = "";
            for (ExcelTKB excelTKB :
                    tkbChieu.get(i)) {
                value += excelTKB.getDmMonHoc().getTen() + "\n" + excelTKB.getDmNhanVien().getHoDem() + " "
                        + excelTKB.getDmNhanVien().getTen() + "\n" + excelTKB.getTkb_lichHocTheoTuan().getDmGiangDuong().getTen()
                        + "\n" + excelTKB.getTkb_lichHocTheoTuan().getTkb_tietDauTien().getTen() + " tới "
                        + excelTKB.getTkb_lichHocTheoTuan().getTkb_tietCuoiCung().getTen() + "\n" + "Tuần "
                        + excelTKB.getTkb_lichHocTheoTuan().getTuanBatDau() + " tới " + excelTKB.getTkb_lichHocTheoTuan().getTuanKetThuc();

                value += "\n\n";
            }
            afternoonRow.createCell(i).setCellValue(value);
            afternoonRow.getCell(i).setCellStyle(cellStyle);
        }

        return workbook;
    }
}
