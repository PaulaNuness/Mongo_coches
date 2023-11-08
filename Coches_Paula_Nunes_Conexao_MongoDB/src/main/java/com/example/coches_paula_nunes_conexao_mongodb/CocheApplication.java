package com.example.coches_paula_nunes_conexao_mongodb;

import com.example.coches_paula_nunes_conexao_mongodb.clases.Coche;
import com.example.coches_paula_nunes_conexao_mongodb.clases.Conexao_MongoDB;
import com.example.coches_paula_nunes_conexao_mongodb.clases.Metodos;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CocheApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CocheApplication.class.getResource("coche.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}