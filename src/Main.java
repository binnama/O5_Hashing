import modules.LCFS;
import modules.RemoveElements;
import modules.RobinHood;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int run = 1;

        Scanner input = new Scanner(System.in);
        //System.out.println("Please provide a hash length: ");
        //int hashLength = input.nextInt();
        //System.out.println(hashLength);

        int hashLength = 2500;
        LCFS lcfs = new LCFS(hashLength);
        RobinHood robinHood = new RobinHood(hashLength);
        RemoveElements rE = new RemoveElements(hashLength);

        try {
            System.out.println("Filen som følger med heter ga.txt og ligger i samme mappe som Main.\n" +
                    "Om du kjører den med IntelliJ må du skrive src/ga.txt, med vim holder det ga.txt");
            System.out.println("Datafile: ");
            String datafile = input.next();
            Scanner inputFile = new Scanner(new File(datafile));

            System.out.println("Velg hvilken oppgave du vil kjøre: ");
            System.out.println("1: Last Come First Serve\n" +
                    "2: Robin Hood\n" +
                    "3: Remove Elements\n" +
                    "4: Exit");
            int oppgave = input.nextInt();

            while (run == 1) {
                /* Oppgave 1: Last Come First Serve */
                if (oppgave == 1) {
                    while (inputFile.hasNext()) {
                        lcfs.insert(inputFile.nextLine());
                    }

                    System.out.println("Hashlengde  : " + hashLength);
                    System.out.println("Elementer   : " + lcfs.antData());
                    System.out.printf("Load factor : %5.3f\n", lcfs.loadFactor());
                    System.out.println("Probes      : " + lcfs.antProbes());
                    break;
                }

                if (oppgave == 2) {
                    /* Oppgave 2: Robin Hood */
                    while (inputFile.hasNext()) {
                        robinHood.insert(inputFile.nextLine());
                    }

                    System.out.println("Hashlengde  : " + hashLength);
                    System.out.println("Elementer   : " + robinHood.antData());
                    System.out.printf("Load factor : %5.3f\n", robinHood.loadFactor());
                    System.out.println("Probes      : " + robinHood.antProbes());
                    break;
                }

                if (oppgave == 3) {
                    /* Oppgave 3: Sletting fra en  */
                    while (inputFile.hasNext()) {
                        rE.insert(inputFile.nextLine());
                    }

                    System.out.println("Jeg brukte bare tekstfilen fra forrige oblig, så hver probe består av " +
                            "forkjellige setninger, ikke bare enkeltord.\n" +
                            "Derfor har jeg plukket ut et par av linjene som skal slettes, og gitt den et par " +
                            "linjer som ikke finnes i teksten.\n");

                    System.out.println("Hashlengde  : " + hashLength);
                    System.out.println("Elementer   : " + rE.antData());
                    System.out.printf("Load factor : %5.3f\n", rE.loadFactor());
                    System.out.println("Probes      : " + rE.antKollisjoner() + "\n");

                    String delete1 = "var voksne. Et par menn med strøkne dresser, en gammel";
                    String delete2 = "snakke med Lucy igjen. Han som hadde bestemt seg for å";
                    String delete3 = "En setning som ikke er som de andre";
                    rE.Delete(delete1);
                    rE.Delete(delete2);
                    rE.Delete(delete3);
                    rE.Delete("Dette er også en spennende tekst");

                    System.out.println("Sjekker at den første slettede setningen faktisk er borte:");
                    rE.Delete(delete1);

                    break;
                }

                if (oppgave == 4) {
                    System.out.println("Programmet avsluttes");
                    run = 0;
                }
            }
        }

        catch (Exception e)
        {
            System.err.println(e);
            System.exit(1);
        }
    }
}
