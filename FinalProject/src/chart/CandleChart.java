package chart;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import data.FinanceDatum;
import data.TimeFrame;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.input.ScrollEvent;

public class CandleChart extends Chart {
	private double space;
	private ArrayList<Candle> candles;
	private static final double MIN_SPACE = 32;

	public CandleChart() {
		super();
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
		FinanceDatum fd = timeFrame.getData().get(0);
		candles.add(new Candle(this, fd));
		double wickHeight = Math.abs(fd.getHigh() - fd.getClose());
		
		for (int i = 1; i < timeFrame.getData().size(); ++i) {
			fd = timeFrame.getData().get(i);
			candles.add(new Candle(this, fd));
		}
		
		
		System.out.println(wickHeight);
		scale = 32 / wickHeight;
		space = 32;
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
	}
	
	private void drawFirstCandle() {
		double x = space;
		Candle candle = candles.get(0);
		Date date = candle.getDatum().getDate();
		drawWithDate(candle, x, date, "MMM-yyyy");
	}
	
	private void drawNonFirstCandle(int i) {
		Candle candle = candles.get(i);
		Date date = candle.getDatum().getDate();
		double x = space * (i + 1);
		Date prevDate = candles.get(i - 1).getDatum().getDate();
		
		if (date.getYear() != prevDate.getYear()) {
			drawWithDate(candle, x, date, "MMM-yyyy");
			return;
		}
		
		if (date.getMonth() != prevDate.getMonth()) {
			drawWithDate(candle, x, date, "MMM");
			return;
		}
	
		candle.draw(space, x, scale);
	}
	
	private void drawWithDate(Candle candle, double x, Date date,
		String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String str = sdf.format(date);
		Label label = new Label(str);
		candle.draw(space, x, scale, label);
	}

}