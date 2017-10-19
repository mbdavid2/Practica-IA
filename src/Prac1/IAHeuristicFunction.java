package Prac1;

import aima.search.framework.HeuristicFunction;

public class IAHeuristicFunction implements HeuristicFunction {

    public double getHeuristicValue(Object n){
        return ((IAMap) n).heuristic();
    }

}