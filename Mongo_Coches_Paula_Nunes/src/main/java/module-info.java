module com.example.mongo_coches {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
            
    opens com.example.mongo_coches to javafx.fxml;
    exports com.example.mongo_coches;
}