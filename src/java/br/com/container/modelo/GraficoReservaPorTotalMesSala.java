/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.modelo;

import java.io.Serializable;

/**
 *
 * @author Alunos
 */
public class GraficoReservaPorTotalMesSala implements Serializable{
    
    private int mes;
    private long total;

    public GraficoReservaPorTotalMesSala(int mes, long total) {
        this.mes = mes;
        this.total = total;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    
    
    
    
}
