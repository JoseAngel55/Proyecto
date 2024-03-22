package com.example.proyecto.vistas;

import com.example.proyecto.componentes.Hilo;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Pista extends Stage{

    private ProgressBar[] pgbcorredores = new ProgressBar[5];
    private Label[] lblCorredores = new Label[5];
    private GridPane gdpPista;
    private Scene escena;
    private String[] strCorredores = {"Cuco","Toño","Ruben","Mario","Robert"};
    private Hilo[] thrCorredores = new Hilo[5];

    public Pista(){
        CrearUI();
        this.setTitle("Pista de atletismo");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        gdpPista =  new GridPane();
        for (int i = 0; i < strCorredores.length; i++) {
            lblCorredores[i] =  new Label(strCorredores[i]);
            pgbcorredores[i] = new ProgressBar();
            gdpPista.add(lblCorredores[i],0,i);
            gdpPista.add(pgbcorredores[i],1,i);
            thrCorredores[i]= new Hilo(strCorredores[i]);
            thrCorredores[i].setPgbCarril(pgbcorredores[i]);
            thrCorredores[i].start();
        }
        escena = new Scene(gdpPista,200,200);
    }
}
