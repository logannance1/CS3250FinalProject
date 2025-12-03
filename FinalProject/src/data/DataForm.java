package data;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import chart.ChartLayout;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class DataForm extends VBox {
	private ChartLayout chartLayout;
	private Button btnSelectDataFile;
	private File dataFile;
	private FileChooser fileChooser;
	private DatePicker startDate;
	private DatePicker endDate;
	private Label lblErr;
	
	/**
	 * Creates a form which allows the user to input a csv file path
	 * along with start and end dates in order to update a chart
	 * @param chartLayout
	 */
	public DataForm(ChartLayout chartLayout) {
		this.chartLayout = chartLayout;
		btnSelectDataFile = new Button("Select File");
		fileChooser = new FileChooser();
		fileChooser.setTitle("Select Data Filey");
		fileChooser.setInitialDirectory(new File(
			"C:\\Users\\Wolfg\\git\\CS3250FinalProject\\FinalProject\\data"));
		
		startDate = new DatePicker();
		endDate = new DatePicker();
		startDate.setDisable(true);
		endDate.setDisable(true);
		
		btnSelectDataFile.setOnAction(e -> {
			this.dataFile = fileChooser.showOpenDialog(
				this.getScene().getWindow());
			
			boolean good = dataFile != null;
			
			if (good) {
				chartLayout.getChart().setTimeFrame(new TimeFrame(dataFile));				
			}
			
			startDate.setDisable(!good);
			endDate.setDisable(!good);
		});
		
		startDate.setOnAction(this::handleStartDateChange);
		endDate.setOnAction(this::handleEndDateChange);
		this.setPadding(new Insets(8));
		this.getChildren().addAll(btnSelectDataFile,
			startDate, endDate);
		
//		Label lblDataFile = new Label("Data File Path:");
//		TextField tfdDataFile = new TextField();
//		tfdDataFile.setText("data/aapl.us.txt");
//		tfdDataFile.setPromptText("data/aapl.us.txt");
//		
//		Label lblStart = new Label("Start Date (yyyy-mm-dd):");
//		TextField tfdStart = new TextField();
//		tfdStart.setText("2000-01-01");
//		tfdStart.setPromptText("2000-01-01");
//		
//		Label lblEnd = new Label("End Date (yyyy-mm-dd):");
//		TextField tfdEnd = new TextField();
//		tfdEnd.setText("2000-02-01");
//		tfdEnd.setPromptText("2000-02-01");
//		
//		btnUpdate = new Button("Update");
//		
//		btnUpdate.setOnAction(e -> {
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//			
//			try {				
//				chartLayout.getChart().setTimeFrame(new TimeFrame(
//					format.parse(tfdStart.getText()),
//					format.parse(tfdEnd.getText()),
//					tfdDataFile.getText()
//				));
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//		});
//		
//		this.getChildren().addAll(lblDataFile, tfdDataFile, lblStart, tfdStart,
//			lblEnd, tfdEnd, btnUpdate);
	}
	
	private void handleStartDateChange(ActionEvent e) {		
		if (endDate.getValue() == null) {
			this.chartLayout.getChart().setTimeFrame(new TimeFrame(
				dataFile, fromLocalDate(startDate.getValue())));
			return;
		}
		
		if (startDate.getValue().isAfter(endDate.getValue())) {
			this.lblErr = new Label("Start date must not be after end " +
				"date!");
			
			this.getChildren().add(this.lblErr);
			return;
		}
		
		chartLayout.getChart().setTimeFrame(new TimeFrame(
			dataFile, fromLocalDate(startDate.getValue()),
			fromLocalDate(endDate.getValue())));
	}
	
	private void handleEndDateChange(ActionEvent e) {
		
	}
	
	private Date fromLocalDate(LocalDate date) {
		return Date.from(date.atStartOfDay(
			ZoneId.systemDefault()).toInstant());
	}
}
