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
        beneficioTotal = 0;
    }

    /*Operaciones*/
    public boolean AddPet(IAPet peticion){
        int x = Peticiones.size();
        if(x >= 10) return false; //Maximo viajes (5)
        if(x%2 != 0)distanciaTotal -= calcular_dV((x-1));
        Peticiones.add(peticion);
        distanciaTotal += calcular_dV(x);
        beneficioTotal += peticion.get_Ben();
        return true;
    }

    public IAPet DelPet(int i){ //0 <= i < Peticiones.size()
        beneficioTotal -= Peticiones.get(i).get_Ben();
        IAPet peticion = Peticiones.get(i);
        int x = Peticiones.size();
        if(i = x-1){

        }
        if(Peticiones.size()%2 == 0){

        }
        pet.remove(i);
        gas.remove(i);
        distance = getTotalDistance();
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
            Gasolinera g2 = (Peticiones.get(i+1)).get_Gas()
            return distCD_G(CD,g1) + distG_G(g1,g2) + distCD_G(CD,g2);
        }
        return -1;
    }

    private int calcular_dT(){
        int distance = 0;
        for(int i = 0; i< Peticiones.size(); i+=2){
           distance += calcular_dV(i);
        }
        return distance;
    }

    /*Check state of object*/
    public void estadoViaje(){
        if (pet.size() > 0) System.out.println("      El CD:" + "(" + CD.getCoordX() + "," + CD.getCoordY() + ")" + " tiene programado atender a G:" + "(" + gas.get(0).getCoordX() + "," + gas.get(0).getCoordY() + ")" + " por su petición de " +  pet.get(0) + " dias");
        if (pet.size() > 1) System.out.println("      El CD:" + "(" + CD.getCoordX() + "," + CD.getCoordY() + ")" + " tiene programado atender a G:" + "(" + gas.get(1).getCoordX() + "," + gas.get(1).getCoordY() + ")" + " por su petición de " +  pet.get(1) + " dias");
        System.out.println("      Distancia del viaje: "+distance);
    }

}

