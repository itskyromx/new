module com.school.projetofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.school.projetofinal to javafx.fxml;
    exports com.school.projetofinal;
}