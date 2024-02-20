package com.example.proyecto.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Memorama extends Stage {
    private Scene scMem;
    private HBox hInferior, hSuperior, hJ1, hJ2;
    private VBox  vMarcador, contenedor;
    private GridPane gdpTablero;
    private Label lNPares, timer, j1, j2, marcadorJ1, marcadorJ2;
    private TextField nPares;
    private Button revolver;


    public Memorama(){
        CrearUI();
        this.setTitle("Memorama de Topicos");
        this.setScene(scMem);
        this.show();
    }

    private void CrearUI() {
        lNPares = new Label("No. Pares");
        nPares = new TextField("");
        revolver = new Button("Revolver");
        timer = new Label("00:00");
        j1 = new Label("Jugador 1");
        j2 = new Label("Jugador 2");
        marcadorJ1 = new Label("  0");
        marcadorJ2 = new Label("  0");
        hSuperior = new HBox(lNPares, nPares, revolver, timer);
        hSuperior.setSpacing(35);
        hJ1 = new HBox(j1, marcadorJ1);
        hJ2 = new HBox(j2, marcadorJ2);
        vMarcador = new VBox(hJ1, hJ2);
        vMarcador.setSpacing(20);
        gdpTablero = new GridPane();
        //crearTablero();

        hInferior = new HBox(gdpTablero, vMarcador);
        contenedor = new VBox(hSuperior, hInferior);
        contenedor.setSpacing(5);
        scMem = new Scene(contenedor,500, 350);
    }

    private void crearTablero() {
    }

}
