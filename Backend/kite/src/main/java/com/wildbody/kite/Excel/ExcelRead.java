package com.wildbody.kite.Excel;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.Service.ArticleService;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class ExcelRead implements ApplicationRunner {

    @Autowired
    private ArticleService svc;

    public void execute() {
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(new ClassPathResource("static/article_data.xlsx").getInputStream());
            int rowindex = 0;
            int columnindex = 0;
            // 시트 수 (첫번째에만 존재하므로 0을 준다)
            // 만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
            XSSFSheet sheet = workbook.getSheetAt(0);
            // 행의 수
            int rows = sheet.getPhysicalNumberOfRows();
            for (rowindex = 1; rowindex < rows; rowindex++) {
                // 행을읽는다
                XSSFRow row = sheet.getRow(rowindex);
                String company = "";
                String title = "";
                String newspaper = "";
                String publicationDate = "";
                String url = "";
                String content = "";
                String keyword = "";
                if (row != null) {
                    // 셀의 수
                    int cells = row.getLastCellNum();
                    for (columnindex = 0; columnindex <= cells; columnindex++) {
                        // 셀값을 읽는다
                        XSSFCell cell = row.getCell(columnindex);
                        String value = "";
                        // 셀이 빈값일경우를 위한 널체크
                        if (cell == null) {
                            continue;
                        } else {
                            // 타입별로 내용 읽기
                            switch (cell.getCellType()) {
                                case XSSFCell.CELL_TYPE_FORMULA:
                                    value = cell.getCellFormula();
                                    break;
                                case XSSFCell.CELL_TYPE_NUMERIC:
                                    value = cell.getNumericCellValue() + "";
                                    break;
                                case XSSFCell.CELL_TYPE_STRING:
                                    value = cell.getStringCellValue() + "";
                                    break;
                                case XSSFCell.CELL_TYPE_BLANK:
                                    value = cell.getBooleanCellValue() + "";
                                    break;
                                case XSSFCell.CELL_TYPE_ERROR:
                                    value = cell.getErrorCellValue() + "";
                                    break;
                            }
                        }
                        switch (columnindex) {
                            case 0:
                                company = value;
                                break;
                            case 2:
                                publicationDate = value;
                                break;
                            case 3:
                                newspaper = value;
                                break;
                            case 5:
                                title = value;
                                break;
                            case 15:
                                keyword = value;
                                break;
                            case 18:
                                url = value;
                                break;
                            case 19:
                                content = value;
                                break;
                        }
                    }
                }
                try {

                    svc.initArticle(
                        new Article(0, company, title, "", newspaper, publicationDate, url, "",
                            content, keyword));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println(e.getCause().toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.execute();
    }
}
