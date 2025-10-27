package chart;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import data.TimeFrame;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class CandleChart extends Chart {
	private double space;
	private ArrayList<Candle> candles;

	public CandleChart(XAxis xAxis, YAxis yAxis) {
		super(xAxis, yAxis);
	}
	
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
		
		space = this.getWidth() / (candles.size() + 1);
		draw();
	}
	
	/**
	 * Simple loop to draw each candle
	 */
	@Override
	public void draw() {
		super.draw();
		drawFirstCandle();
		
		for (int i = 1; i < candles.size(); ++i) {
			drawNonFirstCandle(i);
		}
		
//		drawXAxis();
	}
	
	private void drawFirstCandle() {
		double x = space;
		Candle candle = candles.get(0);
		Date date = candle.getDatum().getDate();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM");
		String month = sdf.format(date);

		Label label = new Label(month);

		Platform.runLater(() -> {
			label.setLayoutX(x + label.getWidth() / 2);
		});

		xAxis.getChildren().add(label);
		candle.draw(this, space, x, 64);
	}
	
	private void drawNonFirstCandle(int i) {
		Candle candle = candles.get(i);
		Date date = candle.getDatum().getDate();
		double x = space * (i + 1);
		int prevMonth = candles.get(i - 1).getDatum().getDate().getMonth();
		
		if (date.getMonth() >= (prevMonth + 1) % 12) {
			SimpleDateFormat sdf = new SimpleDateFormat("MMM");
			String month = sdf.format(date);
			Label label = new Label(month);

			Platform.runLater(() -> {
				label.setLayoutX(x + label.getWidth() / 2);
			});
			
			xAxis.getChildren().add(label);
		}
		
		candle.draw(this, space, x, 64);
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