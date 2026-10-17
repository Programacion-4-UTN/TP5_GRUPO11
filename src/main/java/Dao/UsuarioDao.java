package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Entidades.Usuario;

public class UsuarioDao implements IUsuarioDao {

    @Override
    public Usuario obtenerUsuario(String user, String pass) {
        Usuario usuario = null;
        Connection cn = Conexion.obtenerConexion();
        PreparedStatement pst = null;
        ResultSet rs = null;

        // Asumiendo tabla 'usuarios' con campos: idUsuario, usuario, contrasena
        String query = "SELECT idUsuario, usuario, contrasena FROM usuarios WHERE usuario = ? AND contrasena = ?";

        try {
            if (cn != null) {
                pst = cn.prepareStatement(query);
                pst.setString(1, user);
                pst.setString(2, pass);
                rs = pst.executeQuery();

                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setContrasena(rs.getString("contrasena"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en obtenerUsuario: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                Conexion.cerrarConexion(cn);
            } catch (SQLException e) {
                System.out.println("Error al liberar recursos: " + e.getMessage());
            }
        }
        return usuario;
    }
}