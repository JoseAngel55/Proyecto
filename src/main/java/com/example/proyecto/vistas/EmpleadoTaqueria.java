package com.example.proyecto.vistas;

import com.example.proyecto.modelos.EmpleadosDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmpleadoTaqueria extends Stage {

    private VBox vbxPrincipal;
    private ToolBar tlbMenu;
    private Scene escena;
    private TableView<EmpleadosDAO> tbvEmpleados;
    private Button btnAgregarEmp;
    public EmpleadoTaqueria(){
        CrearUI();
        this.setTitle("Taqueria Don Cuco :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        ImageView imvEmp = new ImageView(getClass().getResource("/Images/Employye.png").toString());
        imvEmp.setFitWidth(50);
        imvEmp.setFitHeight(50);
        btnAgregarEmp = new Button();
        btnAgregarEmp.setPrefSize(50, 50);
        btnAgregarEmp.setGraphic(imvEmp);
        tlbMenu = new ToolBar(btnAgregarEmp);

        CrearTable();
        vbxPrincipal = new VBox(tlbMenu,tbvEmpleados);
        escena = new Scene(vbxPrincipal, 700,400);
    }

    private void CrearTable() {
        EmpleadosDAO objEmp = new EmpleadosDAO();
        tbvEmpleados = new TableView<EmpleadosDAO>();
        TableColumn<EmpleadosDAO, String> tbcNomEmp = new TableColumn<>("Empleado");
        tbcNomEmp.setCellValueFactory(new PropertyValueFactory<>("numEmpleado"));
        TableColumn<EmpleadosDAO, String> tbcrfcEmp = new TableColumn<>("RFC");
        tbcrfcEmp.setCellValueFactory(new PropertyValueFactory<>("rfcEmpleado"));
        TableColumn<EmpleadosDAO, Float> tbcSueldoEmp = new TableColumn<>("Sueldo");
        tbcSueldoEmp.setCellValueFactory(new PropertyValueFactory<>("salario"));
        TableColumn<EmpleadosDAO, String> tbcTelEmp = new TableColumn<>("Telefono");
        tbcTelEmp.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        TableColumn<EmpleadosDAO, String> tbcDirEmp = new TableColumn<>("Direccion");
        tbcDirEmp.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        ///...
        tbvEmpleados.getColumns().addAll(tbcNomEmp,tbcrfcEmp,tbcSueldoEmp,tbcTelEmp,tbcDirEmp);
        tbvEmpleados.setItems(objEmp.CONSULTAR());
    }
}
