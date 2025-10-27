package misc;
import chart.Chart;

public abstract class Indicator {
	private Chart chart;
	
	public Indicator(Chart chart) {
		this.chart = chart;
	}
	
	public abstract void drawLine();
	public abstract void clear();
}
