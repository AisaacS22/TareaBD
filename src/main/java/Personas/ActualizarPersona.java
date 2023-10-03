package Personas;


import edu10.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ActualizarPersona {
    public static void actualizarPersona() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el código de la persona a actualizar:");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea en blanco después de nextInt()

        // Primero, consultamos la persona para mostrar sus datos actuales al usuario
        Connection conexion = ConexionBD.obtenerConexion();
        if (conexion != null) {
            String sql = "SELECT * FROM personas WHERE codigo = ?";

            try {
                PreparedStatement statement = conexion.prepareStatement(sql);
                statement.setInt(1, codigo);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String nombreApellido = resultSet.getString("nombre_apellido");
                    Date fechaRegistro = resultSet.getDate("fecha_registro");
                    double sueldoBase = resultSet.getDouble("sueldo_base");
                    String sexo = resultSet.getString("sexo");
                    int edad = resultSet.getInt("edad");
                    double latitud = resultSet.getDouble("latitud");
                    double longitud = resultSet.getDouble("longitud");
                    String comentarios = resultSet.getString("comentarios");

                    System.out.println("Datos actuales de la persona:");
                    System.out.println("Nombre y Apellido: " + nombreApellido);
                    System.out.println("Fecha de Registro: " + fechaRegistro);
                    System.out.println("Sueldo Base: " + sueldoBase);
                    System.out.println("Sexo: " + sexo);
                    System.out.println("Edad: " + edad);
                    System.out.println("Latitud: " + latitud);
                    System.out.println("Longitud: " + longitud);
                    System.out.println("Comentarios: " + comentarios);

                    System.out.println("Ingrese los nuevos datos:");

                    System.out.println("Nuevo nombre y apellido:");
                    nombreApellido = scanner.nextLine();

                    System.out.println("Nueva fecha de registro (yyyy-MM-dd):");
                    String fechaRegistroStr = scanner.nextLine();
                    Date nuevaFechaRegistro = null;
                    try {
                        nuevaFechaRegistro = new SimpleDateFormat("yyyy-MM-dd").parse(fechaRegistroStr);
                    } catch (ParseException e) {
                        System.out.println("Formato de fecha incorrecto.");
                        return;
                    }

                    System.out.println("Nuevo sueldo base:");
                    double nuevoSueldoBase = scanner.nextDouble();
                    scanner.nextLine(); // Consumir la línea en blanco después de nextDouble()

                    System.out.println("Nuevo sexo (M/F):");
                    String nuevoSexo = scanner.nextLine().toUpperCase();

                    System.out.println("Nueva edad:");
                    int nuevaEdad = scanner.nextInt();
                    scanner.nextLine(); // Consumir la línea en blanco después de nextInt()

                    System.out.println("Nueva latitud de su casa:");
                    double nuevaLatitud = scanner.nextDouble();
                    scanner.nextLine(); // Consumir la línea en blanco después de nextDouble()

                    System.out.println("Nueva longitud de su casa:");
                    double nuevaLongitud = scanner.nextDouble();
                    scanner.nextLine(); // Consumir la línea en blanco después de nextDouble()

                    System.out.println("Nuevos comentarios:");
                    String nuevosComentarios = scanner.nextLine();

                    // Realizar la actualización en la base de datos
                    sql = "UPDATE personas SET nombre_apellido = ?, fecha_registro = ?, sueldo_base = ?, " +
                            "sexo = ?, edad = ?, latitud = ?, longitud = ?, comentarios = ? WHERE codigo = ?";
                    statement = conexion.prepareStatement(sql);
                    statement.setString(1, nombreApellido);
                    statement.setDate(2, new java.sql.Date(nuevaFechaRegistro.getTime()));
                    statement.setDouble(3, nuevoSueldoBase);
                    statement.setString(4, nuevoSexo);
                    statement.setInt(5, nuevaEdad);
                    statement.setDouble(6, nuevaLatitud);
                    statement.setDouble(7, nuevaLongitud);
                    statement.setString(8, nuevosComentarios);
                    statement.setInt(9, codigo);

                    int filasAfectadas = statement.executeUpdate();
                    if (filasAfectadas > 0) {
                        System.out.println("Persona actualizada con éxito.");
                    } else {
                        System.out.println("No se pudo actualizar la persona.");
                    }
                } else {
                    System.out.println("No se encontró ninguna persona con ese código.");
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