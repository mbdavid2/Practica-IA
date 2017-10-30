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
        int ncd = 10;
        int ngas = 100;
        boolean rellenar = false;
        boolean HillClimb = true;
        int numexperiments = 20;
        ArrayList<Integer> seedsG = new ArrayList<Integer>(numexperiments);
        ArrayList<Integer> seedsCD = new ArrayList<Integer>(numexperiments);
        for (int i = 0; i < numexperiments; i++) {
            seedsG.add(i, randInt(1, 9999));
            seedsCD.add(i, randInt(1, 9999));
        }
        /////////////////////////////////////

        //Execució
        int max;
        for (int j = 0; j < 2; j++) { //Dos tipos distintos
            for (int i = 0; i < numexperiments; i++) {
                int seedG = seedsG.get(i);
                int seedCD = seedsCD.get(i);

                CentrosDistribucion cd;
                if (j == 0) cd = new CentrosDistribucion(10, 1, seedG);
                else cd = new CentrosDistribucion(5, 2, seedG);
                Gasolineras gas = new Gasolineras(ngas, seedCD);

                /****ESTADO INICIAL****/
                IAMap map;
                map = new IAMap(cd, gas, false);

                /****CREATE THE PROBLEM OBJECT****/
                Problem p;
                    p = new Problem(map,
                            new IASuccesorFunction(),
                            new IAGoalTest(),
                            new IAHeuristicFunction());

                /****INSTANTIATE THE SEARCH ALGORITHM****/
                Search alg;
                if (HillClimb) alg = new HillClimbingSearch();
                else alg = new SimulatedAnnealingSearch(1000, 10, 5, 0.01);

                /****INSTANTIATE THE SEARCHAGENT OBJECT****/
                SearchAgent agent = new SearchAgent(p, alg);

                /****RESULTS****/
                if (j == 0 && i == 0) System.out.println("10 CDs:");
                if (j == 1 && i == 0) System.out.println("5 CDs:");
                if (HillClimb) printActions(agent.getActions()); //Si es SA esto peta xD
                printInstrumentation(agent.getInstrumentation());
            }
        }
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

    private static void printActions(List actions) {
        /*for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            System.out.println(action);
        }
        //Solo muestra ultimo??¿*/
        String action = (String) actions.get(actions.size()-1);
        System.out.print(action);

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