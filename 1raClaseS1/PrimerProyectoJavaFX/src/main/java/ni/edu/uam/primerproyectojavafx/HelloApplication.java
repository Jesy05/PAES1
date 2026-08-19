package ni.edu.uam.primerproyectojavafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setSpacing(10);

        Label lblTitulo = new Label("Bienvenido a primer programa de escritorio");
        root.getChildren().add(lblTitulo);

        Label lblName = new Label("Dime tu nombre");
        root.getChildren().add(lblName);

        TextField tfName = new TextField();
        root.getChildren().add(tfName);

        Button btnGuardar = new Button("Guardar");
        root.getChildren().add(btnGuardar);

        Scene scene = new Scene(root, 400, 250);
        stage.setTitle("Universidad Americana");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}