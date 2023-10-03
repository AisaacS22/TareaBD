package Personas;


import edu10.ConexionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ConsultarPersonas {
    public static void consultarPersonas() {
        Connection conexion = ConexionBD.obtenerConexion();
        if (conexion != null) {
            String sql = "SELECT * FROM personas";

            try {
                Statement statement = conexion.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    int codigo = resultSet.getInt("codigo");
                    String nombreApellido = resultSet.getString("nombre_apellido");
                    Date fechaRegistro = resultSet.getDate("fecha_registro");
                    double sueldoBase = resultSet.getDouble("sueldo_base");
                    String sexo = resultSet.getString("sexo");
                    int edad = resultSet.getInt("edad");
                    double latitud = resultSet.getDouble("latitud");
                    double longitud = resultSet.getDouble("longitud");
                    String comentarios = resultSet.getString("comentarios");

                    System.out.println("Código: " + codigo);
                    System.out.println("Nombre y Apellido: " + nombreApellido);
                    System.out.println("Fecha de Registro: " + fechaRegistro);
                    System.out.println("Sueldo Base: " + sueldoBase);
                    System.out.println("Sexo: " + sexo);
                    System.out.println("Edad: " + edad);
                    System.out.println("Latitud: " + latitud);
                    System.out.println("Longitud: " + longitud);
                    System.out.println("Comentarios: " + comentarios);
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No se pudo establecer la conexión a la base de datos.");
        }
    }
}