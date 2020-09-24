package com.Weather.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Weather.Base.SessionData;

public class UiUtils {

	public static void waitForDOMStatusWithTimeOut(String status, int timeOut, WebDriver driver) throws Exception {
		long startTime = System.currentTimeMillis() / 1000;
		while ((System.currentTimeMillis() / 1000 - startTime) < timeOut) {
			if (((JavascriptExecutor) driver).executeScript("return document.readyState;").toString()
					.equalsIgnoreCase(status)) {
				return;
			}

		}
	}

	public static void launchBaseUrl() throws Exception {
		// SessionData.getDriver().get(AutomationConstants.URL);

	}

	public static void getMaxWindow() throws Exception {
		SessionData.getDriver().manage().window().maximize();

	}

	public static void applyImplicitWait() throws Exception {
		SessionData.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public static void waitForElement(WebDriver driver, WebElement element) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public static void waitForFluent(WebDriver driver, WebElement element) throws Exception {

		FluentWait wait = new FluentWait<WebDriver>(driver);

	}

	public void readFromExcel(List<Double> tempList, List<Double> tempMinList, List<Double> tempMaxList,
			List<Double> pressureList, List<Double> seaLevelList) throws IOException {
		String filePath = System.getProperty("user.dir");
		String fileName = "convertcsv.xlsx";
		File file = new File(filePath+"/convertcsv.xlsx");

		// Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook Workbook = null;

		// Find the file extension by splitting file name in substring and getting only
		// extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// Check condition if the file is xlsx file

		if (fileExtensionName.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class

			Workbook = new XSSFWorkbook(inputStream);

		}

		// Check condition if the file is xls file

		else if (fileExtensionName.equals(".xls")) {

			// If it is xls file then create object of HSSFWorkbook class

			Workbook = new HSSFWorkbook(inputStream);

		}

		// Read sheet inside the workbook by its name

		Sheet sheet = Workbook.getSheet("Sheet 1");

		// Find number of rows in excel file

		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		// Create a loop over all the rows of excel file to read it

		for (int i = 1; i <= rowCount; i++) {

			Row row = sheet.getRow(i);

			// Create a loop to print cell values in a row

			for (int j = 0; j < 5; j++) {

				// Print Excel data in console

				// System.out.print(row.getCell(j).getNumericCellValue() + "|| ");
				switch (j) {
				case 0:
					tempList.add(row.getCell(j).getNumericCellValue());
					break;
				case 1:
					tempMinList.add(row.getCell(j).getNumericCellValue());
					break;
				case 2:
					tempMaxList.add(row.getCell(j).getNumericCellValue());
					break;
				case 3:
					pressureList.add(row.getCell(j).getNumericCellValue());
					break;
				case 4:
					seaLevelList.add(row.getCell(j).getNumericCellValue());
					break;

				default:
					break;
				}

			}
		}

		System.out.println(tempList);
		System.out.println(tempMinList);
		System.out.println(tempMaxList);
		System.out.println(pressureList);
		System.out.println(seaLevelList);

	}

	public void writeToExcel(List<Double> t1, List<Double> t2, List<Double> t3, List<Double> t4, List<Double> t5,
			List<Double> exp1, List<Double> exp2, List<Double> exp3, List<Double> exp4, List<Double> exp5)
			throws IOException {

		// Create an object of File class to open xlsx file
		String filePath = System.getProperty("user.dir");
		File file = new File(filePath+"/Result.xlsx");

		// Create an object of FileInputStream class to read excel file
		String fileName = "Result.xlsx";
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		// Find the file extension by splitting file name in substring and getting only
		// extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// Check condition if the file is xlsx file

		if (fileExtensionName.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class

			workbook = new XSSFWorkbook(inputStream);

		}

		// Check condition if the file is xls file

		else if (fileExtensionName.equals(".xls")) {

			// If it is xls file then create object of XSSFWorkbook class

			workbook = new HSSFWorkbook(inputStream);

		}

//Read excel sheet by sheet name    

		Sheet sheet = workbook.getSheet("Sheet1");

		String[] colNames = { "Expected temp", "Actual temp", "Expected Mintemp", "Actual Mintemp", "Expected Maxtemp",
				"Actual Maxtemp", "Expected pressure", "Actual pressure", "Expected sealevel", "Actual sealevel", };
//Create column labels
		for (int row = 0; row < 1; row++) {
			Row newRow = sheet.createRow(row);
			for (int col = 0; col < 10; col++) {
				Cell cell = newRow.createCell(col);
				cell.setCellValue(colNames[col]);
			}
		}

		for (int i = 0; i < 3; i++) {
			Row newRow = sheet.createRow(i + 1);
			// Create a loop to print cell values in a row

			for (int j = 0; j < 10; j++) {
				Cell cell = newRow.createCell(j);
				// Print Excel data in console

				switch (j) {
				case 0:
					cell.setCellValue(String.valueOf(exp1.get(i)));
					break;
				case 1:
					cell.setCellValue(String.valueOf(t1.get(i)));
				case 2:
					cell.setCellValue(String.valueOf(exp2.get(i)));
					break;
				case 3:
					cell.setCellValue(String.valueOf(t2.get(i)));
					break;
				case 4:
					cell.setCellValue(String.valueOf(exp3.get(i)));
					break;
				case 5:
					cell.setCellValue(String.valueOf(t3.get(i)));
					break;
				case 6:
					cell.setCellValue(String.valueOf(exp4.get(i)));
					break;
				case 7:
					cell.setCellValue(String.valueOf(t4.get(i)));
					break;
				case 8:
					cell.setCellValue(String.valueOf(exp5.get(i)));
					break;
				case 9:
					cell.setCellValue(String.valueOf(t5.get(i)));
					break;

				default:
					break;
				}

			}
		}

//Close input stream

		inputStream.close();

//Create an object of FileOutputStream class to create write data in excel file

		FileOutputStream outputStream = new FileOutputStream(file);

//write data in the excel file

		workbook.write(outputStream);

//close output stream

		outputStream.close();

	}

	private char[] InstanceOf(List<Double> t1) {
		// TODO Auto-generated method stub
		return null;
	}

	public void handleHttpsCertiffication() {
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

	}

}
