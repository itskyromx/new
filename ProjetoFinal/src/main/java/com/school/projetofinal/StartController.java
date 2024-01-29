package com.school.projetofinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StartController {
    @FXML
    private TextField TextField;
    @FXML
    private Button btnEnter;

    int idade;
    public void verifyAge(ActionEvent event) throws Exception{
        try{
            if (idade < 18){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Tu és menor de idade!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void enter(ActionEvent ActionEvent) throws Exception {
        idade = Integer.parseInt(TextField.getText());
        if (idade < 18){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Tu és menor de idade!");
            errorAlert.setContentText("Vem acompanhado de um maior de idade");
            errorAlert.showAndWait();
        }
        else{
            Parent scene = FXMLLoader.load(getClass().getResource("principal.fxml"));
            Stage entrar = new Stage();
            entrar.initOwner(Settings.getPrimaryStage());
            entrar.initModality(Modality.WINDOW_MODAL);
            entrar.setScene(new Scene(scene));
            Window window = btnEnter.getScene().getWindow();
            window.hide();
            entrar.show();
        }
    }
}
