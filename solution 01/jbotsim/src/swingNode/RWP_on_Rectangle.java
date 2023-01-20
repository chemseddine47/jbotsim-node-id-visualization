package swingNode;


import java.awt.Graphics;

import elib.Loc;


abstract class RWP_on_Rectangle
{
    public static double cx() { return  0.0; }
    public static double cy() { return  0.0; }
    public static double w()  { return  2.0; }
    public static double h()  { return  2.0; }
    
    public   static void init( Loc P )  // initial location
    {
        draw( P );
    }
    
    public static void draw( Loc P )
    {
        P.x = 2;
        
        while ( P.len2() >= 1.0 )
        {
            P.x = 2*Math.random() - 1.0;
            P.y = 2*Math.random() - 1.0;
        }
    }

    public static void drawBorder( Graphics g, ScreenMap sm )
    {
        
    	 sm.drawRectangle( g, 0.0, 0.0, 1.0 ); // Instruction d'origine
    	//sm.drawDiskS(g, 0.0,0.0, 2);
    }
    
  //  public static int getX(Noeud n)   { return (int) ((n.P.x+1)*500.0); }
    
  //  public static int getY(Noeud n)   { return (int) ((n.P.y+1)*500.0); }
}
