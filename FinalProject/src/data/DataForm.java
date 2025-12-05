package data;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import chart.Chart;
import chart.ChartLayout;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class DataForm extends HBox {
	private ChartLayout chartLayout;
	private Label lblStock;
	private Button btnSelectDataFile;
	private File dataFile;
	private FileChooser fileChooser;
	private LabelDatePicker startDate;
	private LabelDatePicker endDate;
	private Button btnUpdate;
	private Label lblErr;
	
	/**
	 * Creates a form which allows the user to input a csv file path
	 * along with start and end dates in order to update a chart
	 * @param chartLayout
	 */
	public DataForm(ChartLayout chartLayout) {
		super(8);
		this.chartLayout = chartLayout;
		this.setAlignment(Pos.CENTER);
		
		lblStock = new Label();
		btnSelectDataFile = new Button("Select File");
		fileChooser = new FileChooser();
		
		fileChooser.setTitle("Select Data File");
		fileChooser.setInitialDirectory(new File(
			"C:\\Users\\Wolfg\\git\\CS3250FinalProject\\FinalProject\\data"));
		
		startDate = new LabelDatePicker("Start:");
		endDate = new LabelDatePicker("End:");
		lblErr = new Label();
		
		btnUpdate = new Button("Update");
		btnUpdate.setOnAction(this::handleUpdateClick);
		btnUpdate.setDisable(true);
		
		btnSelectDataFile.setOnAction(this::handleSelectDataFileClick);
		this.setPadding(new Insets(8));
		this.getChildren().addAll(lblStock, btnSelectDataFile,
			startDate, endDate, btnUpdate, lblErr);
	}
	
	private void handleUpdateClick(ActionEvent e) {		
		DatePicker startDate = this.startDate.getDatePicker();
		DatePicker endDate = this.endDate.getDatePicker();
		LocalDate startVal = startDate.getValue();
		LocalDate endVal = endDate.getValue();
		Chart chart = this.chartLayout.getChart();
		
		if (endVal == null) {
			this.lblErr.setVisible(false);
			
			if (startVal == null) {
				chart.setTimeFrame(new TimeFrame(dataFile));
				startDate.setValue(fromDate(chart.getTimeFrame().getBegin()));
				endDate.setValue(fromDate(chart.getTimeFrame().getEnd()));
				return;
			}
						
			chart.setTimeFrame(new TimeFrame(
				dataFile, fromLocalDate(startVal)));
			
			startDate.setValue(fromDate(chart.getTimeFrame().getBegin()));
			endDate.setValue(fromDate(chart.getTimeFrame().getEnd()));
			return;
		}
		
		if (startVal == null) {
			this.lblErr.setVisible(false);
			chart.setTimeFrame(new TimeFrame(dataFile, null,
				fromLocalDate(endVal)));
			
			startDate.setValue(fromDate(chart.getTimeFrame().getBegin()));
			endDate.setValue(fromDate(chart.getTimeFrame().getEnd()));
			return;
		}
		
		this.lblErr.setVisible(false);
		chart.setTimeFrame(new TimeFrame(
			dataFile, fromLocalDate(startVal),
			fromLocalDate(endVal)));
		
		startDate.setValue(fromDate(chart.getTimeFrame().getBegin()));
		endDate.setValue(fromDate(chart.getTimeFrame().getEnd()));
	}
	
	private void handleSelectDataFileClick(ActionEvent e) {
		this.dataFile = fileChooser.showOpenDialog(
				this.getScene().getWindow());
		
		if (this.dataFile != null) {
			var name = dataFile.getName();
			name = name.split("\\.")[0];
			lblStock.setText(name);
		}
		
		btnUpdate.setDisable(this.dataFile == null);
	}
	
	private void handleStartDateChange(ActionEvent e) {
		DatePicker startDate = this.startDate.getDatePicker();
		DatePicker endDate = this.endDate.getDatePicker();
		
		if (endDate.getValue() == null) {
			this.chartLayout.getChart().setTimeFrame(new TimeFrame(
				dataFile, fromLocalDate(startDate.getValue())));
			return;
		}
		
		if (startDate.getValue().isAfter(endDate.getValue())) {
			this.lblErr.setText("Start date must not be after end " +
				"date!");
			
			this.lblErr.setVisible(true);
			return;
		}
		
		this.lblErr.setVisible(false);
		chartLayout.getChart().setTimeFrame(new TimeFrame(
			dataFile, fromLocalDate(startDate.getValue()),
			fromLocalDate(endDate.getValue())));
	}
	
	private void handleEndDateChange(ActionEvent e) {
		DatePicker startDate = this.startDate.getDatePicker();
		DatePicker endDate = this.endDate.getDatePicker();
		
		if (startDate.getValue() == null) {
			this.chartLayout.getChart().setTimeFrame(new TimeFrame(
				dataFile, null, fromLocalDate(endDate.getValue())));
			return;
		}
		
		if (startDate.getValue().isAfter(endDate.getValue())) {
			this.lblErr.setText("Start date must not be after end " +
					"date!");
				
			this.lblErr.setVisible(true);
			return;
		}
		
		this.lblErr.setVisible(false);
		chartLayout.getChart().setTimeFrame(new TimeFrame(
			dataFile, fromLocalDate(startDate.getValue()),
			fromLocalDate(endDate.getValue())));
	}
	
	private LocalDate fromDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault())
			.toLocalDate();
	}
	
	private Date fromLocalDate(LocalDate date) {
		return Date.from(date.atStartOfDay(
			ZoneId.systemDefault()).toInstant());
	}
}
