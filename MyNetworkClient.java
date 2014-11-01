import ea.*;

class MyNetworkClient 
extends Client 
{
    private int meinIndex;
    private MyGame spiel;
    
    
    
    public MyNetworkClient(String ip, int port)
    {
        super(ip,port);
        this.meinIndex = -1;
    }
    
    
    
    @Override
    public void empfangeString(String s)
    {
        String[] befehle = s. split(":");
        
        // neu:name:index:x:y
        if ( befehle[0].equals("neu") ) {
            this.spiel.neuerSpieler(befehle[1], Integer.parseInt(befehle[2]), Integer.parseInt(befehle[3]), Integer.parseInt(befehle[4]) );
            if ( befehle[1].equals( this.spiel.name() ) )  {
                this.meinIndex = Integer.parseInt(befehle[2]);
                this.spiel.setzeMeinIndex(this.meinIndex);
            }
        }
        
        // bewegen:index:dx:dy
        else if ( befehle[0].equals("bewegen") ) {
            this.spiel.bewegen(Integer.parseInt(befehle[1]), Integer.parseInt(befehle[2]), Integer.parseInt(befehle[3]));
        }
        
        // farbwechsel:index:farbe
        else if ( befehle[0].equals("farbwechsel") ) {
            this.spiel.farbwechsel(Integer.parseInt(befehle[1]), befehle[2]);
        }
        
    }
    
    
    
    public void setzeSpiel(MyGame spiel) {
        this.spiel = spiel;
    }
    
    
    
    public int nenneMeinIndex()
    {
        return this.meinIndex;
    }
}