package com.example.coches_paula_nunes_conexao_mongodb.clases;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Metodos {
    //private MongoClient mongoClient;
    private static MongoClient con;
    private static MongoDatabase db;
    private static final String DATABASE_NAME = "coches";

    public static void conectarse() {
        try{
            con=Conexao_MongoDB.conectar();
            db= con.getDatabase("coches");

        } catch (Exception e) {
            System.out.println(e.getMessage()+"  --- Error");
        }

    }

    public static List<Coche> obtenerCoches() {
        Metodos.conectarse();
        List<Coche> coches = new ArrayList<>();
        Document documento = new Document();

        FindIterable findIterable = db.getCollection("coche").find(documento);

        Iterator<Document> iter = findIterable.iterator();
        while (iter.hasNext()) {
            Document doc = iter.next();
            Coche coche = new Coche();
            coche.setMatricula(doc.getString("matricula"));
            coche.setModelo(doc.getString("modelo"));
            coche.setMarca(doc.getString("marca"));
            coche.setTipo(doc.getString("tipo"));
            coches.add(coche);
        }
        return coches;
    }


}
