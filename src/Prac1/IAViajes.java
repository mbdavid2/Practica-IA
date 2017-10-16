package Prac1;
import IA.Gasolina.Distribucion;
import IA.Gasolina.Gasolinera;
import IA.Gasolina.Gasolineras;

import java.util.ArrayList;

public class IAViajes {
    /*
       CD (CentroDistribucion)
       pet[0] pet[1]
     */
  
    private Distribucion CD;

    /*private ArrayList<Integer> pet;
    private ArrayList<Gasolinera> gas;*/
    private ArrayList<IAPet> pet;

    private int distance;

    /*Constructor*/
    public IAViajes(Distribucion centro) {
        CD = centro;
        pet = new ArrayList<IAPet>();
        distance = 0;
    }


    /*Operaciones*/
    public boolean AddViaje(int dias, Gasolinera g){
        if(pet.size() >= 2) return false;
        pet.add(new IAPet(g, dias));
        distance = getTotalDistance();
        return true;
    }

    public int DelViaje(int i){
        if ((i < 0) || (i >= 2)) return -1;
        int dias = pet.get(i).get_Dias();
        pet.remove(i);
        distance = getTotalDistance();
        return dias;
    }

    /*Getters/Funciones Auxiliares*/
    public Distribucion getCD(){
        return CD;
    }

    public Gasolinera getG(int i){
        return pet.get(i).get_Gas();
    } //0 <= i <= 1

    public int getPetition(int i){
        return pet.get(i).get_Dias();
    }//0 <= i <= 1

    public int getN(){
        return pet.size();
    }

    public int getX(){
        return CD.getCoordX();
    }

    public int getY(){
        return CD.getCoordY();
    }

    private int distCD_G(Distribucion c, Gasolinera g) { //Distance between a CD and a G
        return Math.abs(c.getCoordX() - g.getCoordX()) + Math.abs(c.getCoordY() - g.getCoordY());
    }

    private int distG_G(Gasolinera G1, Gasolinera G2) { //Distance between two G
        return Math.abs(G1.getCoordX() - G2.getCoordX()) + Math.abs(G1.getCoordY() - G2.getCoordY());
    }

    private int getTotalDistance(){
        if(pet.size() == 1) return 2*distCD_G(CD,pet.get(0).get_Gas());
        else if(pet.size() == 2) return distCD_G(CD,pet.get(0).get_Gas()) + distG_G(pet.get(0).get_Gas(),pet.get(1).get_Gas()) + distCD_G(CD,pet.get(1).get_Gas());
        return 0;
    }

    public boolean isFull(){
        return (pet.size() == 2 && pet.size() == 2);
    }



    /*Check state of object*/
    public void estadoViaje(){
        if (pet.size() > 0) System.out.println("      El CD:" + "(" + CD.getCoordX() + "," + CD.getCoordY() + ")" + " tiene programado atender a G:" + "(" + pet.get(0).get_Gas().getCoordX() + "," + pet.get(0).get_Gas().getCoordY() + ")" + " por su petición de " +  pet.get(0).get_Dias() + " dias");
        if (pet.size() > 1) System.out.println("      El CD:" + "(" + CD.getCoordX() + "," + CD.getCoordY() + ")" + " tiene programado atender a G:" + "(" + pet.get(1).get_Gas().getCoordX() + "," + pet.get(1).get_Gas().getCoordY() + ")" + " por su petición de " +  pet.get(1).get_Dias() + " dias");
        System.out.println("      Distancia del viaje: "+distance);
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