import Prac1.IAMap;
import IA.Gasolina.Gasolineras;
import IA.Gasolina.CentrosDistribucion;
import Prac1.IAGoalTest;
import Prac1.IAHeuristicFunction;
import Prac1.IASuccesorFunction;
import Prac1.IASuccesorSA;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {

        /****Parametros para Experimentos****/
        int seedG = randInt(1, 9999);
        int seedCD = randInt(1, 9999);
        int ncd = 10;
        int ngas = 100;
        boolean rellenar = false;
        boolean HillClimb = true;
        /////////////////////////////////////

        double time = System.currentTimeMillis();

        CentrosDistribucion cd = new CentrosDistribucion(ncd, 1, seedG);
        Gasolineras gas = new Gasolineras(ngas, seedCD);

        /****ESTADO INICIAL****/
        IAMap map = new IAMap(cd, gas, rellenar);

        /****CREATE THE PROBLEM OBJECT****/
        Problem p;
        if (HillClimb){
            p = new Problem(map,
                    new IASuccesorFunction(),
                    new IAGoalTest(),
                    new IAHeuristicFunction());
        }
        else {
            p = new Problem(map,
                    new IASuccesorSA(),
                    new IAGoalTest(),
                    new IAHeuristicFunction());
        }

        /****INSTANTIATE THE SEARCH ALGORITHM****/
        Search alg;
        if (HillClimb) alg = new HillClimbingSearch();
        else alg = new SimulatedAnnealingSearch(1000,10,5,0.01);

        /****INSTANTIATE THE SEARCHAGENT OBJECT****/
        SearchAgent agent = new SearchAgent(p, alg);

        /****RESULTS****/
        System.out.println();
        if (HillClimb) printActions(agent.getActions()); //Si es SA esto peta xD
        printInstrumentation(agent.getInstrumentation());

        System.out.println("Time: " +  (System.currentTimeMillis() - time));
        System.out.println("Seeds: " +  seedCD + "_" + seedG);

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

    public static int randInt(int min, int max) {

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
