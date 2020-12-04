package com.espol.aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        scene = new Scene(loadFXML("vistaLogin"));
        stage.setTitle("     Venta y Compra de vehiculos");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(true);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        Parent parent = loadFXML(fxml);
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
        stage.show();
    }

    public static void setRoot(FXMLLoader fxmlLoader) throws IOException {
        Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
        stage.show();
    }

    public static FXMLLoader loadFXMLoad(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
