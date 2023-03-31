package modules;

// GFG-code:
// https://www.geeksforgeeks.org/java-program-for-deleting-a-node-in-a-linked-list/

public class RemoveElements {

    // Lagde meg enda en node for å holde på den første noeden i listen
    hashNode head;

    // Indre klasse:
    // Node med data, kjedes sammen i lenkede lister
    //
    private class hashNode {
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
    public RemoveElements(int lengde) {
        hashLength = lengde;
        hashTabell = new hashNode[lengde];
        n = 0;
        antKollisjoner = 0;
    }

    // Returnerer load factor
    public float loadFactor() {
        return ((float) n) / hashLength;
    }

    // Returnerer antall data i tabellen
    public int antData() {
        return n;
    }

    // Returnerer antall kollisjoner ved innsetting
    public int antKollisjoner() {
        return antKollisjoner;
    }

    // Hashfunksjon
    int hash(String S) {
        int h = Math.abs(S.hashCode());
        return h % hashLength;
    }

    // Innsetting av tekststreng med kjeding
    //
    public void insert(String S) {
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


    // Byttet fra bool til void
    public void Delete(String S) {
        // Fra  GFG med Copilots hjelp
        head = hashTabell[hash(S)];
        hashNode temp = head;
        hashNode prev = null;


        while (temp != null) {

            // Hvis head er det som skal slettes
            if (temp != null && temp.data.compareTo(S) == 0) {
                head = temp.neste;  // Endrer head
                System.out.println("Settningen \"" + temp.data + "\" ble slettet fra listen");
                System.out.println("\"" + head.data + "\" er den nye head\n");
                return;
            }

            // Jobber seg gjennom listen
            while (temp != null && temp.data.compareTo(S) != 0) {
                prev = temp;
                temp = temp.neste;
            }

            if (temp == null) {
                System.out.println("Fant ikke setningen: \"" + S + "\" i listen\n");
                return;
            }

            // Fjerner elementet fra listen
            System.out.println("Fjerner elementet fra listen:");
            System.out.println("\"" + temp.data +"\"\n");
            prev.neste = temp.neste;
            break;
        }
    }
}


