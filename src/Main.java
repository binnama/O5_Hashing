import modules.LCFS;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Scanner input = new Scanner(System.in);
        //System.out.println("Please provide a hash length: ");
        //int hashLength = input.nextInt();
        //System.out.println(hashLength);

        int hashLength = 3000;
        LCFS lcfs = new LCFS(hashLength);

        try
        {
            System.out.println("Datafile: ");
            String datafile = input.next();
            Scanner inputFile = new Scanner(new File(datafile));

            // Middlertidig
            int count = 0;

            // Middlertidig
            while (inputFile.hasNext() && count < 1000)
            {
                lcfs.insert(inputFile.nextLine());
                // Middlertidig
                count++;
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
            System.exit(1);
        }

        // Oppgave 1: Last Come First Serve
        // Skriver ut hashlengde, antall data lest, antall kollisjoner
        // og load factor
        System.out.println("Hashlengde  : " + hashLength);
        System.out.println("Elementer   : " + lcfs.antData());
        System.out.printf( "Load factor : %5.3f\n",  lcfs.loadFactor());
        System.out.println("Probes      : " + lcfs.antProbes());
    }

        //Oppgave 2: Robin Hood

        //Oppgave 3: Fjerning av data
}
