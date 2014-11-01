import ea.*;

class Spielfigur 
extends Knoten 
{
    private Kreis k;
    private Text t;
    
    public Spielfigur(int x, int y, String name)
    {
        super();
        this.k = new Kreis(x,y,100);
        this.k.farbeSetzen("rot");
        this.add(this.k);
        this.t = new Text(x+20,y+30,name);
        this.add(this.t);
    }
    
    public void setzeFarbe(String farbe)
    {
        this.k.farbeSetzen(farbe);
    }
    
    public void bewegen(int dx, int dy)
    {
        super.verschieben(dx, dy);
    }
}