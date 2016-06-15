/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo;

import co.edu.ufps.siwai.modelo.dao.DAOArticulo;
import co.edu.ufps.siwai.modelo.dao.DAOArticuloExtra;
import co.edu.ufps.siwai.modelo.dto.ClienteDTO;
import co.edu.ufps.siwai.modelo.dao.DAOCliente;
import co.edu.ufps.siwai.modelo.dao.DAOComparacion;
import co.edu.ufps.siwai.modelo.dao.DAOEmpleado;
import co.edu.ufps.siwai.modelo.dao.DAOPedido;
import co.edu.ufps.siwai.modelo.dao.DAOProveedor;
import co.edu.ufps.siwai.modelo.dao.DAOSucursal;
import co.edu.ufps.siwai.modelo.dao.DAOTraslado;
import co.edu.ufps.siwai.modelo.dao.DAOUbicacion;
import co.edu.ufps.siwai.modelo.dao.DAOVenta;
import co.edu.ufps.siwai.modelo.dao.asistente.Pedido;
import co.edu.ufps.siwai.modelo.dao.asistente.Traslado;
import co.edu.ufps.siwai.modelo.dao.asistente.Venta;
import co.edu.ufps.siwai.modelo.dto.ArticuloDTO;
import co.edu.ufps.siwai.modelo.dto.ArticuloExtraDTO;
import co.edu.ufps.siwai.modelo.dto.ComparacionDTO;
import co.edu.ufps.siwai.modelo.dto.EmpleadoDTO;
import co.edu.ufps.siwai.modelo.dto.PedidoDTO;
import co.edu.ufps.siwai.modelo.dto.ProveedorDTO;
import co.edu.ufps.siwai.modelo.dto.SucursalDTO;
import co.edu.ufps.siwai.modelo.dto.TrasladoDTO;
import co.edu.ufps.siwai.modelo.dto.UbicacionDTO;
import co.edu.ufps.siwai.modelo.dto.VentaDTO;
import co.edu.ufps.siwai.modelo.seguridad.MD5;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.TreeSet;

/**
 * Clase que sirve como fachada del negocio.
 *
 * @author Alejandro Ramírez
 */
public class Fachada implements Serializable {

    private Pedido pedido;
    private Traslado traslado;
    private Venta venta;

    /**
     * Metodo que envia los datos del cliente a DAOCliente para que sean
     * registrados en la base de datos.
     *
     * @param dni Documento nacional de identificación del cliente.
     * @param nombre Nombres del cliente.
     * @param apellido Apellidos del cliente.
     * @param direccion Direccion de residencia del cliente.
     * @param email Correo electronico del cliente.
     * @param telefono Telefono o celular del cliente.
     * @return True si el cliente fue registrado, false si no se registro.
     * @throws java.lang.Exception Excepcion en la conexion a la base de datos.
     */
    public boolean registrarCliente(String dni, String nombre, String apellido,
            String direccion, String email, String telefono, int ciudad) throws Exception {
        ClienteDTO dto = new ClienteDTO(dni, nombre, apellido, direccion, email, telefono, ciudad);
        DAOCliente dao = new DAOCliente();
        return dao.registrarCliente(dto);
    }

    /**
     * Metodo que solicita a DAOCliente la consulta de los datos de los
     * clientes.
     *
     * @param columna Nombre de la columna de la tabla de clientes.
     * @param informacion Contenido que debe cumplir la columna por la que se
     * busca a los clientes (nom, dni o Todos).
     * @return ArrayList de ClienteDTO.
     * @throws Exception Exception originada por fallo en la conexion o error al
     * consultar los clientes.
     */
    public ArrayList<ClienteDTO> consultarClientes(String columna, String informacion) throws Exception {
        DAOCliente dao = new DAOCliente();
        return dao.consultarClientes(columna, informacion);
    }

    public boolean registrarSucursal(String codigo, String nombre, int telefono, String email,
            String paginaWeb, String direccion, String ciudad, String pais) throws Exception {
        SucursalDTO dto = new SucursalDTO(codigo, nombre, email, paginaWeb, direccion, ciudad,
                pais, telefono);
        DAOSucursal dao = new DAOSucursal();
        return dao.registrarSucursal(dto);
    }

    public ArrayList<SucursalDTO> consultarSucursal(String buscarPor, String informacion) throws Exception {
        DAOSucursal dao = new DAOSucursal();
        return dao.consultarSucursal(buscarPor, informacion);
    }

    public boolean actualizarSucursal(String codigo, String nombre, int telefono, String email,
            String paginaWeb, String direccion, String ciudad, String pais) throws Exception {
        SucursalDTO dto = new SucursalDTO(codigo, nombre, email, paginaWeb, direccion,
                ciudad, pais, telefono);
        DAOSucursal dao = new DAOSucursal();
        return dao.actualizarSucursal(dto);

    }

    public boolean actualizarEmpleado(String sucursal, String cargo, String dni, String nombre,
            String apellido, String telefono, String celular, String email, String direccion, String fIngreso, short habilitado, String codigo) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT-5"));
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.getSucursal().setCodigo(sucursal);
        dto.setCargo(cargo);
        dto.setDni(dni);
        dto.setNombre(nombre);
        dto.setApellido(apellido);
        dto.setTelefono(telefono);
        dto.setCelular(celular);
        dto.setEmail(email);
        dto.setDireccion(direccion);
        dto.setfIngreso(fIngreso);
        dto.setHabilitado(habilitado);
        dto.setCodigo(codigo);
        if (habilitado == 1) {
            dto.setfSalida("NULL");
        } else {
            dto.setfSalida(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH));
        }
        DAOEmpleado dao = new DAOEmpleado();
        return dao.actualizarEmpleado(dto);
    }

    public boolean validarContraseña(String codigo, String contraseña) throws Exception {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setCodigo(codigo);
        contraseña = MD5.encriptar(contraseña);
        dto.setContraseña(contraseña);
        DAOEmpleado dao = new DAOEmpleado();
        return dao.validarContraseña(dto);
    }

    public boolean cambiarContraseña(String codigo, String contraNueva) throws Exception {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setCodigo(codigo);
        contraNueva = MD5.encriptar(contraNueva);
        dto.setContraseña(contraNueva);
        DAOEmpleado dao = new DAOEmpleado();
        return dao.cambiarContraseña(dto);
    }

    public boolean registrarEmpleado(String codigo, String dni, String nombre, String apellido,
            String telefono, String celular, String email, String direccion,
            String fIngreso, String cargo, String sucursal) throws Exception {
        String contraseñaE = "NULL";
        if (!cargo.equals("Vendedor")) {
            contraseñaE = "123456";
            contraseñaE = MD5.encriptar(contraseñaE);
        }
        EmpleadoDTO dto = new EmpleadoDTO(codigo, dni, nombre, apellido, contraseñaE, email,
                direccion, cargo, sucursal, telefono, celular, fIngreso);
        DAOEmpleado dao = new DAOEmpleado();
        return dao.registrarEmpleado(dto);
    }

    public ArrayList<EmpleadoDTO> consultarEmpleado(String buscarPor, String informacion) throws Exception {
        DAOEmpleado dao = new DAOEmpleado();
        return dao.consultarEmpleado(buscarPor, informacion);
    }

    public String iniciarSesion(String usuario, String contraseña) throws Exception {
        DAOEmpleado dao = new DAOEmpleado();
        String contraseñaE = MD5.encriptar(contraseña);
        return dao.iniciarSesion(usuario, contraseñaE);
    }

    /**
     * Metodo que envia la peticion a DAOProveedor para registrar un proveedor.
     *
     * @param codigo Codigo del proveedor.
     * @param nit Nit del proveedor.
     * @param nombre Nombre del proveedor.
     * @param cuenta Cuenta del proveedor.
     * @param tipoCuenta Tipo de cuenta del proveedor.
     * @param sitioWeb URL del Sito Web del proveedor.
     * @param nombreContacto Nombre de la persona que sirve de intermediario con
     * el proveedor.
     * @param emailContacto Email del contacto.
     * @param numCuenta Numero de cuenta del proveedor.
     * @param telContacto Telefono del contacto.
     * @return Cadena de texto, Exito si registro o la exception nula.
     * @throws java.lang.Exception Si existe error en la conexion a la base de
     * datos.
     */
    public String registrarProveedor(String codigo, String nit, String nombre,
            String cuenta, String tipoCuenta, String sitioWeb, String nombreContacto,
            String emailContacto, String numCuenta, String telContacto) throws Exception {
        ProveedorDTO dto = new ProveedorDTO(codigo, nit, nombre, cuenta,
                tipoCuenta, sitioWeb, nombreContacto, emailContacto, numCuenta, telContacto);
        DAOProveedor dao = new DAOProveedor();
        return dao.registrarProveedor(dto);
    }

    /**
     * Metodo que envia la peticion a DAOProveedor para consultar proveedores.
     *
     * @param columna String que contiene la columna por la que se va a filtrar.
     * @param info Strinf con la informacion que debe cumplir la columna.
     * @return ArrayList de ProveedorDTO.
     * @throws Exception Si existe un error en la conexion a la base de datos.
     */
    public ArrayList<ProveedorDTO> consultarProveedores(String columna, String info) throws Exception {
        DAOProveedor dao = new DAOProveedor();
        return dao.consultarProveedores(columna, info);
    }

    /**
     * Metodo que obtiene los datos de los paises.
     *
     * @return ArrayList de UbicacionDTO
     * @throws java.lang.Exception Si existe un error en la conexion.
     */
    public ArrayList<UbicacionDTO> obtenerPaises() throws Exception {
        DAOUbicacion dao = new DAOUbicacion();
        return dao.obtenerPaises();
    }

    /**
     * Metodo que obtiene los datos de las ciudades de un pais.
     *
     * @param pais String con el codigo del pais a las que pertenecen las
     * ciudades.
     * @return ArrayList de UbicacionDTO
     * @throws java.lang.Exception Si existe un error en la conexion.
     */
    public ArrayList<UbicacionDTO> obtenerCiudades(String pais) throws Exception {
        DAOUbicacion dao = new DAOUbicacion();
        return dao.obtenerCiudades(pais);
    }

    /**
     * Metodo que envia los datos del cliente a DAOCliente para que sean
     * actualizados en la base de datos.
     *
     * @param dni Documento nacional de identificación del cliente.
     * @param nombre Nombres del cliente.
     * @param apellido Apellidos del cliente.
     * @param direccion Direccion de residencia del cliente.
     * @param email Correo electronico del cliente.
     * @param telefono Telefono o celular del cliente.
     * @return True si el cliente fue actualziado, false si no se actualizo.
     * @throws java.lang.Exception Excepcion en la conexion a la base de datos.
     */
    public boolean actualizarCliente(String dni, String nombre, String apellido,
            String direccion, String email, String telefono, int ciudad) throws Exception {
        ClienteDTO dto = new ClienteDTO(dni, nombre, apellido, direccion, email, telefono, ciudad);
        DAOCliente dao = new DAOCliente();
        return dao.actualizarCliente(dto);
    }

    /**
     * Metodo que envia la peticion a DAOProveedor para actualizar un proveedor.
     *
     * @param codigo Codigo del proveedor.
     * @param nit Nit del proveedor.
     * @param nombre Nombre del proveedor.
     * @param cuenta Cuenta del proveedor.
     * @param tipoCuenta Tipo de cuenta del proveedor.
     * @param sitioWeb URL del Sito Web del proveedor.
     * @param nombreContacto Nombre de la persona que sirve de intermediario con
     * el proveedor.
     * @param emailContacto Email del contacto.
     * @param numCuenta Numero de cuenta del proveedor.
     * @param telContacto Telefono del contacto.
     * @return Cadena de texto, Exito si actualizo o la exception nula.
     * @throws java.lang.Exception Si existe error en la conexion a la base de
     * datos.
     */
    public String actualizarProveedor(String codigo, String nit, String nombre,
            String cuenta, String tipoCuenta, String sitioWeb, String nombreContacto,
            String emailContacto, String numCuenta, String telContacto) throws Exception {
        ProveedorDTO dto = new ProveedorDTO(codigo, nit, nombre, cuenta,
                tipoCuenta, sitioWeb, nombreContacto, emailContacto, numCuenta, telContacto);
        DAOProveedor dao = new DAOProveedor();
        return dao.actualizarProveedor(dto);
    }

    /**
     * Este método llama crea un ArticuloDTO y llama al DAOArticulo para que él
     * se encargue de guardar el articulo
     *
     * @param referencia referencia del articulo
     * @param nombre nombre del articulo
     * @param tipoArticulo tipo del articulo
     * @return retorna lo que le retorne el método registrarArticulo de la clase
     * DAOArticulo
     */
    public boolean registrarAriculo(String referencia, String nombre, String tipoArticulo) throws Exception {
        ArticuloDTO dto = new ArticuloDTO(referencia, nombre, tipoArticulo);
        DAOArticulo dao = new DAOArticulo();
        return dao.registrarArticulo(dto);

    }

    public boolean registrarArticuloExtra(String codigo, String sucursal, String nombre, int cantidad,
            String fEntrada, int costo, int valor, String notas) throws Exception {
        ArticuloExtraDTO dto = new ArticuloExtraDTO(codigo, nombre, fEntrada, notas, cantidad, costo, valor, sucursal);
        DAOArticuloExtra dao = new DAOArticuloExtra();
        return dao.registrarArticuloExtra(dto);
    }

    public ArrayList<ArticuloExtraDTO> consultarArticuloExtra(String sucursal, String buscarPor, String info) throws Exception {
        DAOArticuloExtra dao = new DAOArticuloExtra();
        return dao.consultarArticuloExtra(sucursal, buscarPor, info);
    }

    public Venta getVenta() {
        return venta;
    }

    public ArrayList<ArticuloDTO> consultarArticulo(String sucursal, String buscarPor, String info) throws Exception {
        DAOArticulo dao = new DAOArticulo();
        return dao.consultarArticulo(sucursal, buscarPor, info);
    }

    /**
     * Metodo que crea el pedido.
     *
     * @param fecha Calendar con la fecha en la que se realizo el pedido.
     * @param proveedor String con el codigo del proveedor al que se le realizo
     * el pedido.
     */
    public void crearPedido(Calendar fecha, String proveedor) {
        pedido = new Pedido(fecha, proveedor);
    }

    /**
     * Metodo que añade un articulo a la lista de articulos del pedido.
     *
     * @param referencia String con la referencia del articulo a añadir, debe
     * ser unica.
     * @param cantidad Cantidad de articulos que se solicitan con el pedido.
     * @return true si se añadio, false si no.
     */
    public boolean aniadirArticuloPedido(String referencia, int cantidad) {
        ArticuloDTO dto = new ArticuloDTO();
        dto.setReferencia(referencia);
        dto.setCantidad(cantidad);
        return pedido.aniadirArticulo(dto);
    }

    /**
     * Metodo que elimina un articulo de la lista de articulos del pedido.
     *
     * @param referencia String con la referencia del articulo a añadir.
     * @return true si se elimino, false si no.
     */
    public boolean eliminarArticuloPedido(String referencia) {
        ArticuloDTO dto = new ArticuloDTO();
        dto.setReferencia(referencia);
        return pedido.eliminarArticulo(dto);
    }

    /**
     * Metodo que notifica a pedido para que este notifique a DAOPedido.
     *
     * @return True si se registro, false si no.
     * @throws Exception Excepcion al conectarse a la base de datos.
     */
    public boolean registrarPedido() throws Exception {
        return pedido.registrarPedido();
    }

    /**
     * Metodo que obtiene el nombre de un articulo para un añadirlo a un pedido.
     *
     * @param referencia String con la referencia del articulo.
     * @return String con el nombre del articulo o String con la cadena
     * ArticuloNombre si el articulo ya esta en el pedido.
     * @throws Exception Si existe un error en la conexion.
     */
    public String cargarNombreArticuloPedido(String referencia) throws Exception {
        if (!pedido.articuloExiste(referencia)) {
            DAOArticulo dao = new DAOArticulo();
            return dao.obtenerNombreArticulo(referencia);
        }
        return "ArticuloDuplicado";
    }

    public ArrayList<PedidoDTO> consultarPedido(String buscarPor, String informacion) throws Exception {
        DAOPedido dao = new DAOPedido();
        return dao.consultarPedido(buscarPor, informacion);
    }

    public TreeSet<ArticuloDTO> cargarArticuloPedidos(int codigoPedido) throws Exception {
        DAOPedido dao = new DAOPedido();
        PedidoDTO dto = new PedidoDTO();
        dto.setCodigo(codigoPedido);
        return dao.cargarArticuloPedidos(dto).getArticulos();
    }

    public boolean registrarComparacion(int pedido, ArrayList<ArticuloDTO> dtos, Calendar fecha,
            String notas, String sucursal, int transporte) throws Exception {
        PedidoDTO dto = new PedidoDTO();
        dto.setCodigo(pedido);
        dto.crearComparacion(fecha, notas, sucursal, transporte);
        for (ArticuloDTO articulo : dtos) {
            if (!dto.aniadirArticuloComparacion(articulo)) {
                return false;
            }
        }
        DAOComparacion dao = new DAOComparacion();
        return dao.registrarComparacion(dto.getComparacion());
    }

    public String cargarNombreArticuloComparacion(String referencia) throws Exception {
        DAOArticulo dao = new DAOArticulo();
        return dao.obtenerNombreArticulo(referencia);
    }

    public boolean existeComparacion(int codigo) throws Exception{
        DAOComparacion dao = new DAOComparacion();
        return dao.existeComparacion(codigo);
    }

    public ComparacionDTO consultarComparacion(int codigo) throws Exception {
        DAOComparacion dao = new DAOComparacion();
        return dao.consultarComparacion(codigo);
    }

    public void crearTraslado(String sOrigen, String sDestino) {
        traslado = new Traslado(sOrigen, sDestino);
    }

    public ArticuloDTO cargarNombreArticuloTraslado(String referencia) throws Exception {
        if (!traslado.articuloExiste(referencia)) {
            DAOArticulo dao = new DAOArticulo();
            return dao.obtenerNombreArticuloTraslado(referencia, traslado.getDto().getOrigen().getCodigo());
        }
        ArticuloDTO dto = new ArticuloDTO();
        dto.setNombre("ArticuloDuplicado");
        return dto;
    }

    public boolean aniadirArticuloTraslado(String referencia, int cantidad) {
        ArticuloDTO dto = new ArticuloDTO();
        dto.setReferencia(referencia);
        dto.setCantidad(cantidad);
        return traslado.aniadirArticulo(dto);
    }

    public boolean registrarTraslado() throws Exception {
        return traslado.registrarTraslado();
    }

    public ArrayList<TrasladoDTO> consultarTraslado(String buscarPor, String informacion) throws Exception {
        DAOTraslado dao = new DAOTraslado();
        return dao.consultarTraslado(buscarPor, informacion);
    }

    public boolean crearVenta(String cliente, String vendedor, String sucursal, String cajero) throws Exception {
        DAOCliente dao = new DAOCliente();
        ArrayList<ClienteDTO> list = dao.consultarClientes("dni", cliente);
        if (!list.isEmpty()) {
            venta = new Venta(cliente, vendedor, sucursal, cajero);
            return true;
        } else {
            return false;
        }
    }

    public ArticuloDTO cargarNombreArticuloVenta(String referencia,String sucursal) throws Exception {
        if (!venta.articuloExiste(referencia)) {
            DAOArticulo dao = new DAOArticulo();
            return dao.obtenerNombreArticuloTraslado(referencia, sucursal);
        }
        ArticuloDTO dto = new ArticuloDTO();
        dto.setNombre("ArticuloDuplicado");
        return dto;
    }

    public boolean aniadirArticuloVenta(String referencia, int cantidad,int valor) {
        ArticuloDTO dto = new ArticuloDTO();
        dto.setReferencia(referencia);
        dto.setCantidad(cantidad);
        dto.setValor(valor);
        return venta.aniadirArticulo(dto);
    }

    public boolean registrarVenta() throws Exception {
        return venta.registrarVenta();
    }

    public TreeSet<ArticuloDTO> cargarArticulosTraslado(int codigo) throws Exception {
        DAOTraslado dao = new DAOTraslado();
        TrasladoDTO dto = new TrasladoDTO();
        dto.setCodigo(codigo);
        return dao.cargarArticuloTraslado(dto).getArticulos();
    }

    public ArrayList<VentaDTO> consultarVenta(String buscarPor, String informacion) throws Exception {
        DAOVenta dao = new DAOVenta();
        return dao.consultarVenta(buscarPor, informacion);
    }

    public TreeSet<ArticuloDTO> cargarArticulosVenta(int codigo) throws Exception {
        DAOVenta dao = new DAOVenta();
        VentaDTO dto = new VentaDTO();
        dto.setCodigo(codigo);
        return dao.cargarArticuloVenta(dto).getArticulos();
    }
}
