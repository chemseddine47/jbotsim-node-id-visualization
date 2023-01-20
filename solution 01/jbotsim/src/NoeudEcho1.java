

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

//import jbotsim.Clock;



import jbotsim.Message;
import jbotsim.Node;
import jbotsim.event.ClockListener;
import jbotsim.event.MessageListener;

// Algorithme de parcours echo

public class NoeudEcho1 extends Node implements ClockListener, MessageListener {

   public NoeudEcho1 parent;
	//public int id;
	public int Nl;
	
	boolean initiateur;
	
    boolean engage;
	
	int val; 

	public NoeudEcho1(){		// Constructor, assigns distinct ID's to nodes;
		this.parent = null;
		this.engage = false;
		this.initiateur = false;	
		
	}

   @Override
   public void onStart(){
	   if (this.getID() == 0) {
	        engage = true;
	        initiateur = true;
	        this.val = this.getID();

	    
			   for (Node nbr : getNeighbors()) { 
			         send(nbr, "INFO",val); 
			   }
			   System.out.println(this.getID()+ " envoie INFO + "+val+ " a"+ this.getInNeighbors().toString());
			   this.setColor(jbotsim.Color.green); // Root node is colored green
	   }
	   
   }
   
   
	  @Override
      public void onMessage(Message msg) {
		  ArrayList<Node> listPere = new ArrayList<Node>();
		  ArrayList<Node> saufPere = new ArrayList<Node>();	   
		  
		   System.out.print(this.getID()+ " recoit "+ msg.returnContent() + " de "+ msg.getSender().toString()+ "==>    ");
		   
		   if(this.engage==false) {	

		       this.engage = true;
			   this.parent = (NoeudEcho1)msg.getSender();
			   this.getCommonLinkWith(parent).setColor(jbotsim.Color.red);		// Color the tree edges red;
			   this.getCommonLinkWith(parent).setWidth(2);
			  
			   
			   listPere.add((Node) this.parent);
			   saufPere = soustraction(listToArray(getNeighbors()), listPere);
			   
			   // Send a message to all neighbors except parent.
			   if (saufPere.size()!= 0) {
			        for (Node nbr : saufPere) 
				       send (nbr,"INFO");
				  
			  	   System.out.println(this.getID()+ " envoie INFO � "+ saufPere.toString());     
			   }   
		  }
		   
		   this.Nl = this.Nl + 1;
		   
		   if (this.Nl == this.getNeighbors().size()){
			   this.engage = false;
		       if (this.initiateur == false) {
		    	     send(this.parent, "ECHO");
		             System.out.println(this.getID()+ " envoie ECHO � "+ this.parent.toString());
		       }
		       else 
		    	   System.out.println("algortihme termin�");
		       
		   }
		   else 
		      if (saufPere.size() == 0)
		    	  System.out.println("Va rien faire");
      }
	   
	   
	 // Calcule la soustraction de deux ensembles
	public static <T> ArrayList<T> soustraction(ArrayList<T> first, ArrayList<T> second) {
		ArrayList<T> list = new ArrayList<T>();
		list = first;
			   
		for (T t : second) {
		     if(first.contains(t)) {
		              list.remove(t);
		     }
	   }

		return list;
    }
	
	// Conversion d'une liste � ArrayList
		public static <T>  ArrayList<T> listToArray(List<T> liste){
		   ArrayList<T> tableau = new ArrayList<T>();
		   for(T indexNode: liste)
			   tableau.add(indexNode);
			return tableau;
		}
		
}
