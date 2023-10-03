package Personas;
import edu10.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class EliminarPersona {
    public static void eliminarPersona() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el código de la persona a eliminar:");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea en blanco después de nextInt()

        Connection conexion = ConexionBD.obtenerConexion();
        if (conexion != null) {
            String sql = "DELETE FROM personas WHERE codigo = ?";

            try {
                PreparedStatement statement = conexion.prepareStatement(sql);
                statement.setInt(1, codigo);

                int filasAfectadas = statement.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("Persona funada con éxito:)");
                } else {
                    System.out.println("No se pudo eliminar la persona.");
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