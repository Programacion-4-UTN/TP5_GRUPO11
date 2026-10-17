package Negocio;

import Dao.IUsuarioDao;
import Dao.UsuarioDao;
import Entidades.Usuario;

public class UsuarioNegocio implements IUsuarioNegocio {

    private final IUsuarioDao usuarioDao;

    public UsuarioNegocio() {
        this.usuarioDao = new UsuarioDao();
    }

    @Override
    public Usuario validarLogin(String usuario, String contrasena) {
        if (usuario == null || contrasena == null) {
            return null;
        }
        return usuarioDao.obtenerUsuario(usuario, contrasena);
    }
}
