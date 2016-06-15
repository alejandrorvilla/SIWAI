/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TreeSet;

/**
 * Clase de transferencia de datos de los pedidos.
 * @author Alejandro Ramírez
 */
public class PedidoDTO implements Serializable {

    private Calendar fecha;
    private int codigo;
    private ProveedorDTO proveedor;
    private final TreeSet<ArticuloDTO> articulos;
    private ComparacionDTO comparacion;

    /**
     * Constructor, inicializa los atributos comparacion, proveedor y articulos.
     * @param fecha Calendar con la fecha en la que se realizo el pedido.
     * @param codProveedor Codigo del proveedor al que se le realizo el pedido.
     */
    public PedidoDTO(Calendar fecha, String codProveedor) {
        proveedor = new ProveedorDTO();
        articulos = new TreeSet<>();
        this.fecha = fecha;
        proveedor.setCodigo(codProveedor);
        comparacion = new ComparacionDTO();
    }

    /**
     * Constructor, inicializa los atributos comparacion, proveedor y articulos.
     */
    public PedidoDTO() {
        proveedor = new ProveedorDTO();
        articulos = new TreeSet<>();
        comparacion = new ComparacionDTO();
    }

    public void crearComparacion(Calendar fecha, String notas, String sucursal, int transporte){
        comparacion = new ComparacionDTO(this.codigo, fecha, notas, sucursal, transporte);
    }

    public ComparacionDTO getComparacion() {
        return comparacion;
    }
    
    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }

    public TreeSet<ArticuloDTO> getArticulos() {
        return articulos;
    }

    /**
     * Metodo que añade un articulo al TreeSet de ArticuloDTO
     * @param dto ArticuloDTO con los datos del articulo.
     * @return True si se añadio, false si existe otro con la misma referencia.
     */
    public boolean aniadirArticulo(ArticuloDTO dto) {
        return this.articulos.add(dto);
    }

    /**
     * Metodo que remueve un articulo del TreeSet de ArticuloDTO
     * @param dto ArticuloDTO con los datos del articulo.
     * @return True si se removio, false si no lo hizo.
     */
    public boolean eliminarArticulo(ArticuloDTO dto) {
        return this.articulos.remove(dto);
    }

    /**
     * Metodo que obtiene la cantidad total que se pide de todos los articulos.
     * @return Integer con la cantidad total de articulos.
     */
    public int obtenerCantidadesArticulos(){
        int total = 0;
        for(ArticuloDTO dto : articulos) {
            total += dto.getCantidad();
        }
        return total;
    }

    /**
     * Metodo que verifica si un articulo ya esta en la lista de articulos.
     * @param referencia String con la referencia del articulo.
     * @return True si esta, false si no.
     */
    public boolean articuloExiste(String referencia) {
        ArticuloDTO articulo = new ArticuloDTO();
        articulo.setReferencia(referencia);
        return articulos.contains(articulo);
    }
    /**
     * Metodo que formatea la fecha
     * @return String con la fecha formateada
     */
    public String getFechaFormateada(){
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      String fechaFormateada = sdf.format(this.fecha.getTime());

      return fechaFormateada;
    }
    
    public boolean aniadirArticuloComparacion(ArticuloDTO dto) {
        return comparacion.aniadirArticulo(dto);
    }
    
}
