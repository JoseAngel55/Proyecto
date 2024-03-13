package com.example.proyecto.vistas;

import com.example.proyecto.componentes.ButtonCell;
import com.example.proyecto.modelos.EmpleadosDAO;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class EmpleadoTaqueria extends Stage {

    private Panel pnlPrincipal;

    private BorderPane bdpPrincipal;
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
        btnAgregarEmp.setOnAction(event -> new EmpleadosForm(tbvEmpleados, null));
        btnAgregarEmp.setPrefSize(50, 50);
        btnAgregarEmp.setGraphic(imvEmp);
        tlbMenu = new ToolBar(btnAgregarEmp);

        CrearTable();
        bdpPrincipal = new BorderPane();
        bdpPrincipal.setTop(tlbMenu);
        bdpPrincipal.setCenter(tbvEmpleados);
        pnlPrincipal = new Panel();
        pnlPrincipal.getStyleClass().add("panel-info");
        pnlPrincipal.setBody(bdpPrincipal);
        escena = new Scene(pnlPrincipal, 700,400);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
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
        TableColumn<EmpleadosDAO,String> tbcEditar = new TableColumn<EmpleadosDAO,String>("EDITAR");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<EmpleadosDAO, String>, TableCell<EmpleadosDAO, String>>() {
                    @Override
                    public TableCell<EmpleadosDAO, String> call(TableColumn<EmpleadosDAO, String> empleadosDAOStringTableColumn) {
                        return new ButtonCell(1);
                    }
                }
        );
        TableColumn<EmpleadosDAO,String> tbcEliminar = new TableColumn<EmpleadosDAO,String>("ELIMINAR");
        tbcEliminar.setCellFactory(
                new Callback<TableColumn<EmpleadosDAO, String>, TableCell<EmpleadosDAO, String>>() {
                    @Override
                    public TableCell<EmpleadosDAO, String> call(TableColumn<EmpleadosDAO, String> empleadosDAOStringTableColumn) {
                        return new ButtonCell(2);
                    }
                }
        );
        ///...
        tbvEmpleados.getColumns().addAll(tbcNomEmp,tbcrfcEmp,tbcSueldoEmp,tbcTelEmp,tbcDirEmp,tbcEditar,tbcEliminar);
        tbvEmpleados.setItems(objEmp.CONSULTAR());
    }
}
