import java.util.Date;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * MainLayout consits of a Chart, IndicatorSelector, and ErrorHandler.
 * The Chart is initially set with a time frame from the year 2000 to present.
 */
public class MainLayout extends BorderPane {
	private IndicatorSelector indicatorSelector = new IndicatorSelector();
	private ErrorHandler errorHandler = new ErrorHandler();
	private Chart chart = new Chart();
	
	/**
	 * Creates a new MainLayout. 
	 */
	public MainLayout() {
		// The main title for the program
		Label lblTitle = new Label("Financial Visualizer");
		// h1 class is added to stylelize the font size
		lblTitle.getStyleClass().add("h1");
		
		// Simple form to input a starting point for the chart
		// TODO: Draw the chart
		Label lblStartYear = new Label("Start Year:");
		TextField tfdStartYear = new TextField();
		Label lblDataFile = new Label("Data File:");
		TextField tfdDataFile = new TextField();
		
		// Submit button updates the chart
		Button btnUpdateChart = new Button("Update Chart");
		btnUpdateChart.setOnAction(event -> {
			// All Date years must be offset by 1900
			Date start = new Date(
				Integer.parseInt(tfdStartYear.getText()) - 1900, 0, 1);
			chart = new Chart(
				new TimeFrame(start, new Date(), tfdDataFile.getText()));
			
			tfdStartYear.setStyle("-fx-background-color: lightgray");
			tfdDataFile.setStyle("-fx-background-color: lightgray");
		});
		
		// Used to position nodes vertically with some spacing
		VBox vbx = new VBox();
		vbx.getChildren().addAll(lblStartYear, tfdStartYear,
			lblDataFile, tfdDataFile, btnUpdateChart);
		vbx.setSpacing(4);

		// Inline style to make the UI look cleaner
		this.setStyle("-fx-padding: 16px;");
		this.setTop(lblTitle);
		this.setLeft(vbx);
	}
}
