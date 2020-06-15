package com.ascencio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private Connection conexion;

    public void conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection("jdbc:postgresql://65.52.36.198:5432/db_coronavirus", "postgres", "mysecretpassword");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en método de conexion :v" + e);
        }
    }

    public void cerrar() throws SQLException {
        if (conexion != null) {
            if (conexion.isClosed() == false) {
                conexion.close();
            }
        }
    }

    public Connection getConexion() {
        return conexion;
    }
}
