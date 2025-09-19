import java.util.Date;

public class MainLayout {
	private Chart chart = chart = new Chart(new TimeFrame(
		new Date(), new Date(), "data/foo.csv"));
	
	private IndicatorSelector indicatorSelector = new IndicatorSelector();
	private ErrorHandler errorHandler = new ErrorHandler();
}
