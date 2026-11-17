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
                for (String employee : readEmployeeFile().split(Constants.COMMA)) {
                    System.out.println(employee);
                }
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_LOADED);
        } else if (args[0].equals(Constants.RANDOM_FLAG)) {
            System.out.println(Constants.MSG_LOADING_DATA);
            try {
                String employees[] = readEmployeeFile().split(Constants.COMMA);
                System.out.println(employees[new Random().nextInt(employees.length)]);
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_LOADED);
        } else if (args[0].contains(Constants.ADD_FLAG)) {
            System.out.println(Constants.MSG_LOADING_DATA);
            try {
                writeEmployeeFile(Constants.COMMA + Constants.SPACE + args[0].substring(1), true);
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_LOADED);
        } else if (args[0].contains(Constants.SEARCH_FLAG)) {
            System.out.println(Constants.MSG_LOADING_DATA);
            try {
                String employees[] = readEmployeeFile().split(Constants.COMMA);
                String target = args[0].substring(1);
                int index = 0;
                for (index = 0; index < employees.length; index++) {
                    if (employees[index].trim().equals(target)) {
                        System.out.println(Constants.MSG_EMPLOYEE_FOUND);
                        break;
                    }
                }
                if (index == employees.length) {
                    System.out.println(Constants.MSG_EMPLOYEE_NOT_FOUND);
                }
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_LOADED);
        } else if (args[0].contains(Constants.COUNT_FLAG)) {
            System.out.println(Constants.MSG_LOADING_DATA);
            try {
                char[] characters = readEmployeeFile().toCharArray();
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
                String employees[] = readEmployeeFile().split(Constants.COMMA);
                for (int index = 0; index < employees.length; index++) {
                    if (employees[index].equals(args[0].substring(1))) {
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
                String employees[] = readEmployeeFile().split(Constants.COMMA);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(args[0].substring(1));
                writeEmployeeFile(String.join(Constants.COMMA, employeeList), false);
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DATA_DELETED);
        }
    }
}