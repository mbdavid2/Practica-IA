package Prac1;

import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Gasolineras;
import IA.Gasolina.Distribucion;
import IA.Gasolina.Gasolinera;

import java.util.ArrayList;
import java.util.Iterator;

public class IAMap {
    /* Class to represent the state of the problem
*/

    /* State data structure
       vector of trips assigned*/
    private ArrayList<IAViajes> Viajes;
    
    //Peticiones no atendidas y la gasolinera asociada
    //private ArrayList<int, Gasolinera> PetNoAt;
    
    static CentrosDistribucion cd;
    
    static Gasolineras gas; //No tendria que ser private? porque cada gasolinera tiene sus peticiones que iran variando en los distintos estados

    /*Constructor estado vacio*/
    public IAMap(CentrosDistribucion centros, Gasolineras gasolineras) {
        
        cd = centros;
        gas = gasolineras;
        
        Viajes = new ArrayList<IAViajes>(); 
        
        /*PetNoAt = new ArrayList<int,Gasolinera>();
        Iterator t = gas.iterator();
        while(t.hasNext()){
            Gasolinera g = t.next();
            ArrayList<int> tmp = g.getPeticiones();
            while(tmp.hasNext())
                PetNoAt.add(tmp.next(),g);
        }*/
    }

    public void AddViajes(IAViajes v){
        Viajes.add(v);
    }

    //Retorna los viajes assignados a un centro
    public IAViajes getViajes(int i){
        return Viajes.get(i);
    }

    //Hay que revisarla
    public void AddPet(Distribucion cd, int peticion, Gasolinera g){
        Iterator t = Viajes.iterator();
        Distribucion tmpcd;
        Boolean found = false;
        IAViajes v;
        //Comprobar si existe ya un viaje para ese centro
        while(!found && t.hasNext()){
            v = (IAViajes)t.next(); //Cast del objeto a tipo IAViajes
            if (cd == v.getCD()){
                found = true;
            }
        }
        if (!found){
            //Si no existe NINGUN viaje para ese CD, se crea una nueva entrada
            IAViajes nueva = new IAViajes(cd);
            nueva.AddPetition(peticion,g);
            Viajes.add(nueva);
        }
        else {
            //Pero si lo encuentra...a cual añade la peticion¿?¿?
        }
    }
}
