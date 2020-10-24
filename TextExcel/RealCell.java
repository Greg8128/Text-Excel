package textExcel;

public abstract class RealCell implements Cell {
    String text;
    RealCell(String str){
        text = str;
    }
    public abstract double getDoubleValue();

    public String fullCellText(){
        return text;
    }

    public String abbreviatedCellText() {
        String abbreviation = getDoubleValue() + "";
        if(abbreviation.length() > 10) abbreviation = abbreviation.substring(0, 10);
        while(abbreviation.length() < 10) abbreviation += " ";
        return abbreviation;
    }
}
