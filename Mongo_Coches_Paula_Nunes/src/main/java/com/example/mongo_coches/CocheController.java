package com.example.mongo_coches;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CocheController {

    @FXML
    private TextField texto_matricula;

    @FXML
    private TextField texto_marca;

    @FXML
    private TextField texto_modelo;

    @FXML
    private ComboBox<?> comobo_box_tipo;

    @FXML
    private Button botton_borrar;

    @FXML
    private Button botton_insertar;

    @FXML
    private Button botton_limpiar;

    @FXML
    private Button botton_actualizar;

    @FXML
    private TableView<?> table_view_datos;

    @FXML
    private TableColumn<?, ?> coluna_matricula;

    @FXML
    private TableColumn<?, ?> coluna_marca;

    @FXML
    private TableColumn<?, ?> coluna_modelo;

    @FXML
    private TableColumn<?, ?> coluna_tipo;

    @FXML
    void Atualizar(ActionEvent event) {

    }

    @FXML
    void Borrar(ActionEvent event) {

    }

    @FXML
    void Insertar(ActionEvent event) {

    }

    @FXML
    void Limpiar(ActionEvent event) {
        try{
            texto_matricula.clear();
            texto_marca.clear();
            texto_modelo.clear();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
