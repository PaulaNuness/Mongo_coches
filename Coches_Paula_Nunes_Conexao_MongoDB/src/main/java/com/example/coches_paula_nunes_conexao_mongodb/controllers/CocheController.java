package com.example.coches_paula_nunes_conexao_mongodb.controllers;

import com.example.coches_paula_nunes_conexao_mongodb.clases.Coche;
import com.example.coches_paula_nunes_conexao_mongodb.clases.Conexao_MongoDB;
import com.example.coches_paula_nunes_conexao_mongodb.clases.Metodos;
import com.mongodb.ErrorCategory;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.InsertOneOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import javafx.scene.control.*;
import org.bson.Document;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.conversions.Bson;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CocheController implements Initializable {

    @FXML
    private Button botton_limpiar;

    @FXML
    private ComboBox<String> combo_box_tipos;
    String[]tipos_de_coches={"SUB", "Monovolumen", "Hibrido", "Furgoneta"};

    @FXML
    private TableView<Coche> table_view_datos;

    @FXML
    private TableColumn coluna_matricula;

    @FXML
    private TableColumn coluna_marca;

    @FXML
    private TableColumn coluna_modelo;

    @FXML
    private TableColumn coluna_tipo;

    @FXML
    private TextField texto_matricula;

    @FXML
    private TextField texto_marca;

    @FXML
    private TextField texto_modelo;

    @FXML
    private Button botton_borrar;

    @FXML
    private Button botton_atualizar;

    @FXML
    private Button botton_insertar;
    private ObservableList<Coche> coches;

    private Coche cocheSeleccionado;

    MongoClient con;


    /**
     * metodo que verifica si tenemos una matricula en concreto en la base de datos
     * @param x
     * @return
     */

    public boolean VerificarMatricula(String x) {

        con = Conexao_MongoDB.conectar();
        MongoDatabase database = con.getDatabase("coches");
        MongoCollection<Document> collection = database.getCollection("coche");


        // Crie um filtro com base na matrícula
        Bson filter = Filters.eq("matricula", x);

        // Realize uma consulta para verificar se a matrícula existe
        FindIterable<Document> result = collection.find(filter);

        // Verifique se algum documento correspondente foi encontrado
        boolean existe = result.iterator().hasNext();

        return existe;
    }


    /**
     * atualiza datos del coche, hay que poner una matricula para que sea exejutado bien
     * @param event
     */
    @FXML
    void Atualizar(ActionEvent event) {
        con = Conexao_MongoDB.conectar();
        MongoDatabase database = con.getDatabase("coches");
        MongoCollection<Document> collection = database.getCollection("coche");

        // Define os critérios de atualização (neste exemplo, com base na matrícula)
        Bson filter = Filters.eq("matricula", texto_matricula.getText());

        // Crie um objeto Bson para conter todas as atualizações que você deseja aplicar
        Bson updates = Updates.combine(
                Updates.set("marca", texto_marca.getText()), // Atualiza a marca
                Updates.set("modelo", texto_modelo.getText()), // Atualiza o modelo
                Updates.set("tipo", combo_box_tipos.getSelectionModel().getSelectedItem()) // Atualiza o tipo
        );

        // Aplica a atualização
        UpdateResult result = collection.updateOne(filter, updates);

        // Verifica se a atualização foi bem-sucedida
        if (result.wasAcknowledged() && result.getModifiedCount() > 0 ) {
            System.out.println("Documento atualizado com sucesso.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmado!!!");
            alert.setContentText("Atualizado el coche");
            alert.show();
        }else if(texto_matricula.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR!!!");
            alert.setContentText("Perdona no hay registro en la matricula");
            alert.show();
        }
        else {
            System.out.println("Nenhum documento correspondente encontrado para atualizar.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR!!!");
            alert.setContentText("Perdona no has atualizado nada");
            alert.show();
        }
    }


    /**
     * metodo que borramos un dato de la base de datos
     * @param event
     */
    @FXML
    void Borrar(ActionEvent event) {
        con = Conexao_MongoDB.conectar();
        // Conecta-se à base de dados "coches"
        MongoDatabase database = con.getDatabase("coches");
        // Obtém a coleção "coche"
        MongoCollection<Document> collection = database.getCollection("coche");

        // Define os critérios de exclusão (neste exemplo, com base na matrícula)
        Bson filter = Filters.eq("matricula", texto_matricula.getText()); // Substitua 1 pelo valor desejado

        // Aplica a exclusão
        DeleteResult result = collection.deleteOne(filter);

        // Verifica se a exclusão foi bem-sucedida
        if (result.wasAcknowledged() && result.getDeletedCount() > 0) {//si ha borrado algo
            System.out.println("Documento excluído com sucesso.");
            Alert alert=new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
            alert.setTitle("Confirmado!!!");//com o titulo
            alert.setContentText("Borrado el coche");
            alert.show();
        } else {//se no ha borrado
            System.out.println("Nenhum documento correspondente encontrado para exclusão.");
            Alert alert=new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
            alert.setTitle("ERROR!!!");//com o titulo
            alert.setContentText("Perdona no hay registro en la matricula");
            alert.show();
        }

    }

    /**
     * metodo para insertar coches, el metodo hace unas confirmaciones
     * @param event
     */
    @FXML
    void Insertar(ActionEvent event) {
        if(texto_matricula.getText().isEmpty()){//si o texto matricula esta vacio
            System.out.println("Perdona no hay registro");
            Alert alert=new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
            alert.setTitle("ERROR!!!");//com o titulo
            alert.setContentText("Perdona no hay registro en la matricula");
            alert.show();
        }else if(VerificarMatricula(texto_matricula.getText())){//si la matricula ja tenemos en la base de datos
            System.out.println("Perdona esa matricula ya existe");
            Alert alert=new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
            alert.setTitle("ERROR!!!");//com o titulo
            alert.setContentText("Perdona esa matricula ya existe");
            alert.show();
        }
        else{//si no esta vacio y no hay la misma matricula en la base de datos, hago la inserçao
            con=Conexao_MongoDB.conectar();

            //La clase MongoDatabase nos ofrece el método getDatabase() que nos permite seleccionar la base de datos
            //con la que queremos trabajar
            // Me conecto a la BD "coches"
            MongoDatabase database= con.getDatabase("coches");
            Document mickeyMouse = new Document();
            //Me devuelve una coleccion(tabla) si no existe la crea
            MongoCollection<Document> collection = database.getCollection("coche");
            //crear un documento con los valores que se insertarán usando el método append()
            mickeyMouse.append("matricula",texto_matricula.getText())
                    .append("marca", texto_marca.getText())
                    .append("modelo",texto_modelo.getText())
                    .append("tipo", combo_box_tipos.getSelectionModel().getSelectedItem());

            collection.insertOne(mickeyMouse);

            System.out.println("Inserção bem-sucedida.");
            Alert alert=new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
            alert.setTitle("Confirmado!!!");//com o titulo
            alert.setContentText("Añadido el coche");
            alert.show();
        }

    }

    /**
     * el metodo limpia los datos que tenemos en los TextField
     * @param event
     */

    @FXML
    void Limpiar(ActionEvent event) {
        try{
            texto_marca.clear();
            texto_modelo.clear();
            texto_matricula.clear();
            combo_box_tipos.setValue(" ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * metodo pasra tener los datos de la base de datos
     * @param coche
     */
    private void cargarCoche(Coche coche) {
        texto_matricula.setText(coche.getMatricula());
        texto_marca.setText(coche.getMarca());
        texto_modelo.setText(coche.getModelo());
        combo_box_tipos.setValue(coche.getTipo());
    }

    /**
     * metodo para poder selecionar un dato que tengo en el TableView
     * @param event
     */
    @FXML
    void selecionar_coche(Event event) {
        cocheSeleccionado = table_view_datos.getSelectionModel().getSelectedItem();
        cargarCoche(cocheSeleccionado);
    }

    /**
     * metodo para inicializar la app
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //conexao
        Conexao_MongoDB.conectar();

        //poner o que tenho na base de datos dentro do tableview
        List<Coche> coches = Metodos.obtenerCoches();
        for(Coche e : coches){
            System.out.println(e);
        }
        table_view_datos.setItems(FXCollections.observableArrayList(coches).sorted());

        //coloco os itens dentro do combobox
        combo_box_tipos.getItems().addAll(tipos_de_coches);

        // Creo el observablelist
        coches = FXCollections.observableArrayList();

        // Asigno las columnas con los atributos del modelo
        this.coluna_marca.setCellValueFactory(new PropertyValueFactory("marca"));
        this.coluna_matricula.setCellValueFactory(new PropertyValueFactory("matricula"));
        this.coluna_modelo.setCellValueFactory(new PropertyValueFactory("modelo"));
        this.coluna_tipo.setCellValueFactory(new PropertyValueFactory("tipo"));


    }
}
