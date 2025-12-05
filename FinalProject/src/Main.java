
import Main.MainLayout;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Creates a scene with a MainLayout and default sizing
		Scene scene = new Scene(new MainLayout(), 800, 600);
		scene.getStylesheets().add("styles/style.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle("Stock Price History");
		primaryStage.show();
	}
}
