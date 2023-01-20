
import java.awt.Component;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;

import jbotsim.Topology;

import jbotsimx.ui.JTopology;
import jbotsimx.ui.JViewer;


@SuppressWarnings("serial")
public class LancerEcho1 extends JFrame {
   
     
    static Topology topo;
    static boolean hasStarted;
    int size;
   
   
    
    
    static void reinit() {
        try {
            topo.getNodeModel("default");
           int n = topo.getClockSpeed();
            topo = new Topology();
            topo.setNodeModel("default", NoeudEcho1.class);
            topo.setClockSpeed(n);
            topo.setDimensions(700, 550);
            topo.resetTime();
           
            topo.pause();
          
            topo.resume();
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
        }
    }
    
 // Programme principal
    public static void main(String[] args) {
	   
		        
	         
	        LancerEcho1 fen = new LancerEcho1();
	        
	        fen.setDefaultCloseOperation(3);
		    fen.setTitle(" Le programme de parcours Echo");
		    fen.setSize(810, 660);
		    fen.setLocationRelativeTo(null);
	   	    
	    
	        fen.setPreferredSize(new Dimension(800, 650));
	        
	        fen.setVisible(true);     
}

// Fin du programme principal
   
 	
 // Constructeur
    LancerEcho1() {
        
    	 topo = new Topology();
    	 topo.setNodeModel("default", NoeudEcho1.class); 
    	
        
        JButton jButton = new JButton(" Nouveau >");
   
        JPanel jPanel2 = new JPanel();
        
        RenewAction renewAction2 = new RenewAction(jPanel2);
        jButton.addActionListener(renewAction2);
        
        JPanel jPanel = new JPanel();
        jPanel.add(jButton);
      
        
        JButton jButton2 = new JButton("| Commencer >");
        jButton2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	
            	System.out.println("les noeuds de topologie" +  topo.getNodes());
                
                /*if (LancerEcho1.hasStarted) {
                    LancerEcho1.topo.resume();
                                    
                } else {
                    LancerEcho1.hasStarted = true;
                    LancerEcho1.topo.restart();
                    LancerEcho1.topo.start();
                    
                }
                */
            	
            	LancerEcho1.topo.start();
                
            }
        });
        
        jPanel.add(jButton2);
       
            
        this.add((Component)jPanel, "North");
        this.add((Component)jPanel2, "Center");
        
	    renewAction2.actionPerformed(null);      
       
       
    }
    
    // Fin du constructeur
    
    static {
        hasStarted = false;
        
    }



    static class RenewAction   implements ActionListener {
        JPanel viewer;
        JTopology svgd = null;

        RenewAction(JPanel jPanel) {
            this.viewer = jPanel;
                    }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                LancerEcho1.hasStarted = false;
                LancerEcho1.reinit();
                JTopology jTopology = new JTopology(topo);
                // rajout�s
                new JViewer(jTopology,false);
                
                if (this.svgd != null) {
                    this.viewer.remove((Component)this.svgd);
                }
                this.svgd = jTopology;
                this.viewer.add((Component)jTopology);
                this.viewer.revalidate();
                this.viewer.repaint();
            }
            catch (Exception var2_3) {
                var2_3.printStackTrace();
            }
        }
    }
    
}
    
   

    
