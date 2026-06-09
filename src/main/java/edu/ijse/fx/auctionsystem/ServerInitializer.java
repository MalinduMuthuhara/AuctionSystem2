package edu.ijse.fx.auctionsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ServerInitializer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ServerInitializer.class.getResource("ServerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Hello I Am Server !");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        Application.launch(ServerInitializer.class, args);
    }
}
