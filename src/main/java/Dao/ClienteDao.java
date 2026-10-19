package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Entidades.Cliente;

public class ClienteDao implements IClienteDao {

    @Override
    public boolean insertarCliente(Cliente c) {
        String query = "INSERT INTO clientes (dni, cuil, nombre, apellido, sexo, fecha_nacimiento, direccion, nacionalidad, localidad, provincia, correo_electronico, telefono) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection cn = null;
        PreparedStatement pst = null;
        boolean estado = false;

        try {
            cn = Conexion.obtenerConexion();
            if (cn != null) {
                pst = cn.prepareStatement(query);
                pst.setString(1, c.getDni());
                pst.setString(2, c.getCuil());
                pst.setString(3, c.getNombre());
                pst.setString(4, c.getApellido());
                pst.setString(5, c.getSexo());
                pst.setString(6, c.getFechaNacimiento());
                pst.setString(7, c.getDireccion());
                pst.setString(8, c.getNacionalidad());
                pst.setString(9, c.getLocalidad());
                pst.setString(10, c.getProvincia());
                pst.setString(11, c.getCorreoElectronico());
                pst.setString(12, c.getTelefono());

                if (pst.executeUpdate() > 0) {
                    estado = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en insertarCliente: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Conexion.cerrarConexion(cn);
        }
        return estado;
    }

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String query = "SELECT id_cliente, dni, cuil, nombre, apellido, sexo, fecha_nacimiento, direccion, nacionalidad, localidad, provincia, correo_electronico, telefono FROM clientes";

        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = Conexion.obtenerConexion();
            if (cn != null) {
                pst = cn.prepareStatement(query);
                rs = pst.executeQuery();

                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setIdCliente(rs.getInt("id_cliente"));
                    c.setDni(rs.getString("dni"));
                    c.setCuil(rs.getString("cuil"));
                    c.setNombre(rs.getString("nombre"));
                    c.setApellido(rs.getString("apellido"));
                    c.setSexo(rs.getString("sexo"));
                    c.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                    c.setDireccion(rs.getString("direccion"));
                    c.setNacionalidad(rs.getString("nacionalidad"));
                    c.setLocalidad(rs.getString("localidad"));
                    c.setProvincia(rs.getString("provincia"));
                    c.setCorreoElectronico(rs.getString("correo_electronico"));
                    c.setTelefono(rs.getString("telefono"));

                    lista.add(c);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en listarClientes: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Conexion.cerrarConexion(cn);
        }
        return lista;
    }
    
    @Override
    public boolean eliminarCliente(int id) {
        boolean filasAfectadas = false;
        String query = "DELETE FROM clientes WHERE id_cliente = ?";
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = Conexion.obtenerConexion();
            if (cn != null) {
                pst = cn.prepareStatement(query);
                pst.setInt(1, id);
                if (pst.executeUpdate() > 0) {
                    filasAfectadas = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en eliminarCliente: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Conexion.cerrarConexion(cn);
        }
        return filasAfectadas;
    }
}