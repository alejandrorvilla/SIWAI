/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.TreeSet;
import java.util.Calendar;

/**
 *
 * @author MarlonGuerrero
 */
public class TrasladoDTO implements Serializable {

    private SucursalDTO origen,destino;
    private final TreeSet<ArticuloDTO> articulos;
    private Calendar fecha;
    private int codigo;

    public TrasladoDTO() {
        origen = new SucursalDTO();
        destino = new SucursalDTO();
        articulos = new TreeSet<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }


    public SucursalDTO getOrigen() {
        return origen;
    }

    public void setOrigen(SucursalDTO origen) {
        this.origen = origen;
    }

    public SucursalDTO getDestino() {
        return destino;
    }

    public void setDestino(SucursalDTO destino) {
        this.destino = destino;
    }

    public TreeSet<ArticuloDTO> getArticulos() {
        return articulos;
    }



    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public boolean aniadirArticulo(ArticuloDTO dto) {
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
