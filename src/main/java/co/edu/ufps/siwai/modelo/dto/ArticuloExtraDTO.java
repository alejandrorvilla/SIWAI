/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.siwai.modelo.dto;

import java.io.Serializable;

/**
 *
 * @author MarlonGuerrero
 */
public class ArticuloExtraDTO implements Serializable{
    private String codigo, nombre,fEntrada,notas;
    private int cantidad,costo,valor;
    private SucursalDTO sucursal;

    public ArticuloExtraDTO() {
        sucursal = new SucursalDTO();
    }

    public ArticuloExtraDTO(String codigo, String nombre, String fEntrada, String notas, int cantidad, int costo, int valor,String codSucursal) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fEntrada = fEntrada;
        this.notas = notas;
        this.cantidad = cantidad;
        this.costo = costo;
        this.valor = valor;
        this.sucursal = new SucursalDTO();
        this.sucursal.setCodigo(codSucursal);
    }

    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getfEntrada() {
        return fEntrada;
    }

    public void setfEntrada(String fEntrada) {
        this.fEntrada = fEntrada;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }
    
    
}
