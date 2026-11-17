//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static String readEmployeeFile() throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("employees.txt")));
        String line = reader.readLine();
        reader.close();
        return line;
    }

    private static void writeEmployeeFile(String content, boolean append) throws Exception {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("employees.txt", append));
        writer.write(content);
        writer.close();
    }

    public static void main(String[] args) {
        // Check arguments
           if (args == null || args.length != 1) {
            System.out.println("Error:only one argument please");
            return;
        }
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                String line = readEmployeeFile();
                String employees[] = line.split(",");
                for (String employee : employees) {
                    System.out.println(employee);
                }
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                String line = readEmployeeFile();
                System.out.println(line);
                String employees[] = line.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex]);
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                String newEmployee = args[0].substring(1);
                writeEmployeeFile(", " + newEmployee, true);
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                String line = readEmployeeFile();
                String employees[] = line.split(",");
                boolean found = false;
                String searchTarget = args[0].substring(1);
                for (int index = 0; index < employees.length && !found; index++) {
                    if (employees[index].equals(searchTarget)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
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
            System.out.println("Data Loaded.");
        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                String line = readEmployeeFile();
                String employees[] = line.split(",");
                String targetEmployee = args[0].substring(1);
                for (int index = 0; index < employees.length; index++) {
                    if (employees[index].equals(targetEmployee)) {
                        employees[index] = "Updated";
                    }
                }
                writeEmployeeFile(String.join(",", employees), false);
            } catch (Exception exception) {
            }
            System.out.println("Data Updated.");
        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                String line = readEmployeeFile();
                String employees[] = line.split(",");
                String targetEmployee = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(targetEmployee);
                writeEmployeeFile(String.join(",", employeeList), false);
            } catch (Exception exception) {
            }
            System.out.println("Data Deleted.");
        }
    }
}