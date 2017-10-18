package Prac1;

import aima.search.framework.HeuristicFunction;

public class IAHeuristicFunction implements HeuristicFunction {

    public double getHeuristicValue(Object n){

        return ((IAMap) n).heuristic();
    }

    //ESTO VA EN IAMAP!!!
    /* Heuristic function */
    public double heuristic(){
        int h=0;
        for (IAViajes v: Viajes) {
            h += v.getDist()*2; //1km 2 euros
            h -= v.getBen();
        }
        return h + perdidas;
    }
}