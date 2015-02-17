import ea.*;

/**
 * @Description     Server-Klasse eines Demo-Netzwerk-Spiels.
 *                  Bevor keine Server-Instanz erzeugt wurde, kann sich kein Spieler anmelden!
 *                  Der Server kann auf einem beliebigen Rechner (auch auf einem Client-Rechner) gestartet werden.
 *                  Anschliessend verbinden sich die beiden Clients mit dem Server.
 *                  Die Clients senden jede gewuenschte Zustands-Aenderung erst an den Server.
 *                  Dieser sendet daraufhin al Reaktion an beide Clients "Befehle" zur Aenderung
 *                  des lokalen Spiel-Zustands.
 *
 * @Author          Mike Ganshorn
 *
 * @Version         1.1 (2015-02-13)
 *
 */
class MyGameServer
        implements ea.Empfaenger
{
    private String[] spieler;
    private int akt_spieler;
    private ea.Server server;


    /**
     * Konstruktor der Klasse MyGameServer.                         <br />
     *
     * Muss auf einem beliebigen Rechner gestartet werden, bevor sich Clients anmelden koennen.
     *
     * @param   port    Der Port auf dem der Server lauschen soll.
     */
    public MyGameServer( int port )
    {
        server = new ea.Server( port );
        this.spieler = new String[2];
        this.akt_spieler = 0;
        // Dieses MyServer-Objekt benachrichtigen, wenn der Server eine Nachricht empfaengt
        server.globalenEmpfaengerSetzen( this );
    }


    /**
     * Vom Interface Empfaenger vorgeschriebene Methode zum Empfangen eines Strings von der Netzwerk-Gegenstelle.
     * Diese Methode wird auftomatisch aufgerufen, sobald von der Netzwerk-Gegenstelle ein String lokal ankommt.
     * <br />
     *
     * Hier wird ein sehr primitives Protokoll mit nur 3 "Befehlen" verwendet:
     * anmelden, bewegen, Farbe aendern                                                         <br />
     *
     * Jeder Client sendet jeden Aenderungs-Wunsch erst an den Server und dieser sendet
     * danach als Reaktion an beide Clients den entsprechenden "Befehl" zur Aenderung
     * des lokalen Spiel-Zustands.
     *
     * @param   s   Der empfangene Text ("Befehl" aus dem Protokoll)
     */
    @Override
    public void empfangeString( String s )
    {
        String[] befehle = s.split( ":" );
        
        // anmelden:name
        if ( befehle[0].equals("anmelden") ) {
            // erst auf beide Spieler warten
            if (this.akt_spieler < 2) {
                this.spieler[this.akt_spieler] = befehle[1];
                this.akt_spieler++;
            }
            // danach bei den Clients das Spiel starten
            if (this.akt_spieler == 2) {
                // neu:name:index:x:y
                int x;
                int y;
                
                x = (int)((Math.random()) * 700 + 50);
                y = (int)((Math.random()) * 500 + 50);
                server.sendeString("neu:" + this.spieler[0] + ":0:" + x + ":" + y);
                
                x = (int)((Math.random()) * 700 + 50);
                y = (int)((Math.random()) * 500 + 50);
                server.sendeString("neu:" + this.spieler[1] + ":1:" + x + ":" + y);
            }
        }
        
        // bewegen:index:dx:dy
        else if ( s.startsWith("bewegen") ) {
            server.sendeString(s);
        }
        
        // farbwechsel:index:farbe
        else if ( s.startsWith("farbwechsel") ) {
            server.sendeString(s);
        }
    }


    // Info: Diese Methoden werden vom Interface Empfaenger gefordert. Sie muessen implementiert werden.
    //       Ihre Ruempfe bleiben leer, da diese Methoden in der Demo nicht verwendet werden.

    @Override
    public void verbindungBeendet ()
    {
        // Diese Methode wird in dieser Demo nicht aufgerufen
    }
 
    @Override
    public void empfangeInt (int i)
    {
        // Diese Methode wird in dieser Demo nicht aufgerufen
    }
 
    @Override
    public void empfangeByte (byte b)
    {
        // Diese Methode wird in dieser Demo nicht aufgerufen
    }
 
    @Override
    public void empfangeDouble (double d)
    {
        // Diese Methode wird in dieser Demo nicht aufgerufen
    }
 
    @Override
    public void empfangeChar (char c)
    {
        // Diese Methode wird in dieser Demo nicht aufgerufen
    }
 
    @Override
    public void empfangeBoolean (boolean b)
    {
        // Diese Methode wird in dieser Demo nicht aufgerufen
    }
}