import modules.LCFS;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Scanner scanner = new Scanner(System.in);

        // Hashlengde leses fra kommandolinjen
        int hashLengde = 0;
        System.out.println("Please provide a hash length: ");
        int hashLength = scanner.nextInt();

        try
        {
            if (args.length != 1)
                throw new IOException("Feil: Hashlengde må angis");
            hashLengde = Integer.parseInt(args[0]);
            if (hashLengde < 1 )
                throw new IOException("Feil:" +
                        "Hashlengde må være større enn 0");
        }
        catch (Exception e)
        {
            System.err.println(e);
            System.exit(1);
        }

        // Oppgave 1: Last Come First Serve

        // Lager ny hashTabell
        LCFS lcfs = new LCFS(hashLengde);
        //lastComeFirstServed lcfs = new lastComeFirstServed(hashLengde);


        // Leser input og hasher alle linjer
        while (hashLength.hasNext())
        {
            lcfs.insert(hashLength.nextLine());
        }

        // Skriver ut hashlengde, antall data lest, antall kollisjoner
        // og load factor
        System.out.println("Hashlengde  : " + hashLengde);
        System.out.println("Elementer   : " + lcfs.antData());
        System.out.printf( "Load factor : %5.3f\n",  lcfs.loadFactor());
        System.out.println("Probes      : " + lcfs.antProbes());
    }

        //Oppgave 2: Robin Hood

        //Oppgave 3: Fjerning av data
}