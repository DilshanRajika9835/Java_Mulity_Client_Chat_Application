import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MessageDashBoardMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/LoginForm.fxml"))));
        primaryStage.setResizable(false);
        primaryStage.resizableProperty().setValue(false);
        primaryStage.setTitle("Welcome!");
        primaryStage.getIcons().add(new Image("/assert/icon/whatsapp.png"));
        primaryStage.show();
    }
}
