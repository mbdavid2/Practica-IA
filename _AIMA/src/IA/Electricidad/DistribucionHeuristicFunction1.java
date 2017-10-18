/*
 * Practica de IA (Busqueda)
 * FIB - UPC
 * Curso 2006-2007
 * Cuatrimestre de Otono
 *
 * Daniel Garcia Perez
 * Sergio Vico Marfil
 *
 *
 * DistribucionHeuristicFunction1.java
 *
 */

package IA.Electricidad;

//Priorizar soluciones mas ecologicas

public class DistribucionHeuristicFunction1 extends DistribucionHeuristic {
    private OfertaDemanda od= OfertaDemanda.getOfertaDemanda();
    
    public double getHeuristicValue(Object estado) {
        Distribucion dist = (Distribucion)estado;
        int val=0;
        int paq;
        for(int i=0; i < dist.getNumConsumidores(); i++) {
            paq = dist.getPaqueteAsignado(i);
            
            if( paq == -1 ) {
                val++;
            }
            else {
                val += od.getCCo2Min(i) - od.getPCo2(paq);
            }
        }
        
        return (val);
    }
    
    
}
