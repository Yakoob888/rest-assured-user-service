package com.skytech.restassured.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public final class ExcelUtils {

	private ExcelUtils() {
		// Utility class
	}

	public static List<Map<String, String>> getExcelData(String fileName, String sheetName) throws IOException {

		List<Map<String, String>> testData = new ArrayList<>();

		String filePath = "src/test/resources/test-data/" + fileName + ".xlsx";

		try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {

			Sheet sheet = workbook.getSheet(sheetName);

			if (sheet == null) {
				throw new IllegalArgumentException("Sheet not found: " + sheetName);
			}

			DataFormatter formatter = new DataFormatter();

			Row headerRow = sheet.getRow(0);

			if (headerRow == null) {
				throw new IllegalArgumentException("Excel sheet is empty: " + sheetName);
			}

			List<String> headers = new ArrayList<>();

			for (int column = 0; column < headerRow.getLastCellNum(); column++) {

				Cell cell = headerRow.getCell(column);

				if (cell == null) {
					throw new IllegalArgumentException("Header cell is empty at column: " + column);
				}

				String header = formatter.formatCellValue(cell).trim();

				if (header.isEmpty()) {
					throw new IllegalArgumentException("Header cannot be empty at column: " + column);
				}

				headers.add(header);
			}

			for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {

				Row row = sheet.getRow(rowIndex);

				if (row == null) {
					continue;
				}

				Map<String, String> rowData = new LinkedHashMap<>();

				for (int column = 0; column < headers.size(); column++) {

					Cell cell = row.getCell(column);

					String value = cell == null ? "" : formatter.formatCellValue(cell).trim();

					rowData.put(headers.get(column), value);
				}

				testData.add(rowData);
			}
		}

		return testData;
	}
}