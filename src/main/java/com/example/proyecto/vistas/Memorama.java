package com.example.proyecto.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        crearTablero();

        hInferior = new HBox(gdpTablero, vMarcador);
        contenedor = new VBox(hSuperior, hInferior);
        contenedor.setSpacing(5);
        scMem = new Scene(contenedor,500, 350);
    }

    private void crearTablero() {
        String[] arImagenes = {"astronauta.jpg", "Hoja.jpg", "paisaje.jpg", "Sol.jpg"};
        Button[][] arBtnCartas = new Button[2][4];
        ImageView imvCarta;
        int posx = 0;
        int posy = 0;
        int cont = 0;
        for (int i = 0; i < arImagenes.length; ) {
            posx = (int)(Math.random()*2);
            posy = (int)(Math.random()*4);
            if(arBtnCartas[posx][posy] == null){
                arBtnCartas[posx][posy] = new Button();
                imvCarta = new ImageView(getClass().getResource("/Images/"+arImagenes[i]).toString());
                imvCarta.setFitHeight(150);
                imvCarta.setFitWidth(100);
                arBtnCartas[posx][posy].setGraphic(imvCarta);
                arBtnCartas[posx][posy].setPrefSize(100,150);
                gdpTablero.add(arBtnCartas[posx][posy], posy, posx);
                cont++;
                if (cont == 2) {
                    i++;
                    cont = 0;
                }
            }
        }
    }

}
