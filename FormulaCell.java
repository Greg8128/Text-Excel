package textExcel;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FormulaCell extends RealCell{
    private final String formula;
    public Spreadsheet spreadsheet;
    public FormulaCell(String f, Spreadsheet s){
        super(f);
        formula = f;
        spreadsheet = s;
    }
    public double getDoubleValue(){
        //TODO: implement evaluation of formula
        String[] argsArr = formula.split("\\s+");
        List<String> args = new LinkedList<String>();
        for(int i = 1; i < argsArr.length - 1; i++){
            args.add(argsArr[i]);
        }
        //loop to identify cell values and formulas
        for(int i = 0; i < args.size(); i++){
            String arg = args.get(i);
            //handle cells
            if(Check.isCell(arg)){
                //replace cell with its double value
                Location loc = new SpreadsheetLocation(arg);
                Cell c = spreadsheet.getCell(loc);
                if (!(c instanceof RealCell)){
                    System.out.println(1/0);
                }
                RealCell rc = (RealCell)c;
                Double val = rc.getDoubleValue();
                args.set(i, val + "");
            }
            //handle formulas
            else if (arg.equals("SUM") || arg.equals("AVG")){
                String[] ranges = args.get(i+1).split("-");
                SpreadsheetLocation loc1 = new SpreadsheetLocation(ranges[0]);
                SpreadsheetLocation loc2 = new SpreadsheetLocation(ranges[1]);
                double result = 0;
                for(int row = loc1.getRow(); row <= loc2.getRow(); row++){
                    for(int col = loc1.getCol(); col <= loc2.getCol(); col++){
                        SpreadsheetLocation loc = new SpreadsheetLocation(row, col);
                        RealCell c = (RealCell)spreadsheet.getCell(loc);
                        result += c.getDoubleValue();
                    }
                }
                if (arg.equals("AVG")){
                    int cols = (1 + loc2.getCol()-loc1.getCol());
                    int rows = (1 + loc2.getRow()-loc1.getRow());
                    int numCells = cols * rows;
                    result /= numCells;
                }
                args.set(i, result + "");
                args.remove(i+1);
            }
        }

        //loop to find and evaluate formulas

        //loop to perform arithmetic
        while(args.size() > 1){
            Double num1 = Double.parseDouble(args.get(0));
            Double num2 = Double.parseDouble(args.get(2));
            String operation = args.get(1);
            double result = 0;
            switch(operation){
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
            }
            args.set(0, result + "");
            args.remove(2);
            args.remove(1);
        }
        return Double.parseDouble(args.get(0));

    }
}
