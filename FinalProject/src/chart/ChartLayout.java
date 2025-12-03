package chart;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;

/**
 * MainLayout consits of a Chart, IndicatorSelector, and ErrorHandler.
 * The Chart is initially set with a time frame from the year 2000 to
 * present.
 */
public class ChartLayout extends BorderPane {
	private Chart chart;
	private ScrollPane scrollPane;
	private Group outerGroup;
	
	/**
	 * Creates a new MainLayout with a CandlestickChart
	 */
	public ChartLayout() {
		chart = new CandleChart();
		outerGroup = new Group(chart);
		scrollPane = new ScrollPane(outerGroup);
		
		this.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (event.isControlDown() && event.getCode() == KeyCode.DIGIT0) {
				chart.setScaleX(1);
				chart.setScaleY(1);
				event.consume();	
			}
		});
		
		scrollPane.addEventFilter(ScrollEvent.SCROLL,
			this::handleScroll);
		
		scrollPane.setPannable(true);
		this.setCenter(scrollPane);
	}
	
	public Chart getChart() {
		return chart;
	}
	
	public ScrollPane getScrollPane() {
		return scrollPane;
	}
	
	private void handleScroll(ScrollEvent event) {
		this.requestFocus();
		if (!event.isControlDown()) return;
		double scale = chart.getScaleY();
		double factor = event.getDeltaY() > 0 ? 1.1 : 0.9;
		double newScale = Math.clamp(scale * factor, 0.25, 4);

		double mouseX = event.getX();
		double mouseY = event.getY();
		
		double h = mouseX / chart.getBoundsInParent().getWidth();
		double v = mouseY / chart.getBoundsInParent().getHeight();
		
		chart.setScaleX(newScale);
		chart.setScaleY(newScale);
		
		// TODO Figure out how to actually do this, it's good enough
		// for now
		scrollPane.setHvalue(scrollPane.getHvalue() + h * (factor - 1));
		scrollPane.setVvalue(scrollPane.getVvalue() + v * (factor - 1));
		event.consume();
	}
}
