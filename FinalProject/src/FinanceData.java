
public class FinanceData {
	private double open;
	private double close;
	private double high;
	private double low;
	
	public FinanceData(double open, double high, double low, double close) {
		this.open = open;
		this.close = close;
		this.high = high;
		this.low = low;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}
	
	@Override
	public String toString() {
		return "open: " + open + ", high: " + high + ", low: " + low +
			", close: " + close;
	}
}
