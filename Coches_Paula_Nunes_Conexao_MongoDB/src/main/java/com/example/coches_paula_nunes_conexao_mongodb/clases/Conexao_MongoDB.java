package com.example.coches_paula_nunes_conexao_mongodb.clases;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;


public class Conexao_MongoDB {

    private  MongoDatabase db;
    private static final String DATABASE_NAME = "coches";

    /**
     *
     * @return
     */
    public static MongoClient conectar() {

        try {

            final MongoClient conexion = new MongoClient(new MongoClientURI("mongodb://root:password@localhost:27017/?authSource=admin"));

            System.out.println("Conectada correctamente a la BD");

            MongoDatabase database= conexion.getDatabase("coches");

            return conexion;
        }
        catch (Exception e) {
            System.out.println("Conexion Fallida");
            System.out.println(e);
            return null;
        }
    }

    /**
     *
     * @param con
     */
    public static void desconectar(MongoClient con)
    {

        con.close();
    }


}
