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
        /*****Ejecución normal*****/
        double time = System.currentTimeMillis();
        boolean HillClimb = true;
        CentrosDistribucion cd = new CentrosDistribucion(10, 1, 1234);
        Gasolineras gas = new Gasolineras(100, 1234);

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
        /****INSTANTIATE THE SEARCHAGENT OBJECT****/
        SearchAgent agent = new SearchAgent(p, alg);

        /****RESULTS****/
        System.out.println("Tiempo de ejecución" + (System.currentTimeMillis() - time));
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

    private static int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

}
