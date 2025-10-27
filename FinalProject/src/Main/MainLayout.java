package Main;
import chart.ChartLayout;
import data.DataForm;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MainLayout extends VBox {
	public MainLayout() {
		ChartLayout cl = new ChartLayout();
		VBox.setVgrow(cl, Priority.ALWAYS);
		this.getChildren().add(cl);
		this.getChildren().add(new DataForm(cl.getChart()));
	}
}
