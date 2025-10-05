import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Candlestick {
	private FinanceData data;

	public Candlestick(FinanceData data) {
		this.data = data;
	}

	public FinanceData getData() {
		return data;
	}

	public void setData(FinanceData data) {
		this.data = data;
	}
}
