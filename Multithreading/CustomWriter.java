import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class CustomWriter {

    private static String filename = "java8.txt";

    public synchronized void printResult(BigDecimal result, double time, String name, int scale) {

        PrintWriter pw = null;
        try {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file, true);
            pw = new PrintWriter(fw);

            pw.println("----------------------------------------------------------------------------------------------------");
            pw.println( name + " calculated to " + scale + " digits after decimal. Time elapsed: " + time + " seconds.");
            pw.println("----------------------------------------------------------------------------------------------------");
            String cipher = result.toString();
            for (int i = 0, size = cipher.length(); i < size; i += 110)
                pw.println(cipher.substring(i, Math.min(i + 110, size)));
            pw.println("----------------------------------------------------------------------------------------------------");
            System.out.println("Finished calculating " + name);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem writing to file");
        } finally {
            pw.close();
        }
    }

    public static String getFilename() {
        return filename;
    }

    public static void setFilename(String filename) {
        CustomWriter.filename = filename;
    }
}
