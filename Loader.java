import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader {

    public static BackEnd backend = new BackEnd();
    private ArrayList<String> PLayerNames = new ArrayList<String>();
    private ArrayList<Integer> Ratings = new ArrayList<Integer>();
    private ArrayList<Integer> JerseyNumbers = new ArrayList<Integer>();

    public Loader() {

    }



    public boolean LoadData() {
      //File directory = new File("/Users/lucy/eclipse-workspace./");
      
        File file = null;
        Scanner scan = null;
        try {
            file = new File("src/Player-Data.txt");
            scan = new Scanner(file);
        } catch (Exception e) {
            System.out.println("Failed to load data file.");
        }

        while (scan != null &&scan.hasNext()) {
            PLayerNames.add(scan.next() + " " + scan.next());
            JerseyNumbers.add(scan.nextInt());
            Ratings.add(scan.nextInt());
        }

        for(int i=0; i< PLayerNames.size(); i++) {
            backend.addPlayer(new Player(PLayerNames.get(i), JerseyNumbers.get(i), Ratings.get(i)));
        }


        return true;
    }


    public void printDB() {

        for (int i=0; i<PLayerNames.size(); i++) {
            System.out.println(PLayerNames.get(i) + " " + JerseyNumbers.get(i) + " " + Ratings.get(i));

        }

    }





}
