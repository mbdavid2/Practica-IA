import Prac1.*;
import IA.Gasolina.Gasolineras;
import IA.Gasolina.CentrosDistribucion;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {

        /****Parametros para Experimentos****/
        int ncd = 0;
        int ngas = 0;
        boolean HillClimb = false;
        int numexperiments = 400;
        ArrayList<Integer> seedsG = new ArrayList<Integer>(numexperiments);
        ArrayList<Integer> seedsCD = new ArrayList<Integer>(numexperiments);
        for (int i = 0; i < numexperiments; i++) {
            seedsG.add(i * 2421 / 23);
            seedsCD.add(i * 3211 / 13);
        }
        /////////////////////////////////////

        //Execució
        for (int i = 0; i < numexperiments; i++) {
            ncd += 10;
            ngas = ncd*10;
            CentrosDistribucion cd = new CentrosDistribucion(ncd, 1, 3378);
            Gasolineras gas = new Gasolineras(ngas, 1456);

            /****ESTADO INICIAL****/
            IAMap map;
            map = new IAMap(cd, gas, false);
            /****CREATE THE PROBLEM OBJECT****/
            Problem p;
            p = new Problem(map,
                    new IASuccesorSA(),
                    new IAGoalTest(),
                    new IAHeuristicFunction());

            /****INSTANTIATE THE SEARCH ALGORITHM****/
            Search alg;
            if (HillClimb) alg = new HillClimbingSearch();
            else alg = new SimulatedAnnealingSearch(10000, 100, 125, 0.01);

            double time = System.currentTimeMillis();

            /****INSTANTIATE THE SEARCHAGENT OBJECT****/
            SearchAgent agent = new SearchAgent(p, alg);
            //printActions(agent.getActions());
            /****RESULTS****/
            System.out.println(ncd + " " + (System.currentTimeMillis() - time));
            //printActions(agent.getActions(), ncd); //Si es SA esto peta xD
            //printInstrumentation(agent.getInstrumentation());
        }
    }
    //}

    private static void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
            System.out.println(" " + property);
        }

    }

    private static void printActions(List actions, int cd) {
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