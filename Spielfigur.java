import ea.*;

/**
 * @Description     Einfache grafische Darstellung einer Spielfigur im Demo-Netzwerk-Spiel.
 *
 * @Author          Mike Ganshorn
 *
 * @Version         1.1 (2015-02-13)
 */
public class Spielfigur
        extends ea.Knoten
{
    private ea.Kreis k;
    private ea.Text t;


    /**
     * Konstruktor der Klasse Spielfigur
     *
     * @param   x       x-Koordinate des Mittelpunkts der Spielfigur
     * @param   y       y-Koordinate des Mittelpunkts der Spielfigur
     * @param   name    Name dieser Spielfigur
     */
    public Spielfigur( int x , int y , String name )
    {
        super();
        this.k = new ea.Kreis( x , y , 100 );
        this.k.farbeSetzen( "rot" );
        this.add( this.k );
        this.t = new ea.Text( x + 20 , y + 30 , name );
        this.add( this.t );
    }


    /**
     * Setzt die Farbe dieser Spielfigur.
     *
     * @param   farbe   Die neue Farbe (rot, gruen, weiss, ...)
     */
    public void setzeFarbe( String farbe )
    {
        this.k.farbeSetzen( farbe );
    }


    /**
     * Bewegt die Spielfigur um (dx|dy).
     *
     * @param   dx  Distanz in x-Richtung der Bewegung (in Pixel)
     * @param   dy  Distanz in y-Richtung der Bewegung (in Pixel)
     */
    public void bewegen( int dx , int dy )
    {
        super.verschieben( dx , dy );
    }
}