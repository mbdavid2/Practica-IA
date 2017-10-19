import Prac1.IAMap;
import IA.Gasolina.Gasolineras;
import IA.Gasolina.CentrosDistribucion;
import Prac1.IAGoalTest;
import Prac1.IAHeuristicFunction;
import Prac1.IASuccesorFunction;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;


public class Main {
    public static void main(String[] args) throws Exception {

        CentrosDistribucion cd = new CentrosDistribucion(10, 1, 10);

        Gasolineras gas = new Gasolineras(30, 10);

        /****ESTADO INICIAL****/
        IAMap map = new IAMap(cd, gas);

        /*Pruebas: Estado empieza vacio*/
        /*map.AddViaje(1,3);
        map.AddViaje(1,8);
        map.AddViaje(1,7);
        map.AddViaje(2, 5);

        map.printViajes();*/


        /****CREATE THE PROBLEM OBJECT****/
        Problem p = new Problem(map,
                new IASuccesorFunction(),
                new IAGoalTest(),
                new IAHeuristicFunction());

        /****INSTANTIATE THE SEARCH ALGORITHM****/
        Search alg = new HillClimbingSearch();

        /****INSTANTIATE THE SEARCHAGENT OBJECT****/
        SearchAgent agent = new SearchAgent(p, alg);

        /****RESULTS****/
        System.out.println();
        printActions(agent.getActions());
        printInstrumentation(agent.getInstrumentation());
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
}


