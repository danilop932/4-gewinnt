public class KI {  
  int spalte;   
  public KI(){
  }
  private int[][] feld=new int[7][6];
  public int getSpalte(){
    return spalte; 
  }
  public void spiel(int amZug){
    if (leer()) {
      System.out.println("Leer");
      spalte=3;
      return;
    } // end of if
    if (win(amZug)) {
      return;
    } // end of if
    if (win((amZug-1)*-1+2)) {
      return;
    } // end of if
    
  }
  public void setFeld(int[][] neuFeld){
    feld=neuFeld;
  }
  public boolean leer(){
    for (int i=0;i<7;i++) {
      for (int j=0;j<6;j++) {
        if (feld[i][j]!=0) {
          return false;
        } // end of if
      } // end of for
    } // end of for
    return true;
  }
  public boolean win(int amZug){
    for (int i=0;i<7;i++) {
      for (int j=0;j<6;j++) {
        if (feld[i][j]==0) {
          feld[i][j]=amZug;
          if (won()) {
            feld[i][j]=0;
            spalte=i;
            return true;
          } // end of if
          else {
            feld[i][j]=0;
          } // end of if-else
        } // end of if
      } // end of for
    } // end of for
    return false;
  }
  public boolean won(){
    for (int i=0;i<7;i++) {
      for (int j=0;j<6;j++) {
        int check=feld[i][j];
        if (check!=0) {
          if (i<4) {
            if (feld[i+1][j]==check && feld[i+2][j]==check && feld[i+3][j]==check) {
              return true;
            } // end of if
          } // end of if
          if (j<3) {
            if (feld[i][j+1]==check && feld[i][j+2]==check && feld[i][j+3]==check) {
              return true;
            } // end of i
          } // end of if
          
          if (j<3 && i<4) {
            if (feld[i+1][j+1]==check && feld[i+2][j+2]==check && feld[i+3][j+3]==check) {
              return true;
            } // end of if
          } // end of if
          if (j>2 && i<4) {
            if (feld[i+1][j-1]==check && feld[i+2][j-2]==check && feld[i+3][j-3]==check) {
              return true;
            } // end of if
          } // end of if
        } // end of if
        
      } // end of for
    } // end of for
    return false; 
  }
}