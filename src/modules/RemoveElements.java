package modules;


// Hashing av tekststrenger med kjeding i lenket liste
// Bruker Javas innebygde hashfunksjon for strenger
//
// Enkel og begrenset implementasjon:
//
// - Ingen rehashing ved full tabell/lange lister
// - Tilbyr bare innsetting og søking
//
public class RemoveElements
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
    private int hashLength;

    // Hashtabell, pekere til lister
    private hashNode hashTabell[];

    // Antall elementer lagret i tabellen
    private int n;

    // Antall kollisjoner ved innsetting
    private int antKollisjoner;

    // Konstruktør
    // Sjekker ikke for fornuftig verdi av hashlengden
    //
    public RemoveElements(int lengde)
    {
        hashLength = lengde;
        hashTabell = new hashNode[lengde];
        n = 0;
        antKollisjoner = 0;
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

    // Returnerer antall kollisjoner ved innsetting
    public int antKollisjoner()
    {
        return antKollisjoner;
    }

    // Hashfunksjon
    int hash(String S)
    {
        int h = Math.abs(S.hashCode());
        return h % hashLength;
    }

    // Innsetting av tekststreng med kjeding
    //
    public void insert(String S)
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
    public boolean searchAndDelete(String S)
    {

        // Finner listen som S skal ligge i
        hashNode hN = hashTabell[hash(S)];

        // Leter gjennom listen
        while (hN != null) {
            // Har vi funnet tekststrengen?
            if (hN.data.compareTo(S) == 0) {
                // Noden må fjernes fra listen
                // Må sette sammen nodene før og etter
                // Må bryte ut av loopen
                hashNode beforeNode = hashTabell[hash(S)];
                hashNode afterNode = hashTabell[hash(S)];
                System.out.println("Settningen \"" + S + "\" er en del av tabellen");
                return true;
            }
            // Prøver neste
            hN = hN.neste;
        }
        // Finner ikke strengen, har kommet til slutten av listen
        System.out.println("Settningen \"" + S + "\" er ikke en del av tabellen");
        return false;
    }
}
