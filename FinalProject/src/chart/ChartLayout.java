package chart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

/**
 * MainLayout consits of a Chart, IndicatorSelector, and ErrorHandler.
 * The Chart is initially set with a time frame from the year 2000 to
 * present.
 */
public class ChartLayout extends BorderPane {
	private XAxis xAxis;
	private YAxis yAxis;
	private Chart chart;
	
	/**
	 * Creates a new MainLayout with a CandlestickChart
	 */
	public ChartLayout() {
		xAxis = new XAxis();
		yAxis = new YAxis();
		chart = new CandleChart(xAxis, yAxis);
		var chartScroll = new ScrollPane(chart);
		chartScroll.setFitToHeight(true);

		chartScroll.setFitToWidth(false);
		chartScroll.setPannable(true);
		chartScroll.setStyle("-fx-background-color: #fff");
		this.setBottom(xAxis);
		this.setLeft(yAxis);

		this.setCenter(chartScroll);
	}
	
	public Chart getChart() {
		return chart;
	}
}
