package Prac1;

import IA.Gasolina.Gasolinera;
import IA.Gasolina.Gasolineras;

public class IAPet {

    private Gasolinera g;

    private int dias;

    private int benefici; //En %
    //Tambe pot estar a l'estat: benef total

    /*Constructor*/
    public IAPet(Gasolinera gas, int d) {
        g = gas;
        dias = d;
        benefici = calc_Ben();
    }

    public int calc_Ben(){
        return (100 - 2^dias);
    }
}
