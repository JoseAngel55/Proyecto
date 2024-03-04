package com.example.proyecto.vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.RandomAccessFile;

public class CuadroMagico extends Stage {

    private Scene escena;
    private HBox hbSup;
    private Label lbTamano;
    private TextField txtTamano, n;
    private Button btnCalcular;
    private GridPane gdpContenedor;
    private VBox vbContenedor;
    private String paramTeclado = "\\d*";
    private TextField[][] artxtField;
    private RandomAccessFile archivo;
    private int tamano;


    public CuadroMagico(){
        CrearUI();
        this.setTitle("Cuadro Magico");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        lbTamano = new Label("Tamaño del Cuadro");
        txtTamano = new TextField("");
        TextFormatter<String> textFormatter = new TextFormatter<>
                (change -> change.getControlNewText().matches(paramTeclado)?change : null);
        txtTamano.setTextFormatter(textFormatter);
        txtTamano.setPrefSize(60,40);
        btnCalcular = new Button("Calcular");
        hbSup = new HBox(lbTamano,txtTamano,btnCalcular);
        hbSup.setSpacing(20);
        gdpContenedor = new GridPane();
        gdpContenedor.setAlignment(Pos.CENTER);
        btnCalcular.setOnAction(event -> generarTablero());
        vbContenedor = new VBox(hbSup,gdpContenedor);
        escena = new Scene(vbContenedor,500,500);
        escena.getStylesheets().add(getClass().getResource("/Estilos/CuadroMagico.css").toString());
    }

    private void generarTablero() {
        tamano = Integer.parseInt(txtTamano.getText());
        if (tamano%2 > 0) {
            artxtField = new TextField[tamano][tamano];
            gdpContenedor.getChildren().clear();
            for (int fila = 0; fila < tamano; fila++) {
                for (int columna = 0; columna < tamano; columna++) {
                    artxtField[fila][columna] = new TextField();
                    artxtField[fila][columna].setPrefSize(60,60);
                    artxtField[fila][columna].addEventFilter(KeyEvent.KEY_TYPED, event -> {
                        event.consume();
                    });
                    gdpContenedor.add(artxtField[fila][columna], columna, fila);
                }
            }
            resolverCuadroMagico();
            this.show();
        }
            else if(tamano%2 == 0){
                mostrarError("\"El numero debe ser impar\"");
        }
    }

    private void mostrarError(String s) {
        String mensaje = "";
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("El numero debe ser impar");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    private void resolverCuadroMagico() {
        try {
            archivo = new RandomAccessFile("cuadromagico.txt", "rw");

            int numero = 1;
            int fila = 0;
            int col = tamano / 2;

            while (numero <= (tamano * tamano)) {
                archivo.writeInt(numero);
                artxtField[fila][col].setText(String.valueOf(numero));
                int nextFila = (fila - 1 + tamano) % tamano;
                int nextCol = (col + 1) % tamano;
                if (!artxtField[nextFila][nextCol].getText().isEmpty()) {
                    fila = (fila + 1) % tamano;
                } else {
                    fila = nextFila;
                    col = nextCol;
                }
                numero++;
            }

            archivo.close();
        } catch (Exception e) {
            mostrarError("Error al resolver el cuadro mágico.");
        }
    }

}


