package Prac1;
import IA.Gasolina.Distribucion;
import IA.Gasolina.Gasolinera;
import IA.Gasolina.Gasolineras;

import java.util.ArrayList;

public class IAViajes {

    private Distribucion CD; //Centro distribucion

    /* Array de peticiones.
    Un viaje es un conjunto de dos peticiones, una de posición par x (x%2 = 0) i x+1;
    */
    private ArrayList<IAPet> Peticiones;

    private int distanciaTotal;

    private int beneficioTotal;

    /*Constructor*/
    public IAViajes(Distribucion centro) {
        CD = centro;
        Peticiones = new ArrayList<IAPet>();
        distanciaTotal = 0;
    }

    /*Operaciones*/
    public boolean AddPet(IAPet peticion){
        int x = Peticiones.size();
        if(x >= 10) return false; //Maximo viajes (5)
        if(x%2 != 0) distanciaTotal -= calcular_dV((x-1));
        Peticiones.add(peticion);
        distanciaTotal += calcular_dV(x);
        beneficioTotal += peticion.get_Ben();
        return distanciaTotal > 640;
    }

    /*Getters/Funciones Auxiliares*/
    private int distCD_G(Distribucion c, Gasolinera g) { //Distance between a CD and a G
        return Math.abs(c.getCoordX() - g.getCoordX()) + Math.abs(c.getCoordY() - g.getCoordY());
    }

    private int distG_G(Gasolinera G1, Gasolinera G2) { //Distance between two G
        return Math.abs(G1.getCoordX() - G2.getCoordX()) + Math.abs(G1.getCoordY() - G2.getCoordY());
    }

    private int calcular_dV(int i){ //Distancia del viaje [i,i+1] & i%2 = 0 & <= 0 < Peticiones.size()
        Gasolinera g1 = (Peticiones.get(i)).get_Gas();
        if(i == Peticiones.size() - 1) return 2*distCD_G(CD,g1);
        else {
            Gasolinera g2 = (Peticiones.get(i+1)).get_Gas();
            return distCD_G(CD,g1) + distG_G(g1,g2) + distCD_G(CD,g2);
        }
    }

    /*Check state of object*/
    public void estadoViaje(){
        System.out.println("El CD:" + "(" + CD.getCoordX() + "," + CD.getCoordY() + ")" + " tiene programados los siguientes viajes:");
        for (int i = 0; i < Peticiones.size(); ++i){
            if(i%2 == 0) System.out.println("   Viaje " + (i%2)+1 + ":");
            System.out.println("      " + ((i%2)+1) +". Atiende a G:" + "(" + Peticiones.get(i).get_Gas().getCoordX() + "," + Peticiones.get(i).get_Gas().getCoordY() + ")" + " por su petición de " + Peticiones.get(i).get_Dias() + " dias");
        }
        System.out.println("Distancia total del camión: " + distanciaTotal);
    }
}
