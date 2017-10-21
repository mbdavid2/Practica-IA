package Prac1;

import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Gasolineras;
import IA.Gasolina.Distribucion;
import IA.Gasolina.Gasolinera;

import java.util.ArrayList;
import java.util.Iterator;

public class IAMap {

    static CentrosDistribucion cd;
    
    static Gasolineras gas;

    private ArrayList<IAViajes> Viajes;

    private ArrayList<IAPet> PetNoAt;

    private int perdidas; //Perdidas totales de pet no atendidas

    /****CONSTRUCTORES****/
    public IAMap(CentrosDistribucion centros, Gasolineras gasolineras) {
        cd = centros;
        gas = gasolineras;
        Viajes = new ArrayList<IAViajes>();
        Iterator c = centros.iterator();
        //Un IAViajes para cada CD
        while (c.hasNext()) {
            Viajes.add(new IAViajes((Distribucion) c.next()));
        }
        //Guardar PetNoAt//
        PetNoAt = new ArrayList<IAPet>();
        ArrayList<Integer> petaux;
        Iterator t = gas.iterator();
        Iterator aux;
        IAPet pet;
        Gasolinera g;
        while (t.hasNext()) {
            g = (Gasolinera) t.next();
            petaux = g.getPeticiones();
            aux = petaux.iterator();
            while (aux.hasNext()) {
                pet = new IAPet(g, (Integer) aux.next());
                PetNoAt.add(pet);
                perdidas += pet.get_Per();
            }
        }
        System.out.println("*Se ha creado el estado incial IAMap (vacío)*");
        printGas(0);
        printViajes();
    }

    public IAMap(CentrosDistribucion c, Gasolineras g,ArrayList<IAViajes> V, ArrayList<IAPet> P,int perd )  {
        cd = c;
        gas = g;
        perdidas = perd;
        Viajes = new ArrayList<IAViajes>();
        for(int i = 0; i < V.size(); i++) {
            Viajes.add(V.get(i).copyViaje());
        }
        PetNoAt = new ArrayList<IAPet>();
        for(int i = 0; i < P.size(); i++){
            PetNoAt.add(P.get(i));
        }
    }

    public IAMap copyState(){
        return new IAMap(cd,gas,Viajes,PetNoAt,perdidas);
    }

    /****GETTERS****/
    public int mapLength(){return Viajes.size();}
    public int petLength(){return PetNoAt.size();}
    public int sizeViajes(int i){return Viajes.get(i).size();}

    /******OPERADORES******/
    public boolean AddViaje(int v, int p){
        IAPet pet = PetNoAt.get(p);
        perdidas -= pet.get_Per();
        PetNoAt.remove(p);
        return Viajes.get(v).AddPet(pet);
    }

    public boolean SwapViaje(int i1, int j1, int i2, int j2){
        IAPet p1 = Viajes.get(i1).getPetition(j1);
        IAPet p2 =Viajes.get(i2).getPetition(j2);
        return Viajes.get(i2).swapPet(j2,p1) && Viajes.get(i1).swapPet(j1,p2);
    }

    /****HEURISTICO****/
    public double heuristic(){
        int h=0;
        for (IAViajes v: Viajes) {
            h += v.getDistanciaTotal()*2; //1km 2 euros
            h -= v.getBeneficioTotal();
        }
        return h + perdidas;
    }

    public double benf(){
        int h=0;
        for (IAViajes v: Viajes) {
            h -= v.getDistanciaTotal()*2; //1km 2 euros
            h += v.getBeneficioTotal();
        }
        return h;
    }

    public double km(){
        int h=0;
        for (IAViajes v: Viajes) {
            h += v.getDistanciaTotal()*2; //1km 2 euros
        }
        return h;
    }

    /****PRINTS****/
    public void printGas(int j){
        if (j == 0) {
            Iterator it = PetNoAt.iterator();
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


}

