package modules;

public class ChainedWSouts {

    hashNode head;
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
    public ChainedWSouts(int lengde)
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

    // Byttet fra bool til void
    public void searchAndDelete(String S)
    {
        // Fra  GFG med Copilots hjelp
        head = hashTabell[hash(S)];
        hashNode temp = head;
        hashNode prev = null;

        //System.out.println("Head: " + head.data);
        //System.out.println("Temp: " + temp.data);
        //System.out.println("Prev: " + prev);

        while (temp != null) {

            // Hvis head er det som skal slettes
            if (temp != null && temp.data.compareTo(S) == 0) {
                head = temp.neste;  // Endrer head
                System.out.println("Settningen \"" + temp.data + "\" ble slettet fra listen");
                System.out.println("\"" + head.data + "\" er den nye head\n");
                return;
            }

            while (temp != null && temp.data.compareTo(S) != 0) {
                prev = temp;
                temp = temp.neste;
                //System.out.println("Sjekker neste element i listen");
                /*
                if (temp.data.compareTo(S) == 0) {
                    System.out.println("Temp: " + temp.data);
                    return;
                }
                */
                //break;
            }

            if (temp == null) {
                System.out.println("Fant ikke setningen: \"" + S + "\" i listen\n");
                return;
            }

            // Fjerner elementet fra listen
            System.out.println("Fjerner elementet fra listen:");
            System.out.println("Temp: " + temp.data +"\n");
            prev.neste = temp.neste;
            break;
        }

        /*
        // Finner listen som S skal ligge i
        //hashNode hN = hashTabell[hash(S)];

        // Leter gjennom listen
        while (temp != null) {
            // Har vi funnet tekststrengen?

            System.out.println("Temp når temp != null: " + temp.data);
            System.out.println("Prev når temp != null: " + prev);
            // Dersom første node holder på det som skal slettes
            if (temp.data.compareTo(S) == 0) {
                System.out.println("\nOm første node holder på søkt setning");
                System.out.println("Temp: " + temp.data);
                System.out.println("Prev: " + prev);
                // Noden må fjernes fra listen
                // Må sette sammen nodene før og etter
                // Må bryte ut av loopen
                head = temp.neste;  // Endrer head
                System.out.println("Settningen \"" + temp.data + "\" ble slettet fra listen");
                System.out.println("\"" + head.data + "\" er den nye head");
                return true;
            }

            prev.data = temp.data;
            temp = temp.neste;
            System.out.println("Temp: " + temp.data);
            System.out.println("Prev: " + prev.data);
            System.out.println("Settningen \"" + S + "\" ble slettet fra listen");

            /*
            // Søker videre i listen etter nøkkelen
            while (temp.data.compareTo(S) == 1) {
                prev.data = temp.data;
                temp = temp.neste;
                System.out.println("Temp: " + temp.data);
                System.out.println("Prev: " + prev);
                System.out.println("Settningen \"" + S + "\" ble slettet fra listen");
            }
            */

        /*
            temp = temp.neste;
            System.out.println("Temp før switch: " + temp.data);
            prev.neste = temp;
            // Prøver neste
            //hN = hN.neste;
        */
    }
    // Finner ikke strengen, har kommet til slutten av listen
    //System.out.println("Settningen \"" + S + "\" er ikke en del av tabellen");
    //return false;
    //}

    public void printList() {
        hashNode tNode = head;
        while (tNode != null) {
            System.out.println(tNode.data + " ");
            tNode = tNode.neste;
        }
    }
}
