/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dao.asistente;

import co.edu.ufps.siwai.modelo.dao.DAOPedido;
import co.edu.ufps.siwai.modelo.dto.ArticuloDTO;
import co.edu.ufps.siwai.modelo.dto.PedidoDTO;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Clase que sirve para gestionar un pedido antes de ser registrado.
 *
 * @author Alejandro Ramírez
 */
public class Pedido implements Serializable {

    private PedidoDTO dto;
    
    /**
     * Metodo que iguala a null el atributo pedido.
     */
    public void eliminarPedido() {
        dto = null;
    }

    /**
     * Metodo que verifica si un articulo ya esta en la lista de articulos.
     * @param referencia String con la referencia del articulo.
     * @return True si esta, false si no.
     */
    public boolean articuloExiste(String referencia) {
        return dto.articuloExiste(referencia);
    }
    
    /**
     * Constructor que inicializa el atributo PedidoDTO.
     *
     * @param fecha Calendar con la fecha en la que se realizo el pedido.
     * @param codProveedor String con el codigo del proveedor al que se le
     * realizo el pedido.
     */
    public Pedido(Calendar fecha, String codProveedor) {
        dto = new PedidoDTO(fecha, codProveedor);
    }

    /**
     * Metodo que añade un articulo a la lista de articulos del atributo
     * PedidoDTO.
     *
     * @param dto ArticuloDTO con la informacion del articulo.
     * @return true si se añadio, false si no.
     */
    public boolean aniadirArticulo(ArticuloDTO dto) {
        return this.dto.aniadirArticulo(dto);
    }

    /**
     * Metodo que elimina un articulo de la lista de articulos del atributo
     * PedidoDTO.
     *
     * @param dto ArticuloDTO con la informacion del articulo.
     * @return true si se elimino, false si no.
     */
    public boolean eliminarArticulo(ArticuloDTO dto) {
        return this.dto.eliminarArticulo(dto);
    }

    /**
     * Metodo que envia la peticion de registrar el pedido al DAOPedido.
     *
     * @return True si se registro, false si no.
     * @throws Exception Excepcion al conectarse a la base de datos.
     */
    public boolean registrarPedido() throws Exception {
        if (dto.getArticulos().size() > 0) {
            DAOPedido dao = new DAOPedido();
            boolean exito = dao.registrarPedido(dto);
            if (exito) {
                dto = null;
            }
            return exito;
        }
        return false;
    }

}
