import ea.*;

class MyGameServer 
implements Empfaenger 
{
    private String[] spieler;
    private int akt_spieler;
    private Server server;
    
    public MyGameServer(int port)
    {
        server = new Server(port);
        this.spieler = new String[2];
        this.akt_spieler = 0;
        // Dieses MyServer-Objekt benachrichtigen, wenn der Server eine Nachricht empfaengt
        server.globalenEmpfaengerSetzen(this);
    }
    
    @Override
    public void empfangeString(String s)
    {
        String[] befehle = s.split(":");
        
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
 
    @Override
    public void verbindungBeendet () {
    }
 
         // Info: Diese Methoden müssen implementiert werden.
         //       Sie bleiben leer, da sie hier nicht verwendet werden.
 
    @Override
    public void empfangeInt (int i) {
    }
 
    @Override
    public void empfangeByte (byte b) {
    }
 
    @Override
    public void empfangeDouble (double d) {
    }
 
    @Override
    public void empfangeChar (char c) {
    }
 
    @Override
    public void empfangeBoolean (boolean b) {
    }
}