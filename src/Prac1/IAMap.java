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
    /* Class to represent the state of the problem

    State data structure
    vector of trips assigned*/
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
        System.out.println("Se ha creado el estado incial IAMap (vacio)");
        System.out.println("Tamaño PetNoAt:  " + PetNoAt.size());
    }


    /******OPERADORES******/

    //El CD atenderá la petición de G de "i" dias. Se borra de PetNoAt
    public void AddPet(Distribucion cd, Gasolinera g, int i){
        IAViajes v;
        //Comprobar si existe ya un viaje para ese centro
        int aux = findCD(cd);
        if(aux == -1){
            //Si no existe viaje para ese CD, se crea una nueva entrada
            IAViajes nueva = new IAViajes(cd);
            nueva.AddViaje(i, g, 0);
            Viajes.add(nueva);
            System.out.println("Se ha añadido un nuevo viaje al estado del problema, CD:" + "(" + cd.getCoordX() + "," + cd.getCoordY() + ")" + " atenderá a G:" + "(" + g.getCoordX() + "," + g.getCoordY() + ")" + " por su petición de " + i + " dias.");
            (PetNoAt.get(g)).remove((Integer)i);
            /*OJO!
            	remove(int index)
                    Removes the element at the specified position in this list.
                remove(Object o)
                    Removes the first occurrence of the specified element from this list, if it is present.
             Hago cast a Integer porque quiero borrar la peticion del dia "i", no la peticion en el indice i
             */
        }
        else{
            v = Viajes.get(aux); //Ya hay un viaje programado para CD (y con un espacio libre)
            //Ahora la pregunta es....cuál va antes y cómo lo decidimos?¿?¿?
            v.AddViaje(i, g,1); //Pongo 1 por poner pero si pongo 0 iria este el primero
            System.out.println("(CD ya tenía viajes) Pasará por otra gasolinera en un viaje del estado del problema, CD:" + "(" + cd.getCoordX() + "," + cd.getCoordY() + ")" + " atenderá a G:" + "(" + g.getCoordX() + "," + g.getCoordY() + ")" + " por su petición de " + i + " dias.");
            (PetNoAt.get(g)).remove((Integer)i);
        }
    }








    //Estas dos hay que comprovarlas/arreglarlas

    public void AddViajes(IAViajes v){
        Viajes.add(v);
        //Hay que borrar la peticion de PEtNoAt
    }

    //Borra un viaje existente del estado (un camión dejará de atender una petición)
    public boolean DelViajes(Distribucion cd, Gasolinera g, int i){
        //i = 0, elimina la primera petición, i = 1 la segunda, i = 2 las dos (el objeto entero)
        Iterator t = Viajes.iterator();
        Distribucion tmpcd;
        Boolean found = false;
        IAViajes v;
        //Buscar el viaje para ese centro
        while(!found && t.hasNext()){
            v = (IAViajes)t.next(); //Cast del objeto a tipo IAViajes
            if (cd == v.getCD() && g == v.getG(i)){
                if (i == 2){
                    t.remove(); //Borra el objeto de IAMap
                    return true;
                }
                found = true;
                v.DelPetition(i);
                return true;
            }
        }
        return false; //No existía
    }
    //Falta añadir peticion a PetnoAT!!!!






    /******GETTERS******/
    //Retorna los viajes asignados a un centro
    public IAViajes getViajes(int i){
        return Viajes.get(i);
    }


    /*****Funciones Auxiliares*****/

    //Busca cd en Viajes (si no está devuelve -1)
    //estoy asumiendo que cuando un cd tiene 2 camiones, hay DOS OBJETOS DISTINTOS cd en la misma posición
    //así que el único caso en el que habrá 2 viajes con el mismo CD, será si uno está lleno
    public int findCD(Distribucion cd){
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
