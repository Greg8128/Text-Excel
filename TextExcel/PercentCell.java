package textExcel;

import javax.xml.soap.Text;

public class PercentCell extends RealCell{
    private double percentValue, value;
    private int cutoff;
    PercentCell(double p){
        super(p/100 + "");
        percentValue = p;

        //percent value as String


    }
    public String abbreviatedCellText(){

        String abbreviatedValue = percentValue + "";

        int decimalPoint = abbreviatedValue.indexOf(".");
        if(decimalPoint != -1) abbreviatedValue = abbreviatedValue.substring(0, decimalPoint);



        if(abbreviatedValue.length() > 9) abbreviatedValue = abbreviatedValue.substring(0, 9);
        abbreviatedValue += "%";
        while(abbreviatedValue.length() < 10) abbreviatedValue += " ";

        value = percentValue / 100;return abbreviatedValue;
    }

    public double getDoubleValue(){
        return value;
    }
}
