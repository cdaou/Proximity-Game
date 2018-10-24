//Φαλάρα Ελπίδα-8305-6981553265-elpidafal@gmail.com
//Νταούλας Χρήστος-Παναγιώτης-8208-6982538435-dchp95@hotmail.com

package gr.auth.ee.dsproject.proximity.defplayers;

import gr.auth.ee.dsproject.proximity.board.Board;
import gr.auth.ee.dsproject.proximity.board.Tile;


import java.util.HashMap;


import gr.auth.ee.dsproject.proximity.board.ProximityUtilities;


/*Η κλάση HeuristicPlayer 11 συναρτήσεις εκ των οποίων οι δύο τελευταίες έχουν 
συμπληρωθεί με το ζητούμενο κώδικα */
public class HeuristicPlayer implements AbstractPlayer
{	
//Μεταβλητές της κλάσης
  int score;
  int id;
  String name;
  int numOfTiles;

 /*Constructor-δίνει τιμή στην ταυτότητα-χρώμα του παίκτη(μεταβλητή id) και το όνομά του
 (μεταβλητή name)*/
  public HeuristicPlayer (Integer pid)
  {
    id = pid;
    name = "Heuristic";
  }
  
//Επιστρέφεται το όνομα του παίκτη(μεταβλητή name)
  public String getName ()
  {
    return name;
  }
  
//Επιστρέφεται τον αριθμό των πλακιδίων του παίκτη(μεταβλητή numOfTiles)
  public int getNumOfTiles ()
  {
    return numOfTiles;
  }

 //Ορίζεται ο αριθμός των πλακιδίων του παίκτη που βρίσκεται στο ταμπλό κάθε
 //δεδομένη στιγμή(μεταβητή numOfTiles)
  public void setNumOfTiles (int tiles)
  {
    numOfTiles = tiles;
  }

 //Επιστρέφεται η ταυτότητα-χρώμα του παίκτη(μεταβλητή id)
  public int getId ()
  {
    return id;
  }

 //Ορίζεται το σκορ του παίκτη(μεταβλητή score)
  public void setScore (int score)
  {
    this.score = score;
  }

 //Επιστρέφεται το σκορ του παίκτη(μεταβλητή score)
  public int getScore ()
  {
    return score;
  }

 //Ορίζεται η ταυτότητα-χρώμα του παίκτη(μεταβλητή id)
  public void setId (int id)
  {
    this.id = id;
  }
 
//Ορίζεται το όνομα του παίκτη(μεταβλητή name)
  public void setName (String name)
  {
    this.name = name;
  }
  
  
/*Συνάρτηση που επιστρέφει έναν πίνακα μονοδιάστατο τριών θέσεων τύπου int που περιέχει
τις συντεταγμένες της επόμενης καλύτερης κίνησης και τον
τυχαίο αριθμό randomNumber(είναι το σκορ του πλακιδίου από τη δεξαμενή που θα 
τοποθετηθεί)που υπάρχει ως όρισμα.Επίσης, ως όρισμα υπάρχει ένα αντκείμενο τύπου board.
quot:όνομα της δομής HashMap, max:Μεταβλητή που περιέχει το μέγιστο ποσοστό από αυτά 
που υπάρχουν στην quot, temp:Αντικείμενο τύπου Tile που περιέχει τα χαρακτηριστικά του 
πλακιδίου που έχει το max, nextArray:Ο ζητούμενος πίνακας 3 θέσεων που επιστρέφεται.*/
  public int[] getNextMove (Board board, int randomNumber){
	  HashMap<Tile,Double> quot=new HashMap<Tile,Double>() ;
		double max=-1;
		Tile temp = null;
		int[] nextArray=new int[3];
		
		for(int i=0;i<ProximityUtilities.NUMBER_OF_COLUMNS;i++){
			for(int j=0;j<ProximityUtilities.NUMBER_OF_ROWS;j++){
				quot.put(board.getTile(i,j), getEvaluation(board,randomNumber,board.getTile(i,j)));
				if(max<quot.get(board.getTile(i, j))){
					max=quot.get(board.getTile(i, j));
					temp=board.getTile(i, j);
				}
			}
		}
		
		nextArray[0]=temp.getX();
		nextArray[1]=temp.getY();
		nextArray[2]=randomNumber;
		
	    return nextArray;
  }
/*Συνάρτηση που αξιολογεί κατα πόσο μία θέση στο ταμπλό είναι καλή για να τοποθετηθεί
 * το πλακίδιο.Δέχεται ως ορίσματα ένα αντικείμενο τύπου Board,τον τυχαίο αριθμό που
 * έχει το πλακίδιο που πρόκειται να τοποθετήσω και ένα αντικείμενο τύπου Tile.
 * nb:Πίνακας έξι θέσεων τύπου Tile, όπου κάθε θέση είναι ένα γειτονικό αντικείμενο
 * (τύπου Tile) του πλακιδίου για το οποίο γίνεται η αξιολόγηση.
 * total:Μεταβλητή που περιέχει το συνολικό σκορ που αναγράφεται στα γειτονικά πλακίδια,
 * των οποίων τα σκορ υπάρχουν στα αντικείμενα το πίνακα nb.
 * quot:Μεταβλητή που περιέχει το ποσοστό καταλληλότητας μιας υποψήφιας θέσης[x,y] για να 
 * τοποθετηθεί ένα πλακίδιο, που τελικώς επιστρέφεται.Το ποσοστό αυτό είναι αθροισμα 
 * των τριών αξιολογήσεων. */
  double getEvaluation (Board board, int randomNumber, Tile tile){
	  Tile[] nb=new Tile[6];
	  double total=0;
	  double quot=0;
	  	  
// Πρώτος τρόπος αξιολόγησης 
	  if(tile.getColor()==0){
		  nb=ProximityUtilities.getNeighbors(tile.getX(),tile.getY(),board);
			for(int k=0;k<6;k++){
				if(nb[k]!=null && nb[k].getScore()<randomNumber){
					total=+ nb[k].getScore(); 
				}
				else{
					total=-1;
				}
			}
			if(total!=-1){
				quot=(25*total)/117;
			}
		
// Δεύτερος τρόπος αξιολόγησης
		     for(int k=0;k<6;k++){
				if(nb[k]!=null && nb[k].getScore()<randomNumber && nb[k].getColor()==1){
					total=+ nb[k].getScore(); 
				}
				else{
					total=-1;
				}
			}
			if(total!=-1){
				quot=+ (25*total)/117;
			}
				
// Τρίτος τρόπος αξιολόγησης		
			for(int k=0;k<6;k++){
				if(nb[k]!=null && nb[k].getScore()<randomNumber){
					total=+ 1; 
				}else{
					total=-1;
				}
			}
			if(total!=-1){
				quot=+ (25*total)/6;
			}	
	  }	
	return quot;
  }

}
 


