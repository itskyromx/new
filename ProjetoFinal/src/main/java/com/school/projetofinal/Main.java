package com.school.projetofinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Registro");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Settings.setPrimaryStage(primaryStage);
        Settings.listaPaciente();
        Settings.listaMedico();
        Settings.listaMedicamento();
        primaryStage.show();
    }
}