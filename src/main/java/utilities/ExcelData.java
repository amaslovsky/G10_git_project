package utilities;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExcelData {

    public Collection excelCollection() {
        String pathToDataFile = "src/main/resources/invalidRegistrationData.xlsx";

        List<Object[]> data = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(pathToDataFile);
            Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheet("invalidRegistrationData");

            boolean skipFirstRow = true;
            int startRow = skipFirstRow ? 1 : 0;  // as a variant - int startRow = 0; if (skipFirstRow) startRow = 1;

            for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    String email = String.valueOf(row.getCell(0));
                    String emailConfirm = String.valueOf(row.getCell(1));
                    String password = String.valueOf(row.getCell(2));
                    String firstName = String.valueOf(row.getCell(3));
                    String lastName = String.valueOf(row.getCell(4));
                    String acceptTermsAndConditions = String.valueOf(row.getCell(5));
                    String submit = String.valueOf(row.getCell(6));
                    String errorMessages = String.valueOf(row.getCell(7));
                    data.add(new Object[] {email, emailConfirm, password, firstName, lastName, acceptTermsAndConditions, submit, errorMessages});
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

}
