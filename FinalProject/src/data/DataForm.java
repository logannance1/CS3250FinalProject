package data;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import chart.ChartLayout;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;

public class DataForm extends VBox {
	private DirectoryChooser dirChooser;
	private ComboBox<String> stocks;
	private DatePicker startDate;
	private DatePicker endDate;
	private Button btnUpdate;
	
	/**
	 * Creates a form which allows the user to input a csv file path
	 * along with start and end dates in order to update a chart
	 * @param chartLayout
	 */
	public DataForm(ChartLayout chartLayout) {
		dirChooser = new DirectoryChooser();
		dirChooser.setTitle("Data File Directory");
		
		stocks = loadStocks();
		startDate = new DatePicker();
		endDate = new DatePicker();
		
		this.setPadding(new Insets(8));
		this.getChildren().addAll(stocks, startDate);
		
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
		
		btnUpdate = new Button("Update");
		
		btnUpdate.setOnAction(e -> {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			try {				
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
	
	private ComboBox<String> loadStocks() {
		ComboBox<String> res = new ComboBox<String>();
		
		try (DirectoryStream<Path> paths =
			Files.newDirectoryStream(Paths.get("data")))
		{
			for (Path path : paths) {
				if (Files.isRegularFile(path)) {
					String name = path.getFileName().toString();
					name = name.split("\\.")[0];
					
					res.getItems().add(name);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
}
