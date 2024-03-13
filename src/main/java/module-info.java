module com.example.proyecto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyecto to javafx.fxml;
    exports com.example.proyecto;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;
    opens com.example.proyecto.modelos;

}