package Dao;

import Entidades.Usuario;

public interface IUsuarioDao {
    Usuario obtenerUsuario(String user, String pass);
}