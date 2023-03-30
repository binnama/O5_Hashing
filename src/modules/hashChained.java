package modules;

import java.io.*;
import java.util.Scanner;

// Hashing av tekststrenger med kjeding i lenket liste
// Bruker Javas innebygde hashfunksjon for strenger
//
// Enkel og begrenset implementasjon:
//
// - Ingen rehashing ved full tabell/lange lister
// - Tilbyr bare innsetting og søking
//
public class hashChained
{
    // Indre klasse:
    // Node med data, kjedes sammen i lenkede lister
    //
    private class hashNode
    {
		// Data, en tekststreng
		String data;
		// Neste node i listen
		hashNode neste;

		// Konstruktør for listenoder
		public hashNode(String S, hashNode hN) {
			data = S;
			neste = hN;
		}
    }

    // Hashlengde
    private int hashLengde;

    // Hashtabell, pekere til lister
    private hashNode hashTabell[];

    // Antall elementer lagret i tabellen
    private int n;

    // Antall kollisjoner ved innsetting
    private int antKollisjoner;

    // Konstruktør
    // Sjekker ikke for fornuftig verdi av hashlengden
    //
    public hashChained(int lengde)
    {
		hashLengde = lengde;
		hashTabell = new hashNode[lengde];
		n = 0;
		antKollisjoner = 0;
    }

    // Returnerer load factor
    public float loadFactor()
    {
		return ((float) n)/hashLengde;
    }

    // Returnerer antall data i tabellen
    public int antData()
    {
		return n;
    }

    // Returnerer antall kollisjoner ved innsetting
    public int antKollisjoner()
    {
		return antKollisjoner;
    }

    // Hashfunksjon
    int hash(String S)
    {
		int h = Math.abs(S.hashCode());
		return h % hashLengde;
    }

    // Innsetting av tekststreng med kjeding
    //
    void insert(String S)
    {
		//System.out.println("Inserting: " + S);
		// Beregner hashverdien
		int h = hash(S);

		// Øker antall elementer som er lagret
		n++;

		// Sjekker om kollisjon
		if (hashTabell[h] != null)
			antKollisjoner++;

		// Setter inn ny node først i listen
		hashTabell[h] = new hashNode(S, hashTabell[h]);
    }

    // Søking etter tekststreng i hashtabell med kjeding
    // Returnerer true hvis strengen er lagret, false ellers
    //
    boolean search(String S)
    {
		// Finner listen som S skal ligge i
		hashNode hN = hashTabell[hash(S)];

		// Leter gjennom listen
		while (hN != null) {
			// Har vi funnet tekststrengen?
			if ( hN.data.compareTo(S) == 0) {
				System.out.println("Found: " + S);
				return true;
			}
			// Prøver neste
			hN = hN.neste;
		}
		// Finner ikke strengen, har kommet til slutten av listen
		System.out.println("Not found: " + S);
		return false;
    }

	public static void main(String argv[])
    {
		// Hashlengde leses fra bruker
		Scanner in = new Scanner(System.in);
		System.out.print("Hashlengde? ");
		int hashLengde = in.nextInt();

		// Lager ny hashTabell
		hashChained hC = new hashChained(hashLengde);

		// Leser datafil og hasher alle linjer
		try {
			System.out.print("Datafil? ");
			String filnavn = in.next();
			Scanner input = new Scanner(new File(filnavn));

			while (input.hasNext()) {
				hC.insert(input.nextLine());
			}

			hC.search("Hei");

			// Skriver ut hashlengde, antall data lest, antall kollisjoner
			// og load factor
			System.out.println("\nHashlengde  : " + hashLengde);
			System.out.println("Elementer   : " + hC.antData());
			System.out.printf( "Load factor : %5.3f\n",  hC.loadFactor());
			System.out.println("Kollisjoner : " + hC.antKollisjoner() + "\n");
		} catch (Exception e) {
			System.err.println(e);
			System.exit(1);
		}
    }
}
