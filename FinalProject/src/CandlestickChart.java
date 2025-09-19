import java.util.ArrayList;

public class CandlestickChart extends Chart {
	private ArrayList<Candlestick> candlesticks;

	public CandlestickChart(TimeFrame timeFrame) {
		super(timeFrame);
		int size = timeFrame.getData().size();
		candlesticks = new ArrayList<Candlestick>(size);
		
		for (int i = 0; i < size; ++i) {
			candlesticks.add(new Candlestick(timeFrame.getData().get(i)));
		}
	}

	@Override
	public void draw() {
		
	}
	
	@Override
	public void setTimeFrame(TimeFrame timeFrame) {
		
	}
}
