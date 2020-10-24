package textExcel;

public class ValueCell extends RealCell {
    private double value;
    public ValueCell (String s) {
        super(s);
        value = Double.parseDouble(s);
    }
	@Override
	public double getDoubleValue() {
		return value;
	}
}
