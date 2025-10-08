import java.util.Date;

import javafx.scene.layout.GridPane;

/**
 * MainLayout consits of a Chart, IndicatorSelector, and ErrorHandler.
 * The Chart is initially set with a time frame from the year 2000 to
 * present.
 */
public class MainLayout extends GridPane {
	private IndicatorSelector indicatorSelector = new IndicatorSelector();
	private ErrorHandler errorHandler = new ErrorHandler();
	private Chart chart;
	
	/**
	 * Creates a new MainLayout with a CandlestickChart
	 */
	public MainLayout() {
		chart = new CandleChart();
		this.add(chart, 0, 0);
		this.add(new DataForm(chart), 0, 1);
	}
	
	// Returns the current start year
	private String startYearText() {
		return "Start Year: " +
			(chart.getTimeFrame().getBegin().getYear() + 1900);
	}
}
