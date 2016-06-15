/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dao.asistente;

import co.edu.ufps.siwai.modelo.dao.DAOTraslado;
import co.edu.ufps.siwai.modelo.dto.ArticuloDTO;
import co.edu.ufps.siwai.modelo.dto.TrasladoDTO;
import java.io.Serializable;
import java.util.Calendar;


public class Traslado implements Serializable {

    private TrasladoDTO dto;


    public void eliminarTraslado() {
        dto = null;
    }

    public TrasladoDTO getDto() {
        return dto;
    }


    public boolean articuloExiste(String referencia) {
        return dto.articuloExiste(referencia);
    }


    public Traslado(String sOrigen, String sDestino) {
        dto = new TrasladoDTO();
        dto.getOrigen().setCodigo(sOrigen);
        dto.getDestino().setCodigo(sDestino);
        Calendar c1 = Calendar.getInstance();
        dto.setFecha(c1);
    }


    public boolean aniadirArticulo(ArticuloDTO dto) {
        return this.dto.aniadirArticulo(dto);
    }


    public boolean eliminarArticulo(ArticuloDTO dto) {
        return this.dto.eliminarArticulo(dto);
    }


    public boolean registrarTraslado() throws Exception {
        if (dto.getArticulos().size() > 0) {
            DAOTraslado dao = new DAOTraslado();
            boolean exito = dao.registrarTraslado(dto);
            if (exito) {
                dto = null;
            }
            return exito;
        }
        return false;
    }

}
