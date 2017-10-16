import Prac1.IAMap;
import Prac1.IAViajes;

import IA.Gasolina.Gasolineras;
import IA.Gasolina.Gasolinera;
import IA.Gasolina.Distribucion;
import IA.Gasolina.CentrosDistribucion;

import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.AStarSearch;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Properties;


public class Main {
    public static void main(String[] args) throws Exception {

        CentrosDistribucion cd = new CentrosDistribucion(10, 1,10);

        Gasolineras gas = new Gasolineras(30, 10);

        //Creación estado inicial (vacío)
        IAMap map = new IAMap(cd, gas);


        /*Pruebas: Estado empieza vacio*/
        map.printGas(0);
        map.ProgramarViaje(cd.get(1),gas.get(2),2); //CD1 atendera a la gasolinera 2 por su peticion de hace 2 dias (aquí porque sé que la Gas2 tiene una pet 2, pero esto hay que comprovarlo antes..)
        map.ProgramarViaje(cd.get(1),gas.get(0),0); //CD1 atendera a la gasolinera 0 por su peticion de hace 0 dias
        map.printViajes();
        map.BorrarViaje(cd.get(1), gas.get(0),1);
        map.printViajes();
        map.printGas(0);









        //Estas cosas son del IAProb5, las dejo por si acaso
/*
        // Create the Problem object
        Problem p = new  Problem(board,
                new ProbIA5SuccesorFunction(),
                new ProbIA5GoalTest(),
                new ProbIA5HeuristicFunction());

        // Instantiate the search algorithm
        // AStarSearch(new GraphSearch()) or IterativeDeepeningAStarSearch()
        Search alg = new AStarSearch(new GraphSearch());

        // Instantiate the SearchAgent object
        SearchAgent agent = new SearchAgent(p, alg);

        // We print the results of the search
        System.out.println();
        printActions(agent.getActions());
        printInstrumentation(agent.getInstrumentation());

        // You can access also to the goal state using the
        // method getGoalState of class Search

    }


    private static void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }

    }

    private static void printActions(List actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            System.out.println(action);
        }
    }
    */
    }
}
