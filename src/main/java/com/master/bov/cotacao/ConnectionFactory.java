/**
 * 
 */
package com.master.bov.cotacao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Vilmar.Ricken
 * 
 */
public class ConnectionFactory {

    /**
     * 
     */
    public static Connection getConnection() {
        try {
            /*
            final String url = "jdbc:postgresql://des101:5432/forms13";
            final String user = "postgres";
            final String password = "111111";
            System.out.println(Class.forName(Driver.class.getName()));
            */
            final String url = "jdbc:jtds:sqlserver://127.0.0.1:143/bovespa";
            final String user = "Mazinho";
            final String password = "821340";
            System.out.println(Class.forName("net.sourceforge.jtds.jdbc.Driver"));
            return DriverManager.getConnection(url, user, password);
        } catch (final Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
