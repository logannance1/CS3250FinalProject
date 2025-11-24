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
	private ScrollPane scrollPane;
	
	/**
	 * Creates a new MainLayout with a CandlestickChart
	 */
	public ChartLayout() {
		xAxis = new XAxis();
		yAxis = new YAxis();
		chart = new CandleChart(xAxis, yAxis);
		scrollPane = new ScrollPane(chart);
		scrollPane.setFitToHeight(false);

		scrollPane.setFitToWidth(false);
		scrollPane.setPannable(true);
		scrollPane.setStyle("-fx-background-color: #fff");
		this.setBottom(xAxis);
		this.setLeft(yAxis);

		this.setCenter(scrollPane);
	}
	
	public Chart getChart() {
		return chart;
	}
	
	public ScrollPane getScrollPane() {
		return scrollPane;
	}
}
