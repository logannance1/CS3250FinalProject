package data;
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

	public double getOpen() {
		return open;
	}

	public double getClose() {
		return close;
	}

	public double getHigh() {
		return high;
	}

	public double getLow() {
		return low;
	}

	@Override
	public String toString() {
		return "open: " + open + ", high: " + high + ", low: " + low +
			", close: " + close;
	}
}
