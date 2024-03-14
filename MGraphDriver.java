package com.cs308gui.CS308AssignmentBoston;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;


public class MGraphDriver extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MGraphDriver.class.getResource("mapScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1150, 650);
        stage.setTitle("Route Finder");

        Controller control = fxmlLoader.getController();
        control.callGraph();
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) throws FileNotFoundException {
        launch();
    }

}


