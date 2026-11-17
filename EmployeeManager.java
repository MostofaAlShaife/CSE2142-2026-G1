//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static String readEmployeeFile() throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.FILE_NAME)));
        String line = reader.readLine();
        reader.close();
        return line;
    }

    private static void writeEmployeeFile(String content, boolean append) throws Exception {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.FILE_NAME, append));
        writer.write(content);
        writer.close();
    }

    public static void main(String[] args) {
        // Check arguments
           if (args == null || args.length != 1) {
            System.out.println(Constants.ERROR_ARG_COUNT);
            return;
        }
        if (args[0].equals(Constants.LIST_FLAG)) {
            System.out.println(Constants.MSG_LOADING_DATA);
            try {
                String line = readEmployeeFile();
                String employees[] = line.split(Constants.COMMA);
                for (String employee : employees) {
                    System.out.println(employee);
                }
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_LOADED);
        } else if (args[0].equals(Constants.RANDOM_FLAG)) {
            System.out.println(Constants.MSG_LOADING_DATA);
            try {
                String line = readEmployeeFile();
                System.out.println(line);
                String employees[] = line.split(Constants.COMMA);
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex]);
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_LOADED);
        } else if (args[0].contains(Constants.ADD_FLAG)) {
            System.out.println(Constants.MSG_LOADING_DATA);
            try {
                String newEmployee = args[0].substring(1);
                writeEmployeeFile(Constants.COMMA + Constants.SPACE + newEmployee, true);
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_LOADED);
        } else if (args[0].contains(Constants.SEARCH_FLAG)) {
            System.out.println(Constants.MSG_LOADING_DATA);
            try {
                String line = readEmployeeFile();
                String employees[] = line.split(Constants.COMMA);
                boolean found = false;
                String searchTarget = args[0].substring(1);
                for (int index = 0; index < employees.length && !found; index++) {
                    if (employees[index].equals(searchTarget)) {
                        System.out.println(Constants.MSG_EMPLOYEE_FOUND);
                        found = true;
                    }
                }
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_LOADED);
        } else if (args[0].contains(Constants.COUNT_FLAG)) {
            System.out.println(Constants.MSG_LOADING_DATA);
            try {
                String line = readEmployeeFile();
                char[] characters = line.toCharArray();
                boolean inWord = false;
                int count = 0;
                for (char character : characters) {
                    if (character == ' ') {
                        if (!inWord) {
                            count++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(count + " word(s) found " + characters.length);
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_LOADED);
        } else if (args[0].contains(Constants.UPDATE_FLAG)) {
            System.out.println(Constants.MSG_LOADING_DATA);
            try {
                String line = readEmployeeFile();
                String employees[] = line.split(Constants.COMMA);
                String targetEmployee = args[0].substring(1);
                for (int index = 0; index < employees.length; index++) {
                    if (employees[index].equals(targetEmployee)) {
                        employees[index] = Constants.UPDATED_TEXT;
                    }
                }
                writeEmployeeFile(String.join(Constants.COMMA, employees), false);
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_UPDATED);
        } else if (args[0].contains(Constants.DELETE_FLAG)) {
            System.out.println(Constants.MSG_LOADING_DATA);
            try {
                String line = readEmployeeFile();
                String employees[] = line.split(Constants.COMMA);
                String targetEmployee = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(targetEmployee);
                writeEmployeeFile(String.join(Constants.COMMA, employeeList), false);
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_DELETED);
        }
    }
}