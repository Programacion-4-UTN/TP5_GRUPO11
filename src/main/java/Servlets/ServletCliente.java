package Servlets;

import Entidades.Cliente;
import Negocio.ClienteNegocio;
import Negocio.IClienteNegocio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IClienteNegocio clienteNegocio = new ClienteNegocio();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String accion = request.getParameter("accion");
        
        if ("eliminar".equals(accion)) {
            String idString = request.getParameter("id");
            if (idString != null && !idString.isEmpty()) {
                int idCliente = Integer.parseInt(idString);
                boolean eliminado = clienteNegocio.eliminarCliente(idCliente);
                
                if (eliminado) {
                    request.setAttribute("exitoEliminar", "El cliente fue eliminado correctamente.");
                } else {
                    request.setAttribute("errorEliminar", "No se pudo eliminar el cliente.");
                }
            }
        }
    	
    	// para cargar la tabla de ListarClientes.jsp
        List<Cliente> clientes = clienteNegocio.obtenerTodos();
        request.setAttribute("listaClientes", clientes);
        request.getRequestDispatcher("/ListarClientes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        if ("alta".equals(accion)) {
            String dni = request.getParameter("dni");
            String cuil = request.getParameter("cuil");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String sexo = request.getParameter("sexo");
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            String direccion = request.getParameter("direccion");
            String nacionalidad = request.getParameter("nacionalidad");
            String localidad = request.getParameter("localidad");
            String provincia = request.getParameter("provincia");
            String correo = request.getParameter("correo");
            String telefono = request.getParameter("telefono");

            // Validación estricta 
            if (dni.isEmpty() || cuil.isEmpty() || nombre.isEmpty() || apellido.isEmpty() ||
                sexo.isEmpty() || fechaNacimiento.isEmpty() || direccion.isEmpty() ||
                nacionalidad.isEmpty() || localidad.isEmpty() || provincia.isEmpty() ||
                correo.isEmpty() || telefono.isEmpty()) {
                
                request.setAttribute("error", "Debe ingresar todos los campos para poder continuar.");
                request.getRequestDispatcher("/AltaCliente.jsp").forward(request, response);
                return;
            }

            Cliente c = new Cliente();
            c.setDni(dni); c.setCuil(cuil); c.setNombre(nombre); c.setApellido(apellido);
            c.setSexo(sexo); c.setFechaNacimiento(fechaNacimiento); c.setDireccion(direccion);
            c.setNacionalidad(nacionalidad); c.setLocalidad(localidad);
            c.setProvincia(provincia); c.setCorreoElectronico(correo); c.setTelefono(telefono);

            boolean guardado = clienteNegocio.agregarCliente(c);

            if (guardado) {
                request.setAttribute("exito", "Cliente guardado con éxito.");
            } else {
                request.setAttribute("error", "Error al guardar el cliente en la base de datos.");
            }
            request.getRequestDispatcher("/AltaCliente.jsp").forward(request, response);
        }
    }
}