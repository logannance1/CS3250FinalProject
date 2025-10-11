import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class CandleChart extends Chart {
	private ArrayList<Candle> candles;
	
	public ArrayList<Candle> getCandles() {
		return this.candles;
	}
	
	/**
	 * Updates the timeFrame and redraws candles
	 */
	@Override
	public void setTimeFrame(TimeFrame timeFrame) {
		super.setTimeFrame(timeFrame);
		candles = new ArrayList<Candle>(timeFrame.getData().size());
		
		for (int i = 0; i < timeFrame.getData().size(); ++i) {
			candles.add(new Candle(timeFrame.getData().get(i)));
		}
		
		draw();
	}
	
	/**
	 * Simple loop to draw each candle
	 */
	@Override
	public void draw() {
		super.draw();
		double space = (this.getWidth() - 50) / candles.size();
		
		for (int i = 0; i < candles.size(); ++i) {
			candles.get(i).draw(this, space, space * (i + 0.5) + 25);
		}
		
		drawXAxis();
	}
	
	// TODO Position x-axis with layout nodes instead of hard-coded positions
	private void drawXAxis() {	
		final int numCandles = candles.size();
		
		// Placement of x-axis labels
		int[] candleIndices = {
			0,
			(int) Math.rint(numCandles * .25) - 1,
			(int) Math.rint(numCandles * .5) - 1,
			(int) Math.rint(numCandles * .75) - 1,
			numCandles - 1,
		};
		
		for (int i = 0; i < candleIndices.length; ++i) {
			Candle candle = candles.get(candleIndices[i]);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM");
			final Label label = new Label(
				sdf.format(candle.getDatum().getDate()));
			
			Platform.runLater(() -> {
				label.setLayoutX(candle.getX() - label.getWidth() / 2);
			});
			
			label.setLayoutY(this.getHeight() - 25);
			this.getChildren().add(label);
		}
	}	
}