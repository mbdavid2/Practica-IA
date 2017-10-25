package Prac1;

import aima.search.framework.SuccessorFunction;
import aima.search.framework.Successor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IASuccesorSA implements SuccessorFunction{
    public List getSuccessors(Object state) {
        ArrayList retval = new ArrayList();
        IAMap board = (IAMap) state;
        IAMap tmp = null;
        tmp = board.copyState();
        
        int randomNum = randInt(0, 2);
        int V1 = randInt(0, board.mapLength());
        int PV1 = randInt(0, board.sizeViajes(V1));
        int V2;
        int PV2;
        do{
            V2 = randInt(0, board.mapLength());
            PV2 = randInt(0, board.sizeViajes(V2));
        }while (!(V1 == V2 && (PV2 <= PV1 || (PV1%2 == 0 && PV2 == PV1+1))));
        
        int P = randInt(0, board.petLength()-1);
                
                
        if (randomNum == 0){ //Add
            if (tmp.AddViaje(V1, P)) retval.add(new Successor("Added " + P + " to " + V1 + ", total km: " + tmp.km() + ", Ben: " + tmp.benf(), tmp));   
        }
        else if (randomNum == 1) {//Swap
            if(tmp.SwapViaje(V1,PV1,V2,PV2)) retval.add(new Successor("Swapped (" + V1+ ","+PV1+ ") with (" +V2 + "," +PV2+ ")" + ", total km: " + tmp.km() + ", Ben: " + tmp.benf(), tmp)); 
            
        }
        else { //SwapPets
            if(tmp.SwapPets(V1,PV1,P)) retval.add(new Successor("Swapped (" + V1 + ","+PV1+ ") with petition (" +P+ ")" + ", total km: " + tmp.km() + ", Ben: " + tmp.benf(), tmp));
        }
        
        
       if(retval.size() == 0)
           retval.add(board.copyState());
        //System.out.println(" ");
        return (retval);
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
