package textExcel;

public class Check {
    public static final String CELL_REGEX = "^[A-L](20|1\\d|[1-9])$",
    NUM_REGEX = "^-?\\d+(\\.\\d+)?$",
    PERCENT_REGEX = "^-?\\d+(\\.\\d+)?%$";

    public static boolean isCell(String s){
        return s.matches(CELL_REGEX);
    }
    public static boolean isNum(String s){
        return s.matches(NUM_REGEX);
    }
    public static boolean isPercent(String s){
        return s.matches(PERCENT_REGEX);
    }

    public static boolean isFormula(String s){
        String[] args = s.split("\\s+");
        if (!args[0].equals("(")) return false;
        if(!args[args.length-1].equals(")")) return false;
        return true;
    }
}
