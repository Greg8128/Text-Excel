package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid
{
	private int rows;
	private int cols;
	private Cell[][] cells;
	public Spreadsheet(){
		rows = 20;
		cols = 12;
		cells = new Cell[rows][cols];
		clear();
	}

	//sets every cell to an EmptyCell
	public void clear(){
		for(int row = 0; row < rows; row++){
			for(int col = 0; col < cols; col++){
				cells[row][col] = new EmptyCell();
			}
		}
	}

	//sets cell at specified location to an EmptyCell
	public void clearCell(SpreadsheetLocation loc){
		int row = loc.getRow();
		int col = loc.getCol();
		cells[row][col] = new EmptyCell();
	}

	@Override
	public String processCommand(String command)
	{
		//empty command
		if(command.equals("")) return "";

		//divide command into arguments
		String[] args = command.split("\\s+");

		//clear command
		if(args[0].equals("clear")){
			//if cell is specified, clear cell
			if (args.length > 1){
				SpreadsheetLocation loc = new SpreadsheetLocation(args[1]);
				clearCell(loc);
			}
			//if not, clear spreadsheet
			else {
				clear();
			}
			return getGridText();
		}

		//commands starting with cell value
		if(Check.isCell(args[0])){
			SpreadsheetLocation loc = new SpreadsheetLocation(args[0]);
			Cell cell = cells[loc.getRow()][loc.getCol()];

			//"cell" == get full value of cell
			if(args.length == 1){
				return cell.fullCellText();
			}

			//"cell = value" == set cell value
			else if (args.length >= 3 && args[1].equals("=")){


				String setTo = args[2];

				//if setTo is broken up, combine pieces
				for(int i = 3; i < args.length; i++){
					setTo += " " + args[i];
				}

				Cell newCell;
				//identify formula
				if(Check.isFormula(setTo)){
					System.out.println("formula");
					newCell = new FormulaCell(setTo, this);
				}
				//identify percent
				else if(Check.isPercent(setTo)){
					double percent = Double.parseDouble(setTo.substring(0,setTo.length()-1));
					newCell = new PercentCell(percent);
				}
				//identify number
				else if(Check.isNum(setTo)){
					newCell = new ValueCell(setTo);
				}
				//if it's not anything else, it's a string
				else {
					newCell  = new TextCell(setTo);
				}
				cells[loc.getRow()][loc.getCol()] = newCell;
				return getGridText();
			}
		}
		return "ERROR: Invalid command.";
	}

	@Override
	public int getRows()
	{
		return rows;
	}

	@Override
	public int getCols()
	{
		return cols;
	}

	@Override
	public Cell getCell(Location loc)
	{
		int row = loc.getRow();
		int col = loc.getCol();
		return cells[row][col];
	}

	@Override
	public String getGridText()
	{

		//TODO: make adjustable header
		String text = "   |A         |B         |C         |D         |E         |F         |G         |H         " +
				"|I         |J         |K         |L         |\n"; //header
		for(int row = 0; row < rows; row++){	//add each row
			String rowStr = (row+1) + "";		//add header
			while(rowStr.length() < 3){
				rowStr += " ";
			}
			for(int col = 0; col < cols; col++){	//add content of each cell with divider in front;
				rowStr += "|" + cells[row][col].abbreviatedCellText();
			}
			rowStr += "|\n"; //end divider and new line
			text += rowStr;	//add row
		}
		return text;

	}

}