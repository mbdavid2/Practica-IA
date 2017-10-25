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


public class Main {
    public static void main(String[] args) throws Exception {

        //double time = System.currentTimeMillis();

        CentrosDistribucion cd = new CentrosDistribucion(10, 1, 1234);

        Gasolineras gas = new Gasolineras(100, 1234);

        /****ESTADO INICIAL****/
        IAMap map = new IAMap(cd, gas, false);

        /****CREATE THE PROBLEM OBJECT****/
        Problem p = new Problem(map,
                new IASuccesorSA(),
                new IAGoalTest(),
                new IAHeuristicFunction());

        /****INSTANTIATE THE SEARCH ALGORITHM****/
        //Search alg = new HillClimbingSearch();
        Search alg = new SimulatedAnnealingSearch(1000,10,5,0.01);

        /****INSTANTIATE THE SEARCHAGENT OBJECT****/
        SearchAgent agent = new SearchAgent(p, alg);

        /****RESULTS****/
        System.out.println();
        printActions(agent.getActions());
        printInstrumentation(agent.getInstrumentation());

        //System.out.println("->Execution time: " +  (System.currentTimeMillis() - time));
        //NO VAAAA
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
