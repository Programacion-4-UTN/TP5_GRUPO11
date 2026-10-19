package Negocio;

import java.util.List;

import Dao.ClienteDao;
import Dao.IClienteDao;
import Entidades.Cliente;

public class ClienteNegocio implements IClienteNegocio {
    
    // instancia de DAO para comunicar con la bd
    private ClienteDao clienteDao = new ClienteDao();

    @Override
    public boolean agregarCliente(Cliente cliente) {
        return clienteDao.insertarCliente(cliente);
    }

    @Override
    public List<Cliente> obtenerTodos() {
        return clienteDao.listarClientes();
    }
    
    @Override
    public boolean eliminarCliente(int id) {
        return clienteDao.eliminarCliente(id);
    }
}