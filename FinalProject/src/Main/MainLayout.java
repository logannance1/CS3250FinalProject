package Main;
import chart.ChartLayout;
import data.DataForm;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MainLayout extends VBox {
	ChartLayout cl;
	DataForm df;
	
	public MainLayout() {
		cl = new ChartLayout();
		df = new DataForm(cl);
		
		VBox.setVgrow(cl, Priority.ALWAYS);
		this.getChildren().addAll(cl, new DataForm(cl));
	}
}
