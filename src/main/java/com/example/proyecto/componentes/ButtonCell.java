package com.example.proyecto.componentes;

import com.example.proyecto.modelos.EmpleadosDAO;
import com.example.proyecto.vistas.EmpleadosForm;
import javafx.scene.control.*;

import java.util.Optional;

public class ButtonCell extends TableCell<EmpleadosDAO, String> {
    Button btnCelda;
    int opc;
    EmpleadosDAO objEmp;

    public ButtonCell(int opc){
        this.opc = opc;
        String txtButton = (opc == 1) ? "Editar" : "Eliminar";
        btnCelda = new Button(txtButton);
        btnCelda.setOnAction(event -> accionBoton(opc));
    }

    private void accionBoton(int opc) {
        TableView<EmpleadosDAO> tbvEmpleados = ButtonCell.this.getTableView();
        objEmp = tbvEmpleados.getItems().get(ButtonCell.this.getIndex());
        if (opc == 1) {
            //Codigo Editar
            new EmpleadosForm(tbvEmpleados, objEmp);

        }else {
            //Codigo Eliminar
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("Confirmacion de accion");
            alert.setContentText("¿Deseas borrar el Empleado:" + objEmp.getNumEmpleado() +"?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                objEmp.ELIMINAR();
                tbvEmpleados.setItems(objEmp.CONSULTAR());
                tbvEmpleados.refresh();
            }
        }
    }

    @Override
    protected void updateItem(String item, boolean empty){
        super.updateItem(item, empty);
        if (!empty)
            this.setGraphic(btnCelda);
    }
}
