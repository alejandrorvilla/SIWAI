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
 * Clase de transferencia de datos de una comparacion de un pedido.
 * @author Alejandro Ramirez
 */
public class ComparacionDTO implements Serializable{
    
    private int pedido;
    private String notas;
    private final TreeSet<ArticuloDTO> articulos;
    private int transporte;
    private Calendar fecha;
    private SucursalDTO sucursal;

    /**
     * Constructor, incializa el atributo TreeSet de ArticuloDTO
     * @param pedido Integer con el codigo del pedido al que pertenece la comparacion.
     * @param fecha Calendar con la fecha en la que llego el pedido.
     * @param notas
     * @param sucursal
     * @param transporte
     */
    public ComparacionDTO(int pedido, Calendar fecha, String notas, String sucursal, int transporte) {
        this.pedido = pedido;
        this.articulos = new TreeSet<>();
        this.sucursal = new SucursalDTO();
        this.sucursal.setCodigo(sucursal);
        this.fecha = fecha;
        this.transporte = transporte;
        this.notas = notas;
    }

    /**
     * Constructor, incializa los atributos PedidoDTO y TreeSet de ArticuloDTO
     */
    public ComparacionDTO() {
        this.articulos = new TreeSet<>();
        sucursal = new SucursalDTO();
    }

    public int getTransporte() {
        return transporte;
    }

    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public void setTransporte(int transporte) {
        this.transporte = transporte;
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public TreeSet<ArticuloDTO> getArticulos() {
        return articulos;
    }

    public int getPedido() {
        return pedido;
    }

    public void setPedido(int pedido) {
        this.pedido = pedido;
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
    
}
