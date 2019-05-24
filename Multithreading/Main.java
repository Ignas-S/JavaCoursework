import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {

        CustomWriter cw = new CustomWriter();
        CustomWriter.setFilename("java8.txt");

        PiCalculator picalc = new PiCalculator(cw);
        picalc.setScale(120000);

        EPowerCalculator epowercalc = new EPowerCalculator(cw);
        epowercalc.setScale(485);
        epowercalc.setPowerX(2000);

        File f = new File("java8.txt");
        if(f.exists()){
            f.delete();
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.print("Inputs:\n0 - start a new pi calculation\n1 - start a new e^x calculation\n9 - end program\n");
        int input;
        Scanner in = new Scanner(System.in);
        do {
            input = in.nextInt();
            if(input == 0) new Thread(picalc).start();
            if(input == 1) new Thread(epowercalc).start();
        } while(input != 9);


    }
}
