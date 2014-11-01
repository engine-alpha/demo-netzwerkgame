import ea.*;

class MyGame
extends Game 
{
    private Spielfigur[] figuren;
    private String name;
    private int meinIndex;
    private MyNetworkClient client;
    
    
    
    public MyGame(String ip, int port, String name)
    {
        super(800,600,"Engine-Alpha-Demo-Game");
        this.figuren = new Spielfigur[2];
        this.name = name;
        this.client = new MyNetworkClient(ip, port);
        this.client.setzeSpiel(this);
        
        //Warte, bis die Verbindung steht
        this.client.warteAufVerbindung();
        
        System.out.println("Done waiting.");
        
        this.client.sendeString("anmelden:" + name);
    }
    
    
    
    public void anmelden()
    {
        this.client.sendeString("anmelden:" + name);
    }
    
    
    
    public void tasteReagieren(int code)
    {
        switch(code)
        {
            // Bewegung
            case Taste.RECHTS:
                this.client.sendeString("bewegen:" + this.meinIndex + ":10:0");
                break;
            case Taste.LINKS:
                this.client.sendeString("bewegen:" + this.meinIndex + ":-10:0");
                break;
            case Taste.OBEN: 
                this.client.sendeString("bewegen:" + this.meinIndex + ":0:-10");
                break;
            case Taste.UNTEN: 
                this.client.sendeString("bewegen:" + this.meinIndex + ":0:10");
                break;
                
            // Farbwechsel    
            case Taste.R:
                this.client.sendeString("farbwechsel:" + this.meinIndex + ":rot");
                break;
            case Taste.G:
                this.client.sendeString("farbwechsel:" + this.meinIndex + ":gruen");
                break;
            case Taste.Y:
                this.client.sendeString("farbwechsel:" + this.meinIndex + ":gelb");
                break;
            case Taste.B:
                this.client.sendeString("farbwechsel:" + this.meinIndex + ":blau");
                break;
                
            // Spiel beenden und alle Ressourcen wieder freigeben
            case Taste.A:
                this.client.beendeVerbindung();
                break;
        }
    }
    
    
    
    public void neuerSpieler(String name, int index, int x, int y)
    {
        this.figuren[index] = new Spielfigur( x , y , name );
        this.figuren[index].setzeFarbe("rot");
        super.wurzel.add( this.figuren[index] );
    }
    
    
    
    public void bewegen(int index, int dx, int dy) 
    {
        this.figuren[index].bewegen(dx, dy);
    }
    
    
    
    public void farbwechsel(int index, String farbe)
    {
        this.figuren[index].setzeFarbe(farbe);
    }
    
    
    
    public String name()
    {
        return this.name;
    }
    
    
    
    public void setzeMeinIndex(int index)
    {
        this.meinIndex = index;
    }
    
}