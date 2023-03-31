import modules.RemoveElements;

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
        //LCFS lcfs = new LCFS(hashLength);
        //RobinHood robinHood = new RobinHood(hashLength);
        RemoveElements rE = new RemoveElements(hashLength);

        try
        {
            System.out.println("Datafile: ");
            String datafile = input.next();
            Scanner inputFile = new Scanner(new File(datafile));


            // Middlertidig
            //int count = 0;

            // Middlertidig
            while (inputFile.hasNext())
            {
                //lcfs.insert(inputFile.nextLine());

                //robinHood.insert(inputFile.nextLine());

                rE.insert(inputFile.nextLine());

            }

        // Oppgave 1: Last Come First Serve
        // Skriver ut hashlengde, antall data lest, antall kollisjoner
        // og load factor

/*
        System.out.println("Hashlengde  : " + hashLength);
        System.out.println("Elementer   : " + lcfs.antData());
        System.out.printf( "Load factor : %5.3f\n",  lcfs.loadFactor());
        System.out.println("Probes      : " + lcfs.antProbes());
*/

        //Oppgave 2: Robin Hood
/*
        System.out.println("Hashlengde  : " + hashLength);
        System.out.println("Elementer   : " + robinHood.antData());
        System.out.printf( "Load factor : %5.3f\n",  robinHood.loadFactor());
        System.out.println("Probes      : " + robinHood.antProbes());
*/

        //Oppgave 3: Fjerning av data

        String delete1 = "var voksne. Et par menn med strøkne dresser, en gammel";
        String delete2 = "snakke med Lucy igjen. Han som hadde bestemt seg for å";
        String delete3 = "En setning som ikke er som de andre";
        rE.searchAndDelete(delete1);
        rE.searchAndDelete(delete2);
        rE.searchAndDelete(delete3);
        rE.searchAndDelete("Dette er også en spennende tekst");

        //rE.printList();

        }
        catch (Exception e)
        {
            System.err.println(e);
            System.exit(1);
        }
    }
}
