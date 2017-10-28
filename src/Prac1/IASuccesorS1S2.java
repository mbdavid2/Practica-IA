package Prac1;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

import java.util.ArrayList;
import java.util.List;

public class IASuccesorS1S2 implements SuccessorFunction{
    public List getSuccessors(Object state) {
        ArrayList retval = new ArrayList();
        IAMap board = (IAMap) state;
        IAMap tmp = null;

        /****SWAP1****/
        for(int i1 = 0; i1 < board.mapLength(); ++i1){
            for(int j1 = 0; j1 < board.sizeViajes(i1); ++j1){
                for(int i2 = i1; i2 < board.mapLength(); ++i2){
                    for(int j2 = 0; j2 < board.sizeViajes(i2); ++j2){
                        if(i1 == i2 && (j2 <= j1 || (j1%2 == 0 && j2 == j1+1)));
                        else {
                            tmp = board.copyState();
                            if(tmp.SwapViaje(i1,j1,i2,j2)) retval.add(new Successor("" + tmp.benf(), tmp));
                            //if(tmp.SwapViaje(i1,j1,i2,j2)) retval.add(new Successor("Swapped (" + i1 + ","+j1+ ") with (" +i2 + "," +j2+ ")" + ", total km: " + tmp.km() + ", Ben: " + tmp.benf(), tmp));
                            //System.out.println("Try: Swapped (" + i1 + ","+j1+ ") with (" +i2 + "," +j2+ ")");
                        }
                    }
                }
            }
        }
        /****SWAP2****/
        for(int i1 = 0; i1 < board.mapLength(); ++i1){
            for(int j1 = 0; j1 < board.sizeViajes(i1); ++j1){
                for(int p = 0; p < board.petLength(); ++p){
                    tmp = board.copyState();
                    //if(tmp.SwapPets(i1,j1,p)) retval.add(new Successor("Swapped (" + i1 + ","+j1+ ") with petition (" +p+ ")" + ", total km: " + tmp.km() + ", Ben: " + tmp.benf(), tmp));
                    if(tmp.SwapPets(i1,j1,p)) retval.add(new Successor("" + tmp.benf(), tmp));
                    //System.out.println("Try: Swapped (" + i1 + ","+j1+ ") with (" +i2 + "," +j2+ ")");
                }
            }
        }
        return (retval);
    }
}
