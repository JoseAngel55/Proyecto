package com.example.proyecto;

import com.example.proyecto.modelos.conexion;
import com.example.proyecto.vistas.Calculadora;
import com.example.proyecto.vistas.CuadroMagico;
import com.example.proyecto.vistas.Memorama;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private MenuBar mnbPrincipal;
    private Menu menParcial1, menParcial2, menSalir;
    private MenuItem mitCalculadora, mitSalir, mitMemorama, mitCuadroMagico;
    private BorderPane bdpPanel;

    @Override
    public void start(Stage stage) throws IOException {
        CrearMenu();
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        bdpPanel = new BorderPane();
        bdpPanel.setTop(mnbPrincipal);
        Scene scene = new Scene(bdpPanel);
        scene.getStylesheets().add(getClass().getResource("/Estilos/main.css").toString());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
        conexion.crearConexion();
        //stage.show();

        //new Calculadora();
    }

    private void CrearMenu() {
        /* Menu primer parcial*/
        mitCalculadora = new MenuItem("Calculadora");
        mitCalculadora.setOnAction(event -> new Calculadora());
        mitMemorama = new MenuItem("Memorama");
        mitMemorama.setOnAction(event -> new Memorama());
        mitCuadroMagico = new MenuItem("Cuadro Mágico");
        mitCuadroMagico.setOnAction(event -> new CuadroMagico());
        menParcial1 = new Menu("Primer Parcial");
        menParcial1.getItems().addAll(mitCalculadora, mitMemorama, mitCuadroMagico);

        /* Menu Segundo parcial*/
        menParcial2 = new Menu("Segundo Parcial");

        /* Menu salir*/
        mitSalir = new MenuItem("Salir");
        menSalir = new Menu("Salir");
        menSalir.getItems().add(mitSalir);
        menSalir.setOnAction(event -> System.exit(0));

        mnbPrincipal = new MenuBar();
        mnbPrincipal.getMenus().addAll(menParcial1, menParcial2, menSalir);
    }

    public static void main(String[] args) {
        launch();
    }
}