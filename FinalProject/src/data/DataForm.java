package data;
import java.text.SimpleDateFormat;

import chart.ChartLayout;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DataForm extends VBox {
	/**
	 * Creates a form which allows the user to input a csv file path
	 * along with start and end dates in order to update a chart
	 * @param chartLayout
	 */
	public DataForm(ChartLayout chartLayout) {
		this.setPadding(new Insets(8));
		
		Label lblDataFile = new Label("Data File Path:");
		TextField tfdDataFile = new TextField();
		tfdDataFile.setText("data/aapl.us.txt");
		tfdDataFile.setPromptText("data/aapl.us.txt");
		
		Label lblStart = new Label("Start Date (yyyy-mm-dd):");
		TextField tfdStart = new TextField();
		tfdStart.setText("2000-01-01");
		tfdStart.setPromptText("2000-01-01");
		
		Label lblEnd = new Label("End Date (yyyy-mm-dd):");
		TextField tfdEnd = new TextField();
		tfdEnd.setText("2000-02-01");
		tfdEnd.setPromptText("2000-02-01");
		
		Button btnUpdate = new Button("Update");
		
		btnUpdate.setOnAction(e -> {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
//				chartLayout.getScrollPane().requestLayout();
				chartLayout.getChart().setTimeFrame(new TimeFrame(
					format.parse(tfdStart.getText()),
					format.parse(tfdEnd.getText()),
					tfdDataFile.getText()
				));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		this.getChildren().addAll(lblDataFile, tfdDataFile, lblStart, tfdStart,
			lblEnd, tfdEnd, btnUpdate);
	}
}
