package modules;

public class LCFS {
    // Last Come First Serve

    // Hashlengde
    private int hashLength;

    // Hashtabell
    private String hashTabell[];

    // Antall elementer lagret i tabellen
    private int n;

    // Antall probes ved innsetting
    private int antProbes;

    // Konstruktør
    // Sjekker ikke for fornuftig verdi av hashlengden
    //
    public LCFS(int lengde)
    {
        hashLength = lengde;
        hashTabell = new String[lengde];
        n = 0;
        antProbes = 0;
    }

    // Returnerer load factor
    public float loadFactor()
    {
        return ((float) n)/ hashLength;
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
        return h % hashLength;
    }

    // Innsetting av tekststreng med lineær probing
    // Avbryter med feilmelding hvis ledig plass ikke finnes
    //
    //Denne delen må skrives om i Oblig 5 for å ta for seg:
    //-Last come, first served
    //--Nyttig når bruker vet at det siste som ble lagt til snart skal brukes
    //--Dytter da alt som ligger i kjeden bakover
    //-Robin Hood
    //--Gjør søkeveiene så korte som mulig. Hvem ligger lengst unna sin
    //	hash-verdi
    public void insert(String S)
    {
        // Beregner hashverdien
        int h = hash(S);

        // Lineær probing
        int neste = h;

        // Så lenge plassen er opptatt:
        while (hashTabell[neste] != null)
        {

            // Ny probe
            antProbes++;

            // Denne indeksen er opptatt, prøver neste

            //neste++;

            // Så her lagrer jeg den nye verdien så jeg kan deale med [neste] som en annen verdi
            // Håper jeg
            String Override = S;

            // Tester
            System.out.println("Override: " + Override);
            System.out.println("HashTabell[neste]: " + hashTabell[neste]);

            // Denne blir satt inn når den forrige verdien er hentet ut og lagret!
            hashTabell[neste] = Override;   // Nå blir vel den gamle verdien overkjørt uten at jeg har dem med videre  :/
            System.out.println("HashTabell[neste] 2.0: " + hashTabell[neste]);

            //LC,FS -> Bare et par linjer til med kode, legg inn en swap
            //	Kun 1 linje er nødvendig! o.O
            //RH -> Lite kode, men beregne verdien for begge som må
            //	sammenlignes. Finn hvem som som må flyttes videre
            //	3/4 linjer nødvendig
            // Wrap-around
            if (neste >= hashLength)
                neste = 0;  // Når koden har nådd slutten går den tilbake til starten


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
            if (neste >= hashLength)
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
    // * Hashlengde gis som input på kommandolinjen
    //
    // * Leser tekststrenger linje for linje fra standard input
    //   og lagrer dem i hashtabellen
    //
    // * Skriver ut litt statistikk etter innsetting
    //
    // * Tester om søk fungerer for et par konstante verdier
    //
}
