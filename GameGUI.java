import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 02.07.2017
  * @author 
  */

public class GameGUI extends JFrame {
  // Anfang Attribute
  Game s=new Game();
  int[][] feld=new int[7][6];
  boolean spielende=false;
  int iSpielart;
  // Ende Attribute
  
  public GameGUI(String title) { 
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        klick(e);
      }
    });
    int frameWidth = 720; 
    int frameHeight = 646;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    
    // Anfang Komponenten
    
    String[] options={"Gegen KI anfangen","KI f�ngt gegen mich an","Mensch gegen Menschen","KI vs. KI"};
    
    iSpielart=JOptionPane.showOptionDialog(null, "Bitte dr�cke auf einen Knopf!","W�hle deine Spielart!",
    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,options,options[0]);
    System.out.println(iSpielart);
    switch(iSpielart) {
      case 0: 
        s.ki1=false;
        s.ki2=true;
        break;
      case 1: 
        s.ki1=true;
        s.ki2=false;
        break;
      case 2: 
        s.ki1=false;
        s.ki2=false;
        break;
      case 3: 
        s.ki1=true;
        s.ki2=true;            
    }
    // Ende Komponenten
    
    setVisible(true);
  } // end of public GameGUI
  
  // Anfang Methoden  
                     
  public void klick(MouseEvent e){
    if (spielende==false) {
      int a=(7*e.getX())/this.getWidth();
      // System.out.println("Spalte: "+ a); //debug
      int b;
      if (s.mensch()) {
        b=s.setz(a);
      } // end of if
      else {
        s.ki();
        b=s.setz(s.kiSpalte);
      } // end of if-else
      
      if (b>5) {
        JOptionPane.showMessageDialog(null,"Eine Spalte hat nur 6 Pl�tze!");
        return;
      } // end of if
      feld=s.getFeld();
      repaint();
      if (s.win()) {
        JOptionPane.showMessageDialog(null,"Spieler "+s.amZug+" hat Gewonnen!");
        spielende=true;
        return;
      } // end of if
      if (s.amZug==1) {
        s.amZug=2;
      } // end of if
      else {
        s.amZug=1;
      } // end of if-else
      
    } // end of if
    
  }
  
  public static void main(String[] args) {
    new GameGUI("4 Gewinnt");
  } // end of main
    
    // Ende Methoden
  public void paint(Graphics g){
    g.setColor(Color.white);
    g.fillRect(0,0,this.getWidth(),this.getHeight()); 
    g.setColor(Color.black);
    for (int i=1;i<7;i++) {
      g.drawLine((this.getWidth()/7)*i,this.getHeight(),(this.getWidth()/7)*i,0);
    } // end of for
    for (int i=1;i<6;i++) {
      int a=15;
      g.drawLine(0,(this.getHeight()-a)/6*i+a,this.getWidth(),((this.getHeight()-a)/6*i+a));
    } // end of for
    
    
    for (int i=0;i<7;i++) {
      int a=15;
      for (int j=0;j<6;j++) {
        switch(feld[i][j]) {
            case 1: {
              g.setColor(Color.blue);
              g.fillOval(i*(this.getWidth()/7)+12,(5-j)*((this.getHeight()-a)/6)+25,this.getHeight()/6-25,this.getHeight()/6-25);
              break;
            }
          case 2: {
              g.setColor(Color.red);
              g.fillOval(i*(this.getWidth()/7)+12,(5-j)*((this.getHeight()-a)/6)+25,this.getHeight()/6-25,this.getHeight()/6-25);
            }
          
        }  
      }
      
    } // end of for
  } // end of for
  
} // end of class GameGUI
  