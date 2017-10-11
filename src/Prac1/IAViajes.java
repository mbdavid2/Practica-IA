package Prac1;


public class IAViajes {
    /* 
        CD (CentroDistribucion)
     */
  
    private Distribucion CD;
    private ArrayList<int,Gasolinera> pet = new ArrayList<int,Gasolinera>(2);
    
    /* Constructor */
    public IAViajes(Distribucion centro, ArrayList<int> peticiones) {
        CD = centro;
        pet = peticiones;
    }
    
    public boolean AddPetition(int peticion, Gasolinera g){
        // can't add a petition
        if(pet.size() >= 2)
            return false;
        pet.add(peticion,g);
        return true;
    }
}
