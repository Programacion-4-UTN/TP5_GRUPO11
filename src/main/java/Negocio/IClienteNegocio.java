package Negocio;

import java.util.List;

import Entidades.Cliente;

public interface IClienteNegocio {
    boolean agregarCliente(Cliente cliente);
    List<Cliente> obtenerTodos();
}
