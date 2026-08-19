module ni.edu.uam.primerproyectojavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.primerproyectojavafx to javafx.fxml;
    exports ni.edu.uam.primerproyectojavafx;
}