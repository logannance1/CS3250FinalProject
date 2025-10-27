package chart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class XAxis extends Pane {
	private CandleChart chart;
	private int[] candleIndices = new int[5];
	private Label[] labels = new Label[5];
	
	public XAxis() {
		this.setPrefHeight(25);
		this.setStyle("-fx-background-color: #eee");
	}
	
	public void setCandles(CandleChart chart) {
		int numCandles = chart.getCandles().size();
		
		candleIndices[0] = 0;
		candleIndices[1] = (int) Math.rint(numCandles * .25) - 1;
		candleIndices[2] = (int) Math.rint(numCandles * .5) - 1;
		candleIndices[3] = (int) Math.rint(numCandles * .75) - 1;
		candleIndices[4] = numCandles - 1;
		
		updateLabels();
	}
	
	private Candle getCandle(int idx) {
		return chart.getCandles().get(candleIndices[idx]);
	}
	
	private void updateLabels() {
//		Candle candle = getCandle(0);
//		Date date = candle.getDatum().getDate();
//		SimpleDateFormat sdf = new SimpleDateFormat("dd");
//		labels[0] = new Label(sdf.format(date));
//		
//		Platform.runLater(() -> {
//			labels[0].setLayoutX(candle.getX() - labels[0].getWidth() / 2);
//		});
//		
//		this.getChildren().add(labels[0]);
//		
//		for (int i = 1; i < labels.length; ++i) {
//			candle = getCandle(i);
//			date = candle.getDatum().getDate();
//			labels[i] = new Label();
//			
//			if ()
//			
//			final Label label = new Label(
//				sdf.format(candle.getDatum().getDate()));
//			
//			Platform.runLater(() -> {
//				label.setLayoutX(candle.getX() - label.getWidth() / 2);
//			});
//			
//			label.setLayoutY(this.getHeight() - 25);
//			this.getChildren().add(label);
//		}
	}
}
