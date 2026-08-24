package ni.edu.uam.ejemplog1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private TextField display;
    private double primerNumero = 0;
    private String operador = "";
    private boolean inicioNuevoNumero = true;

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #ffe6f0;");

        display = new TextField("0");
        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setPrefHeight(60);
        display.setStyle(
                "-fx-background-color: #ffffff; " +
                        "-fx-font-size: 24px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill: #b30059; " +
                        "-fx-border-color: #ff99cc; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 8px; " +
                        "-fx-background-radius: 8px;"
        );

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        String[][] botones = {
                {"C", "±", "%", "/"},
                {"7", "8", "9", "*"},
                {"4", "5", "6", "-"},
                {"1", "2", "3", "+"},
                {"0", ".", "="}
        };

        for (int fila = 0; fila < botones.length; fila++) {
            for (int col = 0; col < botones[fila].length; col++) {
                String texto = botones[fila][col];
                Button btn = new Button(texto);
                btn.setPrefSize(60, 50);

                if (texto.equals("0")) {
                    btn.setPrefWidth(130);
                    grid.add(btn, 0, fila, 2, 1);
                } else if (texto.equals(".")) {
                    grid.add(btn, 2, fila);
                } else if (texto.equals("=")) {
                    grid.add(btn, 3, fila);
                } else {
                    grid.add(btn, col, fila);
                }

                if (texto.matches("[0-9]") || texto.equals(".")) {
                    btn.setStyle("-fx-background-color: #ffb3d9; -fx-text-fill: #660033; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 8px;");
                } else if (texto.matches("[+\\-*/=]")) {
                    btn.setStyle("-fx-background-color: #ff3385; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold; -fx-background-radius: 8px;");
                } else {
                    btn.setStyle("-fx-background-color: #ff80bf; -fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold; -fx-background-radius: 8px;");
                }

                btn.setOnAction(e -> procesarEntrada(texto));
            }
        }

        root.getChildren().addAll(display, grid);

        Scene scene = new Scene(root, 320, 420);
        stage.setTitle("Calculadora Rosa");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private void procesarEntrada(String valor) {
        if (valor.matches("[0-9]")) {
            if (inicioNuevoNumero || display.getText().equals("0")) {
                display.setText(valor);
                inicioNuevoNumero = false;
            } else {
                display.setText(display.getText() + valor);
            }
        } else if (valor.equals(".")) {
            if (inicioNuevoNumero) {
                display.setText("0.");
                inicioNuevoNumero = false;
            } else if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        } else if (valor.equals("C")) {
            display.setText("0");
            primerNumero = 0;
            operador = "";
            inicioNuevoNumero = true;
        } else if (valor.equals("±")) {
            double val = Double.parseDouble(display.getText()) * -1;
            mostrarResultado(val);
        } else if (valor.equals("%")) {
            double val = Double.parseDouble(display.getText()) / 100;
            mostrarResultado(val);
        } else if (valor.matches("[+\\-*/]")) {
            primerNumero = Double.parseDouble(display.getText());
            operador = valor;
            inicioNuevoNumero = true;
        } else if (valor.equals("=")) {
            if (!operador.isEmpty()) {
                double segundoNumero = Double.parseDouble(display.getText());
                double resultado = 0;

                switch (operador) {
                    case "+": resultado = primerNumero + segundoNumero; break;
                    case "-": resultado = primerNumero - segundoNumero; break;
                    case "*": resultado = primerNumero * segundoNumero; break;
                    case "/":
                        if (segundoNumero == 0) {
                            display.setText("Error");
                            operador = "";
                            inicioNuevoNumero = true;
                            return;
                        }
                        resultado = primerNumero / segundoNumero;
                        break;
                }
                mostrarResultado(resultado);
                operador = "";
                inicioNuevoNumero = true;
            }
        }
    }

    private void mostrarResultado(double res) {
        if (res == (long) res) {
            display.setText(String.format("%d", (long) res));
        } else {
            display.setText(String.valueOf(res));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}