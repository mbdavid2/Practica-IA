package Prac1;

import aima.search.framework.SuccessorFunction;
import aima.search.framework.Successor;
import java.util.ArrayList;
import java.util.List;

public class IASuccesorFunction implements SuccessorFunction{
    public List getSuccessors(Object state) {
        ArrayList retval = new ArrayList();
        IAMap board = (IAMap) state;
        IAMap tmp = null;
        //Añadir nuevos elementos
        for(int cd1 = 0; cd1 < board.mapLength();++cd1){
            for(int p = 0; p < board.petLength(); ++p) {
                tmp = board.copyState();
                if (tmp.AddViaje(cd1, p)) retval.add(new Successor("Added " + p + " to " + cd1, tmp));
            }
        }
        //Swaps
        for(int i1 = 0; i1 < board.mapLength(); ++i1){
            for(int j1 = 0; j1 < board.sizeViajes(i1); ++j1){
                for(int i2 = i1; i2 < board.mapLength(); ++i2){
                    for(int j2 = 0; j2 < board.sizeViajes(i2); ++j2){
                        if(i1 == i2 && (j1 == j2 || (j1%2 == 0 && j2 == j1+1)));
                        else {
                            tmp = board.copyState();
                            if(tmp.SwapViaje(i1,j1,i2,j2)) retval.add(new Successor("Swapped (" + i1 + ","+j1+ ") with (" +i2 + "," +j2+ ")", tmp));
                        }
                    }
                }
            }
        }
        return (retval);
    }
}
