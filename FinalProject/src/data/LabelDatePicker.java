package data;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LabelDatePicker extends VBox {
	private Label label;
	private DatePicker datePicker;
	
	public LabelDatePicker(String title) {
		label = new Label(title);
		datePicker = new DatePicker();
		this.getChildren().addAll(label, datePicker);
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public DatePicker getDatePicker() {
		return datePicker;
	}

	public void setDatePicker(DatePicker datePicker) {
		this.datePicker = datePicker;
	}
}
