import java.util.Random;

public class RandomByte {
    public static void main(String[] args) {
        Random rn = new Random();
        final int maxOutput = 50;
        for(int i = 0; i<maxOutput; i++){
            int random = rn.nextInt(1-0+1);
            System.out.println("El número random es: " + random);
            if (random==1) break;
        }
    }
}
