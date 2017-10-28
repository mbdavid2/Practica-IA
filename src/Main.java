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
        for (int inicial = 0; inicial < 2; inicial++) {
            if (inicial == 0) {
                max = 4;
                System.out.println("Vacio:");
            }
            else {
                max = 7;
                System.out.println("Lleno:");
            }
            for (int c = 0; c < max; c++) {
                boolean first = true;
                for (int i = 0; i < numexperiments; i++) {
                    int seedG = seedsG.get(i);
                    int seedCD = seedsCD.get(i);
                    //System.out.println("hola: " + seedCD + seedG);
                    double time = System.currentTimeMillis();
                    CentrosDistribucion cd = new CentrosDistribucion(ncd, 1, seedG);
                    Gasolineras gas = new Gasolineras(ngas, seedCD);

                    /****ESTADO INICIAL****/
                    IAMap map;
                    if (inicial == 0){
                        map = new IAMap(cd, gas, false);
                    }
                    else {
                        map = new IAMap(cd, gas, true);
                    }
                    /****CREATE THE PROBLEM OBJECT****/
                    Problem p;
                    if (HillClimb) {
                        if (c == 0) {
                            if (first == true) {
                                System.out.println("");
                                System.out.println("Add:");
                                System.out.println("");
                                first = false;
                            }
                            p = new Problem(map,
                                    new IASuccesorAdd(),
                                    new IAGoalTest(),
                                    new IAHeuristicFunction());
                        } else if (c == 1) {
                            if (first == true) {
                                System.out.println("");
                                System.out.println("Add + Swap1:");
                                System.out.println("");
                                first = false;
                            }
                            p = new Problem(map,
                                    new IASuccesorAS1(),
                                    new IAGoalTest(),
                                    new IAHeuristicFunction());
                        } else if (c == 2) {
                            if (first == true) {
                                System.out.println("");
                                System.out.println("Add + Swap2: ");
                                System.out.println("");
                                first = false;
                            }
                            p = new Problem(map,
                                    new IASuccesorAS2(),
                                    new IAGoalTest(),
                                    new IAHeuristicFunction());
                        } else if (c == 3) {
                            if (first == true) {
                                System.out.println("");
                                System.out.println("Add + Swap1 + Swap2: ");
                                System.out.println("");
                                first = false;
                            }
                            p = new Problem(map,
                                    new IASuccesorAS1S2(),
                                    new IAGoalTest(),
                                    new IAHeuristicFunction());
                        }
                        //Explusivos de "lleno"
                        else if (c == 4) {
                            if (first == true) {
                                System.out.println("");
                                System.out.println("Lleno + Swap1: ");
                                System.out.println("");
                                first = false;
                            }
                            p = new Problem(map,
                                    new IASuccesorS1(),
                                    new IAGoalTest(),
                                    new IAHeuristicFunction());
                        } else if (c == 5) {
                            if (first == true) {
                                System.out.println("");
                                System.out.println("Lleno + Swap2: ");
                                System.out.println("");
                                first = false;
                            }
                            p = new Problem(map,
                                    new IASuccesorS2(),
                                    new IAGoalTest(),
                                    new IAHeuristicFunction());
                        }else if (c == 6) {
                            if (first == true) {
                                System.out.println("");
                                System.out.println("Lleno + Swap1 + Swap2: ");
                                System.out.println("");
                                first = false;
                            }
                            p = new Problem(map,
                                    new IASuccesorS1S2(),
                                    new IAGoalTest(),
                                    new IAHeuristicFunction());
                        } else {
                            System.out.println("aqui no tiene que entrar");
                            p = new Problem(map,
                                    new IASuccesorFunction(),
                                    new IAGoalTest(),
                                    new IAHeuristicFunction());
                        }
                    } else {
                        p = new Problem(map,
                                new IASuccesorSA(),
                                new IAGoalTest(),
                                new IAHeuristicFunction());
                    }

                    /****INSTANTIATE THE SEARCH ALGORITHM****/
                    Search alg;
                    if (HillClimb) alg = new HillClimbingSearch();
                    else alg = new SimulatedAnnealingSearch(1000, 10, 5, 0.01);

                    /****INSTANTIATE THE SEARCHAGENT OBJECT****/
                    SearchAgent agent = new SearchAgent(p, alg);

                    /****RESULTS****/
                    //System.out.println();
                    double t = (System.currentTimeMillis() - time);
                    if (HillClimb) printActions(agent.getActions(), t); //Si es SA esto peta xD
                    printInstrumentation(agent.getInstrumentation());

                    //System.out.println("Time: " + (System.currentTimeMillis() - time));
                    //System.out.println("Seeds: " + seedCD + "_" + seedG);
                }
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

    private static void printActions(List actions, double t) {
        /*for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            System.out.println(action);
        }
        //Solo muestra ultimo??¿*/
        String action = (String) actions.get(actions.size()-1);
        System.out.print(action + " " + t);

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
