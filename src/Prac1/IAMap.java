package Prac1;

import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Gasolineras;
import IA.Gasolina.Gasolinera;

public class IAMap {
    /* Class to represent the state of the problem
        - It implements the state of the problem and its operators
     */

    /* State data structure
        vector of trips assigned
        Trip -> CD + Truck
     */

    /*private ArrayList<IAViajes> Viajes;
    
    //Peticiones no atendidas y la gasolinera asociada
    private ArrayList<int, Gasolinera> PetNoAt;
    
    static CentrosDistribucion cd;
    
    static Gasolineras gas;

    /* Constructor estado vacio
    public IAMap(CentrosDistribucion centros, Gasolineras gasolineras) {
        
        cd = centros;
        gas = gasolineras;
        
        Viajes = new ArrayList<IAViajes>(); 
        
        PetNoAt = new ArrayList<int,Gasolinera>();
        Iterator t = gas.iterator();
        while(t.hasNext()){
            Gasolinera g = t.next();
            ArrayList<int> tmp = g.getPeticiones();
            while(tmp.hasNext())
                PetNoAt.add(tmp.next(),g);
        }
    }*/
    
    
}
