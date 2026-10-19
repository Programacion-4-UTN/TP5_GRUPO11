package Dao;

import java.util.List;
import Entidades.Cliente;

public interface IClienteDao {
    boolean insertarCliente(Cliente c);
    List<Cliente> listarClientes();
    boolean eliminarCliente(int id);
}