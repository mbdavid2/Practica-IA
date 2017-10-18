package Prac1;

import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Gasolineras;
import IA.Gasolina.Distribucion;
import IA.Gasolina.Gasolinera;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IAMap {

    private ArrayList<IAViajes> Viajes;

    private Map<Gasolinera, ArrayList<Integer>> PetNoAt;

    static CentrosDistribucion cd;
    
    static Gasolineras gas;

    /****Constructor estado vacio****/
    public IAMap(CentrosDistribucion centros, Gasolineras gasolineras) {
        cd = centros;
        gas = gasolineras;
        Viajes = new ArrayList<IAViajes>();

        Gasolinera g;
        PetNoAt = new HashMap<Gasolinera, ArrayList<Integer>>();
        Iterator t = gas.iterator();
        while(t.hasNext()){
            g = (Gasolinera)t.next();
            PetNoAt.put(g ,g.getPeticiones());
        }
        System.out.println("*Se ha creado el estado incial IAMap (vacío)*");
        System.out.println("->Número de gasolineras con peticiones aún no atendidas:  " + PetNoAt.size());
    }


    /******OPERADORES******/
    public void ProgramarViaje(Distribucion cd, Gasolinera g, int i){
        /**El CD atenderá la petición de G de "i" dias. Se borra de PetNoAt**/
        int aux = findCD_notFull(cd);//Buscar el viaje para ese centro, si no lo encuentra: o no tiene o los que tiene estan llenos
        if(aux == -1){
            //Si no existe viaje para ese CD, se crea una nueva entrada
            IAViajes nueva = new IAViajes(cd);
            nueva.AddViaje(i,g);
            Viajes.add(nueva);
            System.out.println("->Se ha añadido un nuevo viaje al estado del problema, CD:" + "(" + cd.getCoordX() + "," + cd.getCoordY() + ")" + " atenderá a G:" + "(" + g.getCoordX() + "," + g.getCoordY() + ")" + " por su petición de " + i + " dias.");
            (PetNoAt.get(g)).remove((Integer)i);//Atendida
            /*OJO!
            	remove(int index)
                    Removes the element at the specified position in this list.
                remove(Object o)
                    Removes the first occurrence of the specified element from this list, if it is present.
             Hago cast a Integer porque quiero borrar la peticion del dia "i", no la peticion en el indice i
             */
        }
        else{
            IAViajes v = Viajes.get(aux); //Ya hay un viaje programado para CD (y con un espacio libre)
            //Ahora la pregunta es....cuál va antes y cómo lo decidimos?¿?¿?
            v.AddViaje(i,g); //Pongo 1 por poner pero si pongo 0 iria este el primero
            System.out.println("->(CD ya tenía viajes) Pasará por otra gasolinera en un viaje del estado del problema, CD:" + "(" + cd.getCoordX() + "," + cd.getCoordY() + ")" + " atenderá a G:" + "(" + g.getCoordX() + "," + g.getCoordY() + ")" + " por su petición de " + i + " dias.");
            (PetNoAt.get(g)).remove((Integer)i);//Atendida
        }
        //System.out.println("->Número de gasolineras con peticiones aún no atendidas:  " + PetNoAt.size());
    }

    public boolean BorrarViaje(Distribucion cd, Gasolinera g, int i){
        /**Borra un viaje existente del estado (un camión dejará de atender una petición)
        i = 0 elimina la primera petición, i = 1 la segunda**/
        IAViajes v;
        Iterator t = Viajes.iterator();
        while(t.hasNext()){
            v = (IAViajes)t.next(); //v ahora "apunta" al viaje???
            if (cd == v.getCD() && g == v.getG(i)){
                v.DelViaje(i);
                (PetNoAt.get(g)).add(i); //esto esta bien?? con el get no hay aliasing de ese??
                //System.out.println("Número de gasolineras con peticiones aún no atendidas:  " + PetNoAt.size());
                return true;
            }
        }
        return false; //No existe ese viaje
    }

    /*
    public boolean BorrarViaje2(int i, int j){ //0 <= i < Viajes.size(); 0<= j <= 1;
        if(j >= (Viajes.get(i)).getN()) return false;
        Gasolinera g = Viajes.get(i).getG(j);
        int p = Viajes.get(i).getPetition(j);
        (PetNoAt.get(g)).add(p);
        Viajes.DelViaje(j);
        return true;
    }
    */

    public void SwapViaje(Distribucion cd, Gasolinera g, int i){
        //Hay que programarla
    }


    /*****Funciones Auxiliares*****/
    public int findCD_notFull(Distribucion cd){
        //Devuelve el indice del primer "Viaje" de CD que no está lleno (podrá pasar por una gasolinera más)
        IAViajes v;
        for (int i = 0; i < Viajes.size(); i++){
            v = Viajes.get(i);
            if (v.getCD() == cd && !v.isFull()) return i;
        }
        return -1;
    }

    public void printGas(int j){
        if (j == 0) {
            //Para ver como estan guardadas las peticiones EN EL MAP
            Iterator it = PetNoAt.keySet().iterator();
            ArrayList<Integer> petsaux;
            while (it.hasNext()) {
                Gasolinera key = (Gasolinera) it.next();
                petsaux = PetNoAt.get(key);
                System.out.println("Gasolinera de: (" + key.getCoordX() + "," + key.getCoordY() + ")");
                Iterator it2 = petsaux.iterator();
                while (it2.hasNext()) {
                    System.out.println("    -> Petición: " + (Integer) it2.next() + " dias");
                }
            }
        }
        else if (j == 1) {
            //Para ver como estan guardadas las peticiones DE VERDAD
            for (int i = 0; i < 30; i++) {
                ArrayList<Integer> prueba = gas.get(i).getPeticiones();
                Iterator t2 = prueba.iterator();
                System.out.println("Gasolinera de: (" + gas.get(i).getCoordX() + "," + gas.get(i).getCoordY() + ")");
                while (t2.hasNext()) {
                    Integer in = (Integer) t2.next();
                    System.out.println("    -> Petición: " + in + " dias");
                }
            }
        }
    }

    public void printViajes(){
        System.out.println("");
        if (Viajes.size() == 0) System.out.println("Aún no hay viajes programados");
        IAViajes v;
        for (int i = 0; i < Viajes.size(); i++){
            v = Viajes.get(i);
            System.out.println("ESTADO DEL VIAJE: " + v);
            v.estadoViaje();
        }
    }




    /******GETTERS******/
    //Retorna los viajes asignados a un centro
    public IAViajes getViajes(int i){
        return Viajes.get(i);
    }








    //Version antigua de AddPet
    /*public void AddPet(Distribucion cd, int peticion, Gasolinera g){
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
    }*/
}
