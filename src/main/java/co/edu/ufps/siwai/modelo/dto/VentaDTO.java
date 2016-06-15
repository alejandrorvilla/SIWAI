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
 *
 * @author Marlon Guerrero
 */
public class VentaDTO implements Serializable {
    private int codigo;
    private SucursalDTO sucursal;
    private ClienteDTO cliente;
    private EmpleadoDTO cajero;
    private EmpleadoDTO vendedor;
    private Calendar fecha;
    private int subTotal=0;
    private double iva,total;
    private final TreeSet<ArticuloDTO> articulos;

    public VentaDTO() {
        sucursal = new SucursalDTO();
        cliente = new ClienteDTO();
        cajero = new EmpleadoDTO();
        vendedor = new EmpleadoDTO();
        articulos = new TreeSet<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public TreeSet<ArticuloDTO> getArticulos() {
        return articulos;
    }

    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public EmpleadoDTO getCajero() {
        return cajero;
    }

    public void setCajero(EmpleadoDTO cajero) {
        this.cajero = cajero;
    }

    public EmpleadoDTO getVendedor() {
        return vendedor;
    }

    public void setVendedor(EmpleadoDTO vendedor) {
        this.vendedor = vendedor;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean aniadirArticulo(ArticuloDTO dto) {
        this.subTotal += dto.getValor();
        return this.articulos.add(dto);
    }

    public boolean eliminarArticulo(ArticuloDTO dto) {
        return this.articulos.remove(dto);
    }

    public boolean articuloExiste(String referencia) {
        ArticuloDTO articulo = new ArticuloDTO();
        articulo.setReferencia(referencia);
        return articulos.contains(articulo);
    }
    public String getFechaFormateada(){
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      String fechaFormateada = sdf.format(this.fecha.getTime());

      return fechaFormateada;
    }
}
