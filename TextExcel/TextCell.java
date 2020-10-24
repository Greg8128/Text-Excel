package textExcel;


public class TextCell implements Cell {
    private final String text, abbreviatedText;

    //cutoff length(10 characters), can be public because it is final
    public static final int cutoff = 10;

    //constructor
    TextCell(String t){
        text = t;

        //abbreviate text once at start
        if(text.length() >= cutoff){
            abbreviatedText = text.substring(0,cutoff);
        }
        //add spacing if text is under cutoff length
        else{
            String s = text;
            //repeatedly add spaces
            while (s.length() < cutoff){
                s += " ";
            }
            abbreviatedText = s;
        }
    }

    //cell text
    public String fullCellText(){
        return text;
    }

    //abbreviated text;
    public String abbreviatedCellText(){
       return abbreviatedText;
    }
}
