import java.io.*;
import java.util.Scanner;

// Hashing av tekststrenger med lineær probing
// Bruker Javas innebygde hashfunksjon for strenger
//
// Enkel og begrenset implementasjon:
//
// - Ingen rehashing ved full tabell
// - Tilbyr bare innsetting og søking

public class hashLinear
{
    // Hashlengde
    private int hashLengde;

    // Hashtabell
    private String hashTabell[];

    // Antall elementer lagret i tabellen
    private int n;

    // Antall probes ved innsetting
    private int antProbes;

    // Konstruktør
    // Sjekker ikke for fornuftig verdi av hashlengden
    //
    public hashLinear(int lengde)
    {
	hashLengde = lengde;
	hashTabell = new String[lengde];
	n = 0;
	antProbes = 0;
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

    // Returnerer antall probes ved innsetting
    public int antProbes()
    {
	return antProbes;
    }

    // Hashfunksjon
    int hash(String S)
    { 
	int h = Math.abs(S.hashCode());
	return h % hashLengde;
    }

    // Innsetting av tekststreng med lineær probing
    // Avbryter med feilmelding hvis ledig plass ikke finnes
    //
    void insert(String S)
    {
	// Beregner hashverdien
	int h = hash(S);

	// Lineær probing
	int neste = h;

	while (hashTabell[neste] != null)
	{
	    // Ny probe
	    antProbes++;

	    // Denne indeksen er opptatt, prøver neste
	    neste++;
	    
	    // Wrap-around
	    if (neste >= hashLengde)
		neste = 0;

	    // Hvis vi er kommet tilbake til opprinnelig hashverdi, er
	    // tabellen full og vi gir opp (her ville man normalt
	    // doblet lengden på hashtabellen og gjort en rehashing)
	    if (neste == h)
	    {
		System.err.println("\nHashtabell full, avbryter");
		System.exit(0);
	    }
	}

	// Lagrer tekststrengen på funnet indeks
	hashTabell[neste] = S;

	// Øker antall elementer som er lagret
	n++;
    }

    // Søking etter tekststreng med lineær probing
    // Returnerer true hvis strengen er lagret, false ellers
    //
    boolean search(String S)
    {
	// Beregner hashverdien
	int h = hash(S);

	// Lineær probing
	int neste = h;

	while (hashTabell[neste] != null)
	{
	    // Har vi funnet tekststrengen?
	    if (hashTabell[neste].compareTo(S) == 0)
		return true;
		
	    // Prøver neste mulige
	    neste++;
	    
	    // Wrap-around
	    if (neste >= hashLengde)
		neste = 0;

	    // Hvis vi er kommet tilbake til opprinnelig hashverdi,
	    // finnes ikke strengen i tabellen
	    if (neste == h)
		return false;
	}	

	// Finner ikke strengen, har kommet til en probe som er null
	return false;
    }

    // Enkelt testprogram:
    // 
    // * Leser hashlengde og datafil fra bruker
    //
    // * Leser tekststrenger linje for linje fra datafilen
    //   og lagrer dem i hashtabellen
    //
    // * Skriver ut litt statistikk etter innsetting
    //
    public static void main(String argv[])
    {
	// Hashlengde leses fra bruker
	Scanner in = new Scanner(System.in);
	System.out.print("Hashlengde? ");
	int hashLengde = in.nextInt();

	// Lager ny hashTabell
	hashLinear hL = new hashLinear(hashLengde);

	// Leser datafil og hasher alle linjer
	try
	{
	    System.out.print("Datafil? ");
	    String filnavn = in.next();
	    Scanner input = new Scanner(new File(filnavn));
	
	    while (input.hasNext())
	    {
		hL.insert(input.nextLine());
	    }

	    // Skriver ut hashlengde, antall data lest, antall kollisjoner
	    // og load factor
	    System.out.println("\nHashlengde  : " + hashLengde);
	    System.out.println("Elementer   : " + hL.antData());
	    System.out.printf( "Load factor : %5.3f\n",  hL.loadFactor());
	    System.out.println("Probes      : " + hL.antProbes() + "\n");
	}
	catch (Exception e)
	{
	     System.err.println(e);
	     System.exit(1);
	}
    }
}
