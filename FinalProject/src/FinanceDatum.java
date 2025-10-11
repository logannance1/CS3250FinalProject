import java.util.Date;

public class FinanceDatum {
	private Date date;
	private double open;
	private double close;
	private double high;
	private double low;
	
	public FinanceDatum(Date date, double open, double high, double low,
		double close)
	{
		this.date = date;
		this.open = open;
		this.close = close;
		this.high = high;
		this.low = low;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
