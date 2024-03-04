package com.example.proyecto.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class EmpleadosDAO {
    int idEmpleado;
    String numEmpleado;
    String rfcEmpleado;
    float salario;
    String telefono;
    String direccion;

    public void INSERTAR(){
        String query = "INSERT INTO Empleado(numEmleado," +
                "rfcEmpleado,salario,telefono,direccion) " +
                "VALUES('"+numEmpleado+"','"+rfcEmpleado+"',"+salario+",'"+telefono+"','"+direccion+"')";
        try {
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void ACTUALIZAR(){
        String query = "UPDATE Empleado SET numEmpleado='"+numEmpleado+"'," +
                "rfcEmpleado='"+rfcEmpleado+"',salario="+salario+"," +
                "telefono='"+telefono+"',direccion='"+direccion+"' " +
                "WHERE idEmpleado="+idEmpleado;
        try {
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void ELIMINAR(){
        String query = "DELETE FROM Empleado WHERE idEmpleado="+idEmpleado;
        try {
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<EmpleadosDAO> CONSULTAR(){
        ObservableList<EmpleadosDAO> listaEmp = FXCollections.observableArrayList();
        String query = "SELECT * FROM Empleado";
        try {
            EmpleadosDAO objEmp;
            Statement stmt = conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()){
                objEmp = new EmpleadosDAO();
                objEmp.idEmpleado = res.getInt("idEmpleado");
                objEmp.numEmpleado = res.getString("numEmpleado");
                objEmp.rfcEmpleado = res.getString("rfcEmpleado");
                objEmp.salario = res.getFloat("salario");
                objEmp.telefono = res.getString("telefono");
                objEmp.direccion = res.getString("direccion");
                listaEmp.add(objEmp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listaEmp;
    }

}
