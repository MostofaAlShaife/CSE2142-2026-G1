/**
 * Holds global constants for application file paths, command-line flags,
 * delimiters, and standard user interface output messages.
 */
public class Constants {
    // Data Storage File Path
    public static final String FILE_NAME = "employees.txt";
    
    // Formatting Delimiters
    public static final String COMMA_DELIMITER = ",";
    public static final String SPACE_DELIMITER = " ";
    public static final String WHITESPACE_REGEX = "\\s+";
    
    // Command Line Interface Flags
    public static final String LIST_ALL_FLAG = "l";
    public static final String SELECT_RANDOM_FLAG = "s";
    public static final String ADD_EMPLOYEE_FLAG = "+";
    public static final String SEARCH_EMPLOYEE_FLAG = "?";
    public static final String COUNT_WORDS_FLAG = "c";
    public static final String UPDATE_EMPLOYEE_FLAG = "u";
    public static final String DELETE_EMPLOYEE_FLAG = "d";
    
    // System Status & Error Messages
    public static final String ERROR_SINGLE_ARG_REQUIRED = "Error:only one argument please";
    public static final String ERROR_INVALID_ARGUMENT = "Error: Invalid argument passed.";
    public static final String MSG_LOADING_DATA = "Loading data ...";
    public static final String MSG_DATA_LOADED = "Data Loaded.";
    public static final String MSG_DATA_UPDATED = "Data Updated.";
    public static final String MSG_DATA_DELETED = "Data Deleted.";
    public static final String MSG_EMPLOYEE_FOUND = "Employee found!";
    public static final String MSG_EMPLOYEE_NOT_FOUND = "Employee not found!";
    public static final String UPDATED_TEXT_LABEL = "Updated";
}