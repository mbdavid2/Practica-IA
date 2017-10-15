package Prac1;
import IA.Gasolina.Distribucion;
import IA.Gasolina.Gasolinera;
import IA.Gasolina.Gasolineras;

import java.util.ArrayList;

public class IAViajes {
    /*
       CD (CentroDistribucion)
       pet1 pet2   //Assigned petitions (max 2)
       gasX gasY   //Gas stations of those petitions
     */
  
    private Distribucion CD;

    /*Implementation
    *  pet[0] is a petition from gas[0] and pet[1] from gas[1]
    */
    private ArrayList<Integer> pet;
    private ArrayList<Gasolinera> gas;

    /*Constructor*/
    public IAViajes(Distribucion centro) {
        CD = centro;
        pet = new ArrayList<Integer>();
        gas = new ArrayList<Gasolinera>();
    }


    /*Operaciones*/
    public boolean AddViaje(int dias, Gasolinera g, int i){
        //"i" es la prioridad e indica si va a la posición 0 o 1
        if(pet.size() >= 2 || gas.size() >= 2)
            return false;
        pet.add(i, dias);
        gas.add(i, g);
        if(i == 0)
            System.out.println("Gasoinera: " + g);
        return true;
    }

    public boolean DelViaje(int i){
        if ((i < 0) || (i >= 2)) return false;
        pet.remove(i);
        gas.remove(i);
        return true;
    }

    /*Getters/Funciones Auxiliares*/
    public Distribucion getCD(){
        return CD;
    }

    public Gasolinera getG(int i){
        return gas.get(i);
    }

    public int getX(){
        return CD.getCoordX();
    }

    public int getY(){
        return CD.getCoordY();
    }

    private int distCD_G(Distribucion c, Gasolinera g) { //Distance between a CD and a G
        return Math.abs(c.getCoordX() - g.getCoordX()) - Math.abs(c.getCoordY() - g.getCoordY());
    }

    private int distG_G(Gasolinera G1, Gasolinera G2) { //Distance between two G
        return Math.abs(G1.getCoordX() - G2.getCoordX()) - Math.abs(G1.getCoordY() - G2.getCoordY());
    }

    public int getTotalDistance(){
        if(gas.size() == 1) return 2*distCD_G(CD,gas.get(0));
        else if(gas.size() == 2) return distCD_G(CD,gas.get(0)) + distG_G(gas.get(0),gas.get(1)) + distCD_G(CD,gas.get(1));
        return 0;
    }

    public boolean isFull(){
        return (pet.size() == 2 && gas.size() == 2);
    }



    /*Check state of object*/
    public void estadoViaje(){
        if (pet.size() > 0) System.out.println("      El CD:" + "(" + CD.getCoordX() + "," + CD.getCoordY() + ")" + " tiene programado atender a G:" + "(" + gas.get(0).getCoordX() + "," + gas.get(0).getCoordY() + ")" + " por su petición de " +  pet.get(0) + "dias");
        if (pet.size() > 1) System.out.println("      El CD:" + "(" + CD.getCoordX() + "," + CD.getCoordY() + ")" + " tiene programado atender a G:" + "(" + gas.get(1).getCoordX() + "," + gas.get(1).getCoordY() + ")" + " por su petición de " +  pet.get(1) + "dias");
    }






}

/*private ArrayList<Integer,Gasolinera> pet = new ArrayList<Integer,Gasolinera>(2);*/
    /* Constructor
    public IAViajes(Distribucion centro, ArrayList<Integer> peticiones) {
        CD = centro;
        pet = peticiones;
    }

    public boolean AddPetition(int peticion, Gasolinera g){
        // can't add a petition
        if(pet.size() >= 2)
            return false;
        pet.add(peticion,g);
        return true;
    }*/