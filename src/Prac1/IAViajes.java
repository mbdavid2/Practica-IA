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
    *  pet[0] from gas[0] and pet[1] from gas[1]
    */
    private ArrayList<Integer> pet = new ArrayList<Integer>();
    private ArrayList<Gasolinera> gas = new ArrayList<Gasolinera>();

    /*Constructor*/
    public IAViajes(Distribucion centro) {
        CD = centro;
    }

    public boolean AddPetition(int peticion, Gasolinera g){
        //Can't add a petition
        if(pet.size() >= 2 || gas.size() >= 2)
            return false;
        pet.add(peticion);
        gas.add(g);
        return true;
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

    /*Check state of object*/
    public void checkValues(){
        if (pet.size() > 0) System.out.println("Petition: " + pet.get(0) + " from gas station located at: (" + gas.get(0).getCoordX() + "," + gas.get(0).getCoordY() + ")");
        if (pet.size() > 1) System.out.println("          " + pet.get(1) + " from gas station located at: (" + gas.get(1).getCoordX() + "," + gas.get(1).getCoordY() + ")");
    }

    /*Getters for the coordiºnates of de CD*/
    public int getX(){
        return CD.getCoordX();
    }

    public int getY(){
        return CD.getCoordY();
    }
}
