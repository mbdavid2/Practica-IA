import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Gasolineras;
import Prac1.*;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        /*****Parametros*****/
        boolean HillClimb = false;
        int ncd = 10;
        int ngas = 100;
        CentrosDistribucion cd = new CentrosDistribucion(ncd, 1, 1234);
        Gasolineras gas = new Gasolineras(ngas, 1234);

        /****ESTADO INICIAL****/
        IAMap map = new IAMap(cd, gas, false);
        Problem p;
        Search alg;
        if (HillClimb) {
            /****CREATE THE PROBLEM OBJECT****/
            p = new Problem(map,
                    new IASuccesorFunction(),
                    new IAGoalTest(),
                    new IAHeuristicFunction());

            /****INSTANTIATE THE SEARCH ALGORITHM****/
            alg = new HillClimbingSearch();
        }
        else{
            /****CREATE THE PROBLEM OBJECT****/
            p = new Problem(map,
                    new IASuccesorSA(),
                    new IAGoalTest(),
                    new IAHeuristicFunction());

            /****INSTANTIATE THE SEARCH ALGORITHM****/
            alg = new SimulatedAnnealingSearch(10000, 10, 125, 0.01);

        }
        double time = System.currentTimeMillis();

        /****INSTANTIATE THE SEARCHAGENT OBJECT****/
        SearchAgent agent = new SearchAgent(p, alg);

        /****RESULTS****/
        if (HillClimb) printActions(agent.getActions());
        printInstrumentation(agent.getInstrumentation());
        System.out.println("Execution time: " + (System.currentTimeMillis() - time) + " ms");
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
