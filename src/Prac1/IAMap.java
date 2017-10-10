package Prac1;

import IA.Gasolina.CentrosDistribucion;
import IA.Gasolina.Gasolineras;

public class IAMap {
    /* Class to represent the state of the problem
        - It implements the state of the problem and its operators
     */

    /* State data structure
        vector of trips assigned
        Trip -> CD + Truck
     */

    private int [] map; //int¿?¿?¿?¿

    /* Constructor */
    public IAMap(CentrosDistribucion cd, Gasolineras gas) {

        /*Two options:
            - fill with random trips
            - leave all trips empty
         */

        //Empty Trips//
        map = new int[cd.size()]; //for example, CentrosDistribucion inherits "size" from ArrayList

    }
}
