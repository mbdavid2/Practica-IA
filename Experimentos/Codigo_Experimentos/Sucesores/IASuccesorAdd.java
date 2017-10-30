package Prac1;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

import java.util.ArrayList;
import java.util.List;

public class IASuccesorAdd implements SuccessorFunction{
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
        return (retval);
    }
}
