package com.example.coches_paula_nunes_conexao_mongodb.clases;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class R {

    public static InputStream getImage(String name) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("images" + File.separator + name);
    }

    /**
     * metodo para obtener as informacoes que tenho no fichero pizzeria.properties
     * @param name
     * @return
     */
    public static InputStream getProperties(String name) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(name );
    }

    public static URL getUI(String name) {
        return Thread.currentThread().getContextClassLoader().getResource("ui" + File.separator + name);
    }
}
