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

    public void insert(String S)
    {
        // Beregner hashverdien
        int h = hash(S);

        // Lineær probing
        int neste = h;

        // Lagrer nye verdier som egen. Fordi jeg kan :D
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

            // Lagrer den gamle verdien på gjeldende index
            holder = hashTabell[neste];     // Holder på den gamle verdien

            hashTabell[neste] = Override;   // Setter den nye verdien på gjeldende index

            neste++;

            if (hashTabell[neste] == null) {
                hashTabell[neste] = holder;
                break;
            }

            while (hashTabell[neste] != null)
            {
                antProbes++;
                Override = holder;
                holder = hashTabell[neste];
                hashTabell[neste] = Override;
                neste++;
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
}
