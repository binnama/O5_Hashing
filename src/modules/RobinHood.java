package modules;

// Hashing av tekststrenger med lineær probing
// Bruker Javas innebygde hashfunksjon for strenger
//
// Enkel og begrenset implementasjon:
//
// - Ingen rehashing ved full tabell
// - Tilbyr bare innsetting og søking

public class RobinHood
{
    // Hashlengde
    private int hashLength;

    // Hashtabell
    private String hashTabell[];

    // Antall elementer lagret i tabellen
    private int n;

    // Antall probes ved innsetting
    private int antProbes;

	// PSL-holder
	private int[] dist;

    // Konstruktør
    // Sjekker ikke for fornuftig verdi av hashlengden
    //
    public RobinHood(int lengde)
    {
		hashLength = lengde;
		hashTabell = new String[lengde];
		n = 0;
		antProbes = 0;
		dist = new int[lengde];

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
    public void insert(String S)
    {
		// Beregner hashverdien
		// Key / Index(?)
		int h = hash(S);

		// Lineær probing
		int neste = h;

		// Value
		String Override = S;
		String holder;

		// Probe Sequence Length
		int psl = 0;

		// Probe-tallet som sitter på den aktuelle verdien
		int vpsl = 0;

		while (hashTabell[neste] != null) {
			// Dersom PSL blir høyere enn eksisterende PSL (vpsl) vil de to bytte plass og den nye verdien
			// fortsetter nedover med swapping til den finner en ledig plass hvor den får en ny PSL-verdi.
			if (psl > dist[neste]) {
				System.out.println("dist[neste] LCFS: " + dist[neste]);
				System.out.println("psl LCFS: " + psl);
        /*
		Bruker LCFS-koden videre fra forrige oppgave
		 */
				antProbes++;

				holder = hashTabell[neste];     // Holder på den gamle verdien

				hashTabell[neste] = Override;   // Setter den nye verdien på gjeldende index

				neste++;

				if (hashTabell[neste] == null) {
					hashTabell[neste] = holder;
					break;
				}

				while (hashTabell[neste] != null && psl > dist[neste]) {

					System.out.println("Nå er vi inne i innerste while!!!!");
					System.out.println("dist[neste] while2: " + dist[neste]);
					System.out.println("psl while2: " + psl);

					antProbes++;
					Override = holder;
					holder = hashTabell[neste];
					hashTabell[neste] = Override;
					neste++;
					if (neste >= hashLength)
						neste = 0;  // Når koden har nådd slutten går den tilbake til starten

					if (neste == h) {
						System.err.println("\nHashtabell full, avbryter");
						System.exit(0);
					}
					psl++;
				}
			}
			else { // Vanlig lineær probing / innsetting

				System.out.println("dist[neste] difølt: " + dist[neste]);
				System.out.println("psl difølt: " + psl);
				antProbes++;

				neste++;

				// Wrap-around
				if (neste >= hashLength)
					neste = 0;

				if (neste == h) {
					System.err.println("\nHashtabell full, avbryter");
					System.exit(0);
				}

				psl++;
			}
		}
		dist[neste] = psl;
		hashTabell[neste] = S;

		n++;
    }

    boolean search(String S)
    {
		// Beregner hashverdien
		int h = hash(S);

		// Lineær probing
		int neste = h;

		while (hashTabell[neste] != null) {
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

}
