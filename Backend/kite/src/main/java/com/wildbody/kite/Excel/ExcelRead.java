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
				Article article = new Article();
				XSSFRow row = sheet.getRow(rowindex);
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
							article.setCompany(value.trim());
							break;
						case 2:
							article.setPublicationDate(
									value.substring(0, 4) + "-" + value.substring(4, 6) + "-" + value.substring(6));
							break;
						case 3:
							article.setNewspaper(value);
							break;
						case 5:
							article.setTitle(value);
							break;
						case 15:
							article.setKeyword(value);
							break;
						case 18:
							article.setUrl(value);
							break;
						case 19:
							article.setContent(value);
							break;
						case 20:
							article.setUrl(value);
							break;
						}
					}
				}
				try {
					svc.initArticle(article);
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
//    this.execute();
	}
}
