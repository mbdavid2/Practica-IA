package Prac1;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

import java.util.ArrayList;
import java.util.List;

public class IASuccesorAS2 implements SuccessorFunction{
    public List getSuccessors(Object state) {
        ArrayList retval = new ArrayList();
        IAMap board = (IAMap) state;
        IAMap tmp = null;
        /****ADD****/
       for(int cd1 = 0; cd1 < board.mapLength();++cd1){
            for(int p = 0; p < board.petLength(); ++p) {
                tmp = board.copyState();
                //if (tmp.AddViaje(cd1, p)) retval.add(new Successor("Added " + p + " to " + cd1 + ", total km: " + tmp.km() + ", Ben: " + tmp.benf(), tmp));
                if (tmp.AddViaje(cd1, p)) retval.add(new Successor("" + tmp.benf(), tmp));
                //System.out.println("Try: Added " + p + " to " + cd1 + ", km: " + tmp.km() + ", Ben: " + tmp.benf());
                //tmp.printViajes();
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
