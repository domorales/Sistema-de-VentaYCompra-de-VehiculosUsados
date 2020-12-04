module com.espol.aplicacion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires  java.datatransfer;
    requires  mail;
    requires javafx.base;
    requires javafx.graphics;
    

    opens com.espol.aplicacion to javafx.fxml;
    opens com.espol.controller to javafx.fxml;
    opens com.espol.model.datos to javafx.fxml;
    opens com.espol.model.utilidades to javafx.fxml;
    opens com.espol.model.vehiculos to javafx.fxml;
    exports com.espol.aplicacion;
    exports com.espol.controller;
    exports  com.espol.model.datos;
    exports com.espol.model.utilidades;
    exports com.espol.model.vehiculos;
   
    
}
