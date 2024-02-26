package com.example.proyecto.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class Calculadora extends Stage {

    private Scene escena;
    private VBox vContenedor;
    private GridPane gdpTeclado;
    private TextField txtPantalla;
    private Button[][] arBotones = new Button[4][4];
    private String[] arEtiquetas =
            {"7","8","9","/", "4","5","6","*"
                    ,"1","2","3","-","0",".","=","+"};
    private double num1, num2;
    private String operador;

    public Calculadora(){
        CrearUI();
        this.setTitle("Mi primer Calculadora :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI(){
        txtPantalla = new TextField("");
        txtPantalla.setEditable(false);
        gdpTeclado = new GridPane();
        CrearTeclado();
        vContenedor = new VBox(txtPantalla, gdpTeclado);
        vContenedor.setSpacing(5);
        escena = new Scene(vContenedor, 200, 200);
        escena.getStylesheets().add(getClass().getResource
                ("/Estilos/Calculadora.css").toString());
    }


    private void CrearTeclado(){
        int pos = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arBotones[i][j] = new Button(arEtiquetas[pos]+"");
                arBotones[i][j].setPrefSize(50,50);
                int finalPos = pos;
                arBotones[i][j].setOnAction(event -> botonPresionado
                        (arEtiquetas[finalPos]));
                gdpTeclado.add(arBotones[i][j],j,i);
                if (arEtiquetas[pos] == "+" || arEtiquetas[pos] == "*"
                        || arEtiquetas[pos] == "=" || arEtiquetas[pos] == "/"
                        || arEtiquetas[pos] == "-" )
                    arBotones[i][j].setId("color-operador");
                pos++;
            }

        }
    }

    private void botonPresionado(String valor) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        if (valor.equals(".") && txtPantalla.getText().contains(".")) {
            a.setTitle("Error de sintaxis");
            return;
        }
        switch (valor) {
            case "+":
            case "-":
            case "*":
            case "/":
                operador = valor;
                num1 = Double.parseDouble(txtPantalla.getText());
                txtPantalla.clear();
                break;
            case "=":
                if (!txtPantalla.getText().isEmpty()) {
                    num2 = Double.parseDouble(txtPantalla.getText());
                    double resultado = calcular();
                    txtPantalla.setText(String.valueOf(resultado));
                }
                break;
            default:
                txtPantalla.appendText(valor);
        }
    }

    private double calcular() {
        switch (operador) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0)
                    return num1 / num2;
                else {
                    mostrarError("No se puede dividir por cero.");
                    return 0;
                }
            default:
                return 0; // En caso de operador desconocido
        }
    }

    private void mostrarError(String s) {
        String mensaje = "";
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No se puede Dividir entre 0");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    private void setValue(String simbolo) {
        txtPantalla.appendText(simbolo+"");
    }
}
