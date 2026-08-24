module ni.edu.uam.ejemplog1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ni.edu.uam.ejemplog1 to javafx.fxml;
    exports ni.edu.uam.ejemplog1;
}