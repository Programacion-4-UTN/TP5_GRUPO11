package Negocio;

import Entidades.Usuario;

public interface IUsuarioNegocio {

    /**
     * Devuelve el usuario si las credenciales son validas, o null si no existe
     * un usuario registrado con ese usuario y contrasena.
     */
    Usuario validarLogin(String usuario, String contrasena);
}