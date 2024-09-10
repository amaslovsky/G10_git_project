package utilities;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExcelDataMap {
    private Logger logger = Logger.getLogger(getClass());

//    public Collection<Object[]> excelCollection() {
//        String pathToDataFile = "src/main/resources/invalidRegistrationData.xlsx";
//
//        try (InputStream inputStream = getClass().getClassLoader()
//                .getResourceAsStream("invalidRegistrationData.xlsx")) {
//            if (inputStream == null) {
//                throw new FileNotFoundException
//                        ("Resource not found: invalidRegistrationData.xlsx");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        String sheetName = "invalidRegistrationData";
//        boolean skipFirstRow = true;
//        logger.info("Data file path: " + pathToDataFile);
//        logger.info("sheetName: " + sheetName);
//        logger.info("skipFirstRow: " + skipFirstRow);
//
//
//        List<Object[]> data = new ArrayList<>();
//        try (FileInputStream inputStream = new FileInputStream(pathToDataFile);
//             Workbook workbook = WorkbookFactory.create(inputStream)) {
//                Sheet sheet = workbook.getSheet(sheetName);
//                int startRow = skipFirstRow ? 1 : 0;   //аналог попростіше: int i; if (skipFirstRow) i = 1; else i = 0;
//                for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
//                    Row row = sheet.getRow(i);
//                    if (row != null) {
////                        String email = getCellValue(row, 0);
//                        String email = String.valueOf(row.getCell(0));
//                        String emailConfirm = String.valueOf(row.getCell(1));
//                        String password = String.valueOf(row.getCell(2));
//                        String firstName = String.valueOf(row.getCell(3));
//                        String lastName = String.valueOf(row.getCell(4));
//                        String acceptTermsAndConditions = String.valueOf(row.getCell(5));
//                        String submit = String.valueOf(row.getCell(6));
//                        data.add(new Object[]{email, emailConfirm, password, firstName, lastName, acceptTermsAndConditions, submit});
//
//                        Map<String, String> expectedErrors = new HashMap<>();
//    //                    addCommentIfExists(sheet, row.getCell(0), "email", expectedErrors);
//                        addCommentIfExists(row.getCell(0), "email", expectedErrors);
//                        addCommentIfExists(row.getCell(1), "emailConfirm", expectedErrors);
//                        addCommentIfExists(row.getCell(2), "password", expectedErrors);
//                        addCommentIfExists(row.getCell(3), "firstName", expectedErrors);
//                        addCommentIfExists(row.getCell(4), "lastName", expectedErrors);
//                        addCommentIfExists(row.getCell(5), "check-box", expectedErrors);
//                        data.add(new Object[]{expectedErrors});
//                    }
//                }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return data;
//    }

    public Collection excelCollection() {
        String pathToDataFile = "src/main/resources/invalidRegistrationDataComments.xlsx";

        List<Object[]> data = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(pathToDataFile);
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheet("invalidRegistrationData");
///////////////////////////////////////////////////////////////
//            if (sheet != null) {
//                Row row = sheet.getRow(0); // Get the second row (index starts from 0)
//                if (row != null) {
//                    Cell cell = row.getCell(0); // Get the first cell (index starts from 0)
//                    if (cell != null) {
//                        Comment comment = cell.getCellComment();
//                        if (comment != null) {
//                            String commentText = comment.getString().getString();
//                            System.out.println("Cell Comment: " + commentText);
//                        } else {
//                            System.out.println("No comment found for cell.");
//                        }
//                    } else {
//                        System.out.println("Cell is null.");
//                    }
//                } else {
//                    System.out.println("Row is null.");
//                }
//            } else {
//                System.out.println("Sheet is null.");
//            }
///////////////////////////////////////////////////////////////
            boolean skipFirstRow = true;
            int startRow = skipFirstRow ? 1 : 0;

            for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
//                    String email = String.valueOf(row.getCell(0));
//                    String emailConfirm = String.valueOf(row.getCell(1));
//                    String password = String.valueOf(row.getCell(2));
//                    String firstName = String.valueOf(row.getCell(3));
//                    String lastName = String.valueOf(row.getCell(4));
//                    String acceptTermsAndConditions = String.valueOf(row.getCell(5));
//                    String submit = String.valueOf(row.getCell(6));

//                    String email = row.getCell(0).getCellComment().getString().getString();
//                    String emailConfirm = row.getCell(1).getCellComment().getString().getString();
//                    String password = row.getCell(2).getCellComment().getString().getString();
//                    String firstName = row.getCell(3).getCellComment().getString().getString();
//                    String lastName = row.getCell(4).getCellComment().getString().getString();
//                    String acceptTermsAndConditions = row.getCell(5).getCellComment().getString().getString();
//                    String submit = row.getCell(6).getCellComment().getString().getString();

                    String email = getComment(row.getCell(0));
                    String emailConfirm = getComment(row.getCell(1));
                    String password = getComment(row.getCell(2));
                    String firstName = getComment(row.getCell(3));
                    String lastName = getComment(row.getCell(4));
                    String acceptTermsAndConditions = getComment(row.getCell(5));
                    String submit = getComment(row.getCell(6));


                    // Collect error messages from comments
//                    Map<String, String> expectedErrors = new HashMap<>();
//                    addCommentIfExists(row.getCell(0), "email", expectedErrors);
//                    addCommentIfExists(row.getCell(1), "emailConfirm", expectedErrors);
//                    addCommentIfExists(row.getCell(2), "password", expectedErrors);
//                    addCommentIfExists(row.getCell(3), "firstName", expectedErrors);
//                    addCommentIfExists(row.getCell(4), "lastName", expectedErrors);
//                    addCommentIfExists(row.getCell(5), "check-box", expectedErrors);
                    // Loop through the map and print each key-value pair
//                    System.out.println(expectedErrors.size());
//                    for (Map.Entry<String, String> entry : expectedErrors.entrySet()) {
//                        String field = entry.getKey();
//                        String errorMessage = entry.getValue();
//                        System.out.println("Field: " + field + " - Error Message: " + errorMessage);
//                    }
                    data.add(new Object[] {email, emailConfirm, password, firstName, lastName, acceptTermsAndConditions, submit});
//                    data.add(new Object[] {expectedErrors});
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    private void addCommentIfExists(Cell cell, String fieldName, Map<String, String> expectedErrors) {
        if (cell != null && cell.getCellComment() != null) {
            Comment comment = cell.getCellComment();
            String commentText = comment.getString().getString();
            expectedErrors.put(fieldName, commentText);
        }
    }

    private String getComment(Cell cell) {
        if (cell != null && cell.getCellComment() != null) {
            Comment comment = cell.getCellComment();
            String commentText = comment.getString().getString();
            return commentText;
        }
        return "nothing";
    }

}
