import java.util.ArrayList;

public class Chart {
	private TimeFrame timeFrame;
	private ArrayList<Indicator> indicators = new ArrayList<Indicator>();
	
	public Chart(TimeFrame timeFrame) {
		System.out.println(this.timeFrame.getBegin());
		this.timeFrame = timeFrame;
	}
	
	public void draw() {}

	public TimeFrame getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(TimeFrame timeFrame) {
		this.timeFrame = timeFrame;
	}

	public ArrayList<Indicator> getIndicators() {
		return indicators;
	}
	
	public void addIndicator(Indicator indicator) {
		this.indicators.add(indicator);
	}
	
	public boolean removeIndicator(Indicator indicator) {
		return this.indicators.remove(indicator);
	}
}
