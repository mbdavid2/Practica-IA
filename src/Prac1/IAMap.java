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

    //private Map<Gasolinera, ArrayList<Integer>> PetNoAt;

    private ArrayList<IAPet> PetNoAt;

    static CentrosDistribucion cd;
    
    static Gasolineras gas;

    private int perd; //Implementar: perdidas de pet no atendidas

    /****Constructor estado vacio****/
    public IAMap(CentrosDistribucion centros, Gasolineras gasolineras) {
        cd = centros;
        gas = gasolineras;
        Viajes = new ArrayList<IAViajes>();
        Gasolinera g;
        PetNoAt = new ArrayList<IAPet>();
        ArrayList<Integer> petaux;
        Iterator t = gas.iterator();
        Iterator aux;
        IAPet pet;
        while(t.hasNext()){
            g = (Gasolinera)t.next();
            petaux = g.getPeticiones();
            aux = petaux.iterator();
            while(aux.hasNext()) {
                pet = new IAPet(g, (Integer)aux.next());
                PetNoAt.add(pet);
                perd += pet.get_Ben();
            }
        }
        System.out.println("*Se ha creado el estado incial IAMap (vacío)*");
    }


    /******OPERADORES******/
    public void ProgramarViaje(Distribucion cd, Gasolinera g, int i){
        /**El CD atenderá la petición de G de "i" dias. Se borra de PetNoAt**/
        int aux = findViaje_notFull(cd);
        if(aux == -1){
            //Si no existe viaje para ese CD, se crea una nueva entrada
            IAViajes nueva = new IAViajes(cd);
            nueva.AddViaje(i, g);
            Viajes.add(nueva);
            System.out.println("->Se ha añadido un nuevo viaje al estado del problema, CD:" + "(" + cd.getCoordX() + "," + cd.getCoordY() + ")" + " atenderá a G:" + "(" + g.getCoordX() + "," + g.getCoordY() + ")" + " por su petición de " + i + " dias.");
            boolean found = false;
            IAPet pet;
            for (int j = 0; j < PetNoAt.size(); j++){
                pet = PetNoAt.get(j);
                if(!found) {
                    if (g.equals(pet.get_Gas()) && i == pet.get_Dias()) {
                        perd -= pet.get_Ben();
                        PetNoAt.remove(pet);//Borra así con el objeto o mejor encontrar el indice¿?
                        found = true;
                    }
                    //POco eficiente porque lo recorre todo
                }
            }
        }
        else{
            IAViajes v = Viajes.get(aux);
            v.AddViaje(i, g);
            System.out.println("->(CD ya tenía viajes) Pasará por otra gasolinera en un viaje del estado del problema, CD:" + "(" + cd.getCoordX() + "," + cd.getCoordY() + ")" + " atenderá a G:" + "(" + g.getCoordX() + "," + g.getCoordY() + ")" + " por su petición de " + i + " dias.");
            boolean found = false;
            IAPet pet;
            for (int j = 0; j < PetNoAt.size(); j++){
                pet = PetNoAt.get(j);
                if(!found) {
                    if (g.equals(pet.get_Gas()) && i == pet.get_Dias()) {
                        perd -= pet.get_Ben();
                        PetNoAt.remove(pet);//Borra así con el objeto o mejor encontrar el indice¿?
                        found = true;
                    }
                }
            }
        }
        System.out.println("->Número de peticiones aún no atendidas:  " + PetNoAt.size());
        System.out.println("->Pérdidas por no atenderlas:  " + perd);
        System.out.println("");
    }

    public boolean BorrarViaje(Distribucion cd, Gasolinera g, int i){
        /**Borra un viaje existente del estado (un camión dejará de atender una petición)
        i = 0 elimina la primera petición, i = 1 la segunda**/
        IAViajes v;
        Iterator t = Viajes.iterator();
        IAPet pet;
        while(t.hasNext()){
            v = (IAViajes)t.next(); //v ahora "apunta" al viaje??? si
            if (cd.equals(v.getCD()) && g.equals(v.getG(i))){
                int dias = v.DelViaje(i);
                pet = new IAPet(g, dias);
                PetNoAt.add(pet);
                System.out.println("");
                System.out.println("Viaje borrado");
                return true;
            }
        }
        return false;
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
    public int findViaje_notFull(Distribucion cd){
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
            Iterator it = PetNoAt.iterator();
            int i = 0;
            IAPet pet;
            while (it.hasNext()) {
                pet = (IAPet) it.next();
                Gasolinera gas = pet.get_Gas();
                int dias = pet.get_Dias();
                System.out.println("Gasolinera de: (" + gas.getCoordX() + "," + gas.getCoordY() + ")");
                System.out.println("    -> Petición: " + dias + " dias");
            }

        }
        else if (j == 1) {
            //Para ver como estan guardadas las peticiones DE VERDAD
            for (int i = 0; i < gas.size(); i++) {
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

}
