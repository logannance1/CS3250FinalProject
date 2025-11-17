package chart;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

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
		
		Pane pane = new Pane();
		pane.setPadding(new Insets(8));
		chart = new CandleChart(xAxis, yAxis);
		pane.getChildren().add(chart);

		this.setBottom(xAxis);
		this.setLeft(yAxis);
		this.setCenter(pane);
	}
	
	public Chart getChart() {
		return chart;
	}
}
