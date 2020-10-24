package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location
{
    private int row;
    private int col;
    @Override
    public int getRow()
    {
        return row;
    }

    @Override
    public int getCol()
    {
        return col;
    }
    
    public SpreadsheetLocation(String cellName)
    {
        char colChar = cellName.charAt(0);
        col = Character.getNumericValue(colChar) - 10;
        String rowStr = cellName.substring(1);
        row = Integer.parseInt(rowStr) - 1;
    }
    public SpreadsheetLocation(int row, int col){
        this.row = row;
        this.col = col;
    }

}
