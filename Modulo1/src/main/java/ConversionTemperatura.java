public class ConversionTemperatura {
    public static void main(String[] args) {
        tablaConversiones();
    }

    private static void tablaConversiones(){
        int fahrenheit, reaumur;
        String format;
        System.out.println("|   Celsius   |   Fahrenheit   |   Reaumur   |");
        System.out.println("----------------------------------------------");
        for (int i = 0; i<=100;i+=5){
            fahrenheit = (int)(i*(9f/5f)+32);
            reaumur = (int)(i*(4f/5f));
            format = String.format("|%8d     |%9d       |%8d     |", i, fahrenheit, reaumur);
            System.out.println(format);
        }
        System.out.println("----------------------------------------------");
    }
}
