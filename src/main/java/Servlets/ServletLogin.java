package Servlets;

import java.io.IOException;

import Entidades.Usuario;
import Negocio.IUsuarioNegocio;
import Negocio.UsuarioNegocio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Login del sistema. La logica (validaciones, sesion y mensajes) vive aca,
 * como pide la consigna; la capa Negocio solo consulta al DAO.
 *
 * POST /ServletLogin                -> valida credenciales
 * GET  /ServletLogin?accion=logout  -> cierra la sesion
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /** Atributo de sesion donde se guarda el usuario logueado. */
    public static final String SESSION_USUARIO = "usuarioLogueado";

    /** Pagina a la que se va despues de un login correcto. */
    private static final String PAGINA_INICIO = "Inicio.jsp";

    private final IUsuarioNegocio usuarioNegocio = new UsuarioNegocio();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if ("logout".equals(request.getParameter("accion"))) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }
        response.sendRedirect(request.getContextPath() + "/Login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");
        usuario = (usuario == null) ? "" : usuario.trim();
        contrasena = (contrasena == null) ? "" : contrasena;

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            volverAlLogin(request, response, "Debe ingresar usuario y contraseña.");
            return;
        }

        Usuario usuarioValido = usuarioNegocio.validarLogin(usuario, contrasena);

        if (usuarioValido == null) {
            volverAlLogin(request, response, "Usuario o contraseña incorrectos.");
            return;
        }

        // Sesion nueva en cada login (evita reutilizar un id de sesion previo)
        HttpSession anterior = request.getSession(false);
        if (anterior != null) {
            anterior.invalidate();
        }
        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_USUARIO, usuarioValido.getUsuario());

        response.sendRedirect(request.getContextPath() + "/" + PAGINA_INICIO);
    }

    private void volverAlLogin(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws ServletException, IOException {
        request.setAttribute("error", mensaje);
        request.getRequestDispatcher("/Login.jsp").forward(request, response);
    }
}