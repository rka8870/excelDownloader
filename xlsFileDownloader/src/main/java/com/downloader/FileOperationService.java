/**
 * 
 */
package com.downloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/** Raunak Kumar Agarwal **/
@Service
@Slf4j
public class FileOperationService {
	
	public void downloadeWorkBook(List<User> list) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet  = workbook.createSheet("Users");
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillBackgroundColor(IndexedColors.LIGHT_YELLOW.index);
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("User Id");
		cell.setCellStyle(headerStyle);
		cell = row.createCell(1);
		cell.setCellValue("Name");
		cell.setCellStyle(headerStyle);
		cell = row.createCell(2);
		cell.setCellValue("Email Adderess");
		cell.setCellStyle(headerStyle);
		cell = row.createCell(3);
		cell.setCellValue("Phone No");
		cell.setCellStyle(headerStyle);
		int rowNum = 1;
		for(User user:list) {
			Row data = sheet.createRow(rowNum++);
			data.createCell(0).setCellValue(user.getUserId());
			data.createCell(1).setCellValue(user.getName());
			data.createCell(2).setCellValue(user.getEmailId());
			data.createCell(3).setCellValue(user.getMobileNo());
		}
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			workbook.write(stream);
			File newfile = new File("H:\\JioDownload\\users.xlsx");
			newfile.createNewFile();
			FileOutputStream fileStream  = new FileOutputStream(newfile);
			fileStream.write(stream.toByteArray());
			fileStream.close();
		} catch (IOException e) {
			log.error("Error occured while downloading excel");
			throw new FileOperationException("Error occured while downloading excel");
		}
	}
	
}
