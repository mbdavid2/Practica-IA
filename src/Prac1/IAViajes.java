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
        return true;
    }

    public IAPet DelPet(int i){ //0 <= i < Peticiones.size()
        beneficioTotal -= Peticiones.get(i).get_Ben();
        IAPet peticion = Peticiones.get(i);
        int x = Peticiones.size();
        if(i == x-1){

        }
        if(Peticiones.size()%2 == 0){

        }
        return peticion;
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

    public Distribucion getCD(){
        return CD;
    }



    /*Check state of object*/
    /*public void estadoViaje(){
        if (pet.size() > 0) System.out.println("      El CD:" + "(" + CD.getCoordX() + "," + CD.getCoordY() + ")" + " tiene programado atender a G:" + "(" + pet.get(0).get_Gas().getCoordX() + "," + pet.get(0).get_Gas().getCoordY() + ")" + " por su petición de " +  pet.get(0).get_Dias() + " dias");
        if (pet.size() > 1) System.out.println("      El CD:" + "(" + CD.getCoordX() + "," + CD.getCoordY() + ")" + " tiene programado atender a G:" + "(" + pet.get(1).get_Gas().getCoordX() + "," + pet.get(1).get_Gas().getCoordY() + ")" + " por su petición de " +  pet.get(1).get_Dias() + " dias");
        System.out.println("      Distancia del viaje: " + distance);
    }*/
}
