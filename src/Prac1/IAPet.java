package Prac1;

import IA.Gasolina.Gasolinera;
import IA.Gasolina.Gasolineras;

public class IAPet {

    private Gasolinera g;

    private int dias;

    private int beneficio;
    //Un deposito = 1000€
    //Beneficio = 1020€ si dia 0 o 1000 - 10*(2^dias) si dia > 0
    private int perdidas;

    /*Constructor*/
    public IAPet(Gasolinera gas, int d) {
        g = gas;
        dias = d;
        beneficio = calc_Ben(d);
        perdidas = calc_Perd(d);
    }

    private int calc_Ben(int dias){
        if (dias == 0) return 1200;
        return 1000 - 10*(2^dias);
    }

    private int calc_Perd(int dias){
        return calc_Ben(dias) - calc_Ben(dias+1);
    }

    public int get_Ben(){
        return beneficio;
    }

    public int get_Per(){
        return perdidas;
    }

    public Gasolinera get_Gas(){
        return g;
    }

    public int get_Dias(){
        return dias;
    }
}