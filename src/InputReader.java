import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by bimurto on 19-Nov-15.
 */
public class InputReader {

    public InputReader(){
        readFile();
    }

    public void readFile(){
        int counter=0, type, nomatchX=0, nomatchY=0;
        double x1,x2,y1,y2;
        int c=0;
        Random random = new Random();
        FileWriter writer = null;
        File file = new File("CalPOIXY.txt");
        try {
            writer = new FileWriter(new File("Input3d.txt"));
            Scanner input = new Scanner(file);
            while(input.hasNext()){
                counter = input.nextInt();
                x1 = input.nextDouble();
                x2 = input.nextDouble();
                y1 = input.nextDouble();
                y2 = input.nextDouble();
                type = input.nextInt();
                if(x1 != x2) nomatchX++;
                if(y1 != y2) nomatchY++;
                int a = random.nextInt(5)+1;
                int b = random.nextInt(10)+1;
                c++;
               // writer.write(x1+" "+y1+" "+a+" "+b+" "+type+"\n");
                writer.write(x1+" "+y1+" "+a+" "+type+"\n");
               // writer.write(x1+" "+y1+" "+type+"\n");
            }
            writer.close();
            System.out.println(counter+ " "+nomatchX+" "+nomatchY);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]){
        new InputReader();
    }
}
