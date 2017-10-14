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


    /*Different implementation
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

    public boolean AddViaje(int dias, Gasolinera g, int i){
        //"i" es la prioridad e indica si va a la posición 0 o 1
        if(pet.size() >= 2 || gas.size() >= 2)
            return false;
        pet.add(i, dias);
        gas.add(i, g);
        return true;
    }

    public boolean DelPetition(int i){
        if (i > 2) return false;
        if (i == 2)
        pet.remove(i);
        gas.remove(i);
        return true;
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

    /*Getters/Funciones Auxiliares*/

    public Distribucion getCD(){
        return CD;
    }

    public int getX(){
        return CD.getCoordX();
    }

    public int getY(){
        return CD.getCoordY();
    }

    public boolean isFull(){
        return (pet.size() == 2 && gas.size() == 2);
    }

    public Gasolinera getG(int i){
        return gas.get(i);
    }

    /*Check state of object*/
    public void checkValues(){
        if (pet.size() > 0) System.out.println("Petition from gas station located at: (" + gas.get(0).getCoordX() + "," + gas.get(0).getCoordY() + ") since: " + pet.get(0) + " days");
        if (pet.size() > 1) System.out.println("Petition from gas station located at: (" + gas.get(1).getCoordX() + "," + gas.get(1).getCoordY() + ") since: " + pet.get(1) + " days");
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