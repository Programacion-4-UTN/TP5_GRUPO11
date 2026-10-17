
package Filtros;

import java.io.IOException;

import Servlets.ServletLogin;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/Inicio.jsp", "/AltaCliente.jsp", "/ListarClientes.jsp", "/ServletCliente"})
public class FiltroSesion implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        // validamos segun ServletLogin
        if (session == null || session.getAttribute(ServletLogin.SESSION_USUARIO) == null) {
            res.sendRedirect(req.getContextPath() + "/Login.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }
}