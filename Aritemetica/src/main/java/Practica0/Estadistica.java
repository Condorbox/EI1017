package Practica0;

public class Estadistica {
    public double media(double[] conjunto){
        if (conjunto == null) throw new NullPointerException("Parametros nulos no estan permitidos");
        if (conjunto.length <= 0) throw new ArithmeticException("El conjunto tiene que ser de almenos un elemento");
        double suma = 0;
        for (Double e: conjunto)
            suma += e;
        return suma / conjunto.length;
    }

    public double varianza(double[] conjunto){
        double suma = 0;
        double media = media(conjunto);
        for (Double e: conjunto)
            suma += Math.pow((e-media), 2);
        return suma / conjunto.length;
    }

    public double desviacionEstandar(double[] conjunto){
        return Math.sqrt(varianza(conjunto));
    }
}
