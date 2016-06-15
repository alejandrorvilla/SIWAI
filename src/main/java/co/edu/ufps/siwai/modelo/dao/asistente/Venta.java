/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dao.asistente;

import co.edu.ufps.siwai.modelo.dao.DAOVenta;
import co.edu.ufps.siwai.modelo.dto.ArticuloDTO;
import co.edu.ufps.siwai.modelo.dto.VentaDTO;
import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author MarlonGuerrero
 */
public class Venta implements Serializable{
    private VentaDTO dto;

    public void eliminarVenta() {
        dto = null;
    }

    public VentaDTO getDto() {
        return dto;
    }


    public boolean articuloExiste(String referencia) {
        return dto.articuloExiste(referencia);
    }


    public Venta(String cliente, String vendedor,String sucursal,String cajero) {
        dto = new VentaDTO();
        dto.getCliente().setDni(cliente);
        dto.getVendedor().setCodigo(vendedor);
        dto.getSucursal().setCodigo(sucursal);
        dto.getCajero().setCodigo(cajero);
        Calendar c1 = Calendar.getInstance();
        dto.setFecha(c1);
    }


    public boolean aniadirArticulo(ArticuloDTO dto) {
        return this.dto.aniadirArticulo(dto);
    }


    public boolean eliminarArticulo(ArticuloDTO dto) {
        return this.dto.eliminarArticulo(dto);
    }


    public boolean registrarVenta() throws Exception {
        if (dto.getArticulos().size() > 0) {
            DAOVenta dao = new DAOVenta();
            double iva = dto.getSubTotal()*0.16;
            dto.setIva(iva);
            dto.setTotal(dto.getSubTotal()+iva);
            boolean exito = dao.registrarVenta(dto);
            if (exito) {
                dto = null;
            }
            return exito;
        }
        return false;
    }
}
