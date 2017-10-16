package Prac1;

import IA.Gasolina.Gasolinera;
import IA.Gasolina.Gasolineras;

public class IAPet {

    private Gasolinera g;

    private int dias;

    private int beneficio;
    //Un deposito = 1000€
    //Beneficio = 1200€ si dia 0 o 100*(100 - 2^dias) si dia > 0

    /*Constructor*/
    public IAPet(Gasolinera gas, int d) {
        g = gas;
        dias = d;
        beneficio = calc_Ben();
    }

    private int calc_Ben(){
        if (dias == 0) return 1200;
        return 100*(100 - 2^dias);
    }

    public int get_Ben(){
        return beneficio;
    }

    public Gasolinera get_Gas(){
        return g;
    }

    public int get_Dias(){
        return dias;
    }
}
