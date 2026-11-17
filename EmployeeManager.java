//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

/**
 * Utility program to manage employee data records from a flat-file storage system.
 * Supports operations such as listing, random selection, searching, adding, 
 * updating, deleting, and counting character/word statistics.
 */
public class EmployeeManager {

    /**
     * Reads the single line of employee records stored in the storage file.
     * 
     * @return Raw string data containing comma-separated employee records.
     * @throws Exception If an I/O error occurs while opening or reading the file.
     */
    private static String readEmployeeFile() throws Exception {
        BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.FILE_NAME)));
        String rawDataLine = fileReader.readLine();
        fileReader.close();
        return rawDataLine;
    }

    /**
     * Writes or appends employee string content to the target storage file.
     * 
     * @return void
     * @throws Exception If an I/O error occurs while opening or writing to the file.
     */
    private static void writeEmployeeFile(String content, boolean appendMode) throws Exception {
        BufferedWriter fileWriter = new BufferedWriter(
                new FileWriter(Constants.FILE_NAME, appendMode));
        fileWriter.write(content);
        fileWriter.close();
    }

    public static void main(String[] commandArgs) {
        // Validate argument presence and count
        if (commandArgs == null || commandArgs.length != 1) {
            System.out.println(Constants.ERROR_SINGLE_ARG_REQUIRED);
            return;
        }

        String operationFlag = commandArgs[0];

        // Operation 1: List all employees
        if (operationFlag.equals(Constants.LIST_ALL_FLAG)) {
            System.out.println(Constants.MSG_LOADING_DATA);
            try {
                String[] employeeList = readEmployeeFile().split(Constants.COMMA_DELIMITER);
                for (String employeeName : employeeList) {
                    System.out.println(employeeName);
                }
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_LOADED);
        
        // Operation 2: Pick a random employee
        } else if (operationFlag.equals(Constants.SELECT_RANDOM_FLAG)) {
            System.out.println(Constants.MSG_LOADING_DATA);
            try {
                String[] employeeList = readEmployeeFile().split(Constants.COMMA_DELIMITER);
                Random randomGenerator = new Random();
                int randomIndex = randomGenerator.nextInt(employeeList.length);
                System.out.println(employeeList[randomIndex]);
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_LOADED);

        // Operation 3: Add new employee entry (+Name)
        } else if (operationFlag.contains(Constants.ADD_EMPLOYEE_FLAG)) {
            System.out.println(Constants.MSG_LOADING_DATA);
            try {
                String newEmployeeName = operationFlag.substring(1);
                writeEmployeeFile(Constants.COMMA_DELIMITER + Constants.SPACE_DELIMITER + newEmployeeName, true);
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_LOADED);

        // Operation 4: Search for an employee (?Name)
        } else if (operationFlag.contains(Constants.SEARCH_EMPLOYEE_FLAG)) {
            System.out.println(Constants.MSG_LOADING_DATA);
            try {
                String[] employeeList = readEmployeeFile().split(Constants.COMMA_DELIMITER);
                String targetEmployeeName = operationFlag.substring(1);
                int searchIndex = 0;
                for (searchIndex = 0; searchIndex < employeeList.length; searchIndex++) {
                    if (employeeList[searchIndex].trim().equals(targetEmployeeName)) {
                        System.out.println(Constants.MSG_EMPLOYEE_FOUND);
                        break;
                    }
                }
                if (searchIndex == employeeList.length) {
                    System.out.println(Constants.MSG_EMPLOYEE_NOT_FOUND);
                }
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_LOADED);

        // Operation 5: Count total words and character length
        } else if (operationFlag.contains(Constants.COUNT_WORDS_FLAG)) {
            System.out.println(Constants.MSG_LOADING_DATA);
            try {
                String fileContent = readEmployeeFile();
                String[] parsedWords = fileContent.trim().split(Constants.WHITESPACE_REGEX);
                System.out.println(parsedWords.length + " word(s) found " + fileContent.length());
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_LOADED);

        // Operation 6: Update employee entry (uName)
        } else if (operationFlag.contains(Constants.UPDATE_EMPLOYEE_FLAG)) {
            System.out.println(Constants.MSG_LOADING_DATA);
            try {
                String[] employeeList = readEmployeeFile().split(Constants.COMMA_DELIMITER);
                String targetToUpdate = operationFlag.substring(1);
                for (int index = 0; index < employeeList.length; index++) {
                    if (employeeList[index].equals(targetToUpdate)) {
                        employeeList[index] = Constants.UPDATED_TEXT_LABEL;
                    }
                }
                writeEmployeeFile(String.join(Constants.COMMA_DELIMITER, employeeList), false);
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_UPDATED);

        // Operation 7: Delete employee entry (dName)
        } else if (operationFlag.contains(Constants.DELETE_EMPLOYEE_FLAG)) {
            System.out.println(Constants.MSG_LOADING_DATA);
            try {
                String[] employeeList = readEmployeeFile().split(Constants.COMMA_DELIMITER);
                String targetToDelete = operationFlag.substring(1);
                List<String> updatedList = new ArrayList<>(Arrays.asList(employeeList));
                updatedList.remove(targetToDelete);
                writeEmployeeFile(String.join(Constants.COMMA_DELIMITER, updatedList), false);
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_DELETED);

        // Fallback: Invalid command-line argument
        } else {
            System.out.println(Constants.ERROR_INVALID_ARGUMENT);
        }
    }
}