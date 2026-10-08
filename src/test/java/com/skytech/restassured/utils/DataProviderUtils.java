package com.skytech.restassured.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.skytech.restassured.model.UserRequest;

public class DataProviderUtils {

    @DataProvider(name = "userData")
    public Object[][] getUserData() throws IOException {

        List<Map<String, String>> excelData =
                ExcelUtils.getExcelData(
                        "excel_test_data",
                        "Sheet1");

        Object[][] data =
                new Object[excelData.size()][1];

        for (int i = 0; i < excelData.size(); i++) {

            Map<String, String> row =
                    excelData.get(i);

            data[i][0] =
                    UserRequestMapper.map(row);
        }

        return data;
    }
}