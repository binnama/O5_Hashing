package modules;

public class LCFSwSOUTs {    // Last Come First Serve

    // Denne er bare for min egen skyld. Ikke tenk på den ;)

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
    public LCFSwSOUTs(int lengde)
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

        // Lagrer den nye verdien som en egen. Forid jeg kan :D
        String Override = S;
        String holder;

        // Så lenge plassen er opptatt:
        while (hashTabell[neste] != null)
        {

            /*
             Skriver heller endringene som har blitt gjort her:
             Lagde Override og holder for å holde styr på hva jeg holdt på med. Kunne sikkert
             bare holdt meg på 'neste', men turte ikke teste ut det
             Her i while fjernet jeg den første 'neste++' og lagret den eksisterende verdien i holder
             Så lagret jeg Override i hashTabell[neste] og økte neste med 1. Dersom neste plass var ledig vil
             verdien bli plassert der.
             For å håndtere forekomster der jeg måtte swappe med eldre verdier kjørte jeg enda en while-loop.
             Der settes holder-verdien til å bli den nye Override, mens ny holder blir verdien som ligger på
             den nye indexen.
             Kunne helt sikkert gjort det penere og kortere, men nå funker den i det minste XD
             */

            // Ny probe
            antProbes++;

            // Denne indeksen er opptatt, prøver neste

            //Mutet denne ->
            //neste++;

            // Lagrer den gamle verdien på gjeldende index
            holder = hashTabell[neste];     // Holder på den gamle verdien
            System.out.println("Holder : " + holder);

            hashTabell[neste] = Override;   // Setter den nye verdien på gjeldende index
            System.out.println("Override original: " + hashTabell[neste]);

            neste++;

            if (hashTabell[neste] == null) {
                hashTabell[neste] = holder;
                System.out.println("Plasserer holder på neste ledige plass: " + hashTabell[neste] + "\n");
                break;
            }

            System.out.println("Dette blir ny Holder. Ny verdi: " + hashTabell[neste] + "\n");
            while (hashTabell[neste] != null)
            {

                Override = holder;
                System.out.println("New Override: " + Override);
                holder = hashTabell[neste];
                System.out.println("Skal matche verdien to opp!");
                System.out.println("New Holder: " + holder);
                hashTabell[neste] = Override;
                System.out.println("Setter fra meg New Override: " + hashTabell[neste]);
                neste++;
                System.out.println("HashTabell[neste] bytte verdi: " + hashTabell[neste] + "\n");
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
            System.out.println("Swapping is done!\n\n");
            /*
            System.out.println("HashTabell[neste] bytte verdi: " + hashTabell[neste] + "\n");
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
            */

            //LC,FS -> Bare et par linjer til med kode, legg inn en swap
            //	Kun 1 linje er nødvendig! o.O
            //RH -> Lite kode, men beregne verdien for begge som må
            //	sammenlignes. Finn hvem som som må flyttes videre
            //	3/4 linjer nødvendig
            // Wrap-around
        }

        // Lagrer tekststrengen på funnet indeks
        // Byttet fra S til Override
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
