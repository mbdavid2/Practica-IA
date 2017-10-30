package Prac1;

import aima.search.framework.SuccessorFunction;
import aima.search.framework.Successor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IASuccesorSA implements SuccessorFunction {
    public List getSuccessors(Object state) {
        ArrayList retval = new ArrayList();
        IAMap board = (IAMap) state;
        IAMap tmp = null;
        boolean SuccessorFound = false;

        while(!SuccessorFound) {
            //PetNoAt vacio -> Solo podemos hacer Swap
            if (board.petLength() == 0) {
                int V1;
                do {
                    V1 = randInt(0, board.mapLength() - 1);
                } while (board.sizeViajes(V1) == 0);
                int PV1 = randInt(0, board.sizeViajes(V1) - 1);
                int V2;
                do {
                    V2 = randInt(0, board.mapLength() - 1);
                } while (board.sizeViajes(V2) == 0);
                int PV2 = randInt(0, board.sizeViajes(V2) - 1);
                tmp = board.copyState();
                if (tmp.SwapViaje(V1, PV1, V2, PV2)) {
                    SuccessorFound = true;
                    retval.add(new Successor("Ben: " + tmp.benf(), tmp));
                    //System.out.println("Swapped (" + V1 + "," + PV1 + ") with (" + V2 + "," + PV2 + ")" + ", total km: " + tmp.km() + ", Ben: " + tmp.benf());
                }
            }
            //Viajes vacio -> Solo podemos hacer Add
            else if (board.getPetAtendidas() == 0) {
                int V = randInt(0, board.mapLength() - 1);
                int P = randInt(0, board.petLength() - 1);
                tmp = board.copyState();
                if (tmp.AddViaje(V, P)) {
                    //System.out.println("Added " + P + " to " + V + ", total km: " + tmp.km() + ", Ben: " + tmp.benf());
                    SuccessorFound = true;
                    retval.add(new Successor("Ben: " + tmp.benf(), tmp));
                }
            }
            //Podemos hacer cualquier cosa
            else {
                int randomNum = randInt(0, 2);
                if (randomNum == 0) { /****ADD****/
                    int V = randInt(0, board.mapLength() - 1);
                    int P = randInt(0, board.petLength() - 1);
                    tmp = board.copyState();
                    if (tmp.AddViaje(V, P)) {
                        SuccessorFound = true;
                        System.out.println("Added " + P + " to " + V + ", total km: " + tmp.km() + ", Ben: " + tmp.benf());
                        retval.add(new Successor("Ben: " + tmp.benf(), tmp));
                    }
                } else if (randomNum == 1) { /****SWAP**/
                    int V1;
                    do {
                        V1 = randInt(0, board.mapLength() - 1);
                    } while (board.sizeViajes(V1) == 0);
                    int PV1 = randInt(0, board.sizeViajes(V1) - 1);
                    int V2;
                    do {
                        V2 = randInt(0, board.mapLength() - 1);
                    } while (board.sizeViajes(V2) == 0);
                    int PV2 = randInt(0, board.sizeViajes(V2) - 1);
                    tmp = board.copyState();
                    if (tmp.SwapViaje(V1, PV1, V2, PV2)) {
                        SuccessorFound = true;
                        System.out.println("Swapped (" + V1 + "," + PV1 + ") with (" + V2 + "," + PV2 + ")" + ", total km: " + tmp.km() + ", Ben: " + tmp.benf());
                        retval.add(new Successor("Ben: " + tmp.benf(), tmp));
                    }
                }else { /****SWAP**/
                    int V1;
                    do {
                        V1 = randInt(0, board.mapLength() - 1);
                    } while (board.sizeViajes(V1) == 0);
                    int PV1 = randInt(0, board.sizeViajes(V1) - 1);
                    int P = randInt(0, board.petLength() - 1);
                    tmp = board.copyState();
                    if (tmp.SwapPets(V1, PV1, P)) {
                        SuccessorFound = true;
                        System.out.println("Swapped (" + V1 + "," + PV1 + ") with petition (" + P + ")" + ", total km: " + tmp.km() + ", Ben: " + tmp.benf());
                        retval.add(new Successor("Swapped (" + V1 + "," + PV1 + ") with petition (" + P + ")" + ", total km: " + tmp.km() + ", Ben: " + tmp.benf(), tmp));
                    }
                }
            }
        }
        return (retval);
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}