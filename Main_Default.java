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

        CentrosDistribucion cd = new CentrosDistribucion(10, 1, 1234);
        Gasolineras gas = new Gasolineras(100, 1234);

        /****ESTADO INICIAL****/
        IAMap map = new IAMap(cd, gas, false);

        /****CREATE THE PROBLEM OBJECT****/
        Problem p = new Problem(map,
                new IASuccesorFunction(),
                new IAGoalTest(),
                new IAHeuristicFunction());

        /****INSTANTIATE THE SEARCH ALGORITHM****/
        Search alg = new HillClimbingSearch();
        //Search alg = new SimulatedAnnealingSearch(1000, 10, 5, 0.01);

        /****INSTANTIATE THE SEARCHAGENT OBJECT****/
        SearchAgent agent = new SearchAgent(p, alg);

        /****RESULTS****/
        System.out.println();
        double t = (System.currentTimeMillis() - time);
        printActions(agent.getActions(), t);
        printInstrumentation(agent.getInstrumentation());
    }

    private static void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            //System.out.println(key + " : " + property);
            System.out.println(" " + property);
        }

    }

    private static void printActions(List actions, double t) {
        /*for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            System.out.println(action);
        }
        //Solo muestra ultimo??¿*/
        String action = (String) actions.get(actions.size() - 1);
        System.out.print(action + " " + t);

    }

    private static int randInt(int min, int max) {

        // NOTE: This will (intentionally) not run as written so that folks
        // copy-pasting have to think about how to initialize their
        // Random instance.  Initialization of the Random instance is outside
        // the main scope of the question, but some decent options are to have
        // a field that is initialized once and then re-used as needed or to
        // use ThreadLocalRandom (if using at least Java 1.7).
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

}
