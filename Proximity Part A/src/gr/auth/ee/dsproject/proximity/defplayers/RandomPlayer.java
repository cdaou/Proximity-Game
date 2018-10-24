//Φαλάρα Ελπίδα - ΑΕΜ:8305 - 6981553265 - elpidafal@gmail.com
//Νταούλας Χρήστος-Παναγιώτης - ΑΕΜ:8208 - 6982538435 - dchp95@hotmail.com


package gr.auth.ee.dsproject.proximity.defplayers;

import gr.auth.ee.dsproject.proximity.board.Board;
import gr.auth.ee.dsproject.proximity.board.ProximityUtilities;

public class RandomPlayer implements AbstractPlayer
{
	int id;
	String name ;
	int score ;
	int numOfTiles ;
	  
	 //Κατασκευάζουμε τον 1ο constructor, που αρχικοποιεί την τιμή της μεταβλητής id.  
	  public RandomPlayer(Integer pid){
		  id=pid ;
	  }
      
	  //Κατασκευάζουμε τον 2ο constructor, αρχικοποεί τις τιμές των μεταβλητών id,name,score,numOfTiles.
	  public RandomPlayer(Integer pid,String pname, Integer pscore, Integer pnumofTiles ){
		  id=pid;
		  name=pname;
		  score=pscore;
		  numOfTiles=pnumofTiles;
	  }
	  
	  //Στους παρακάτω setters, oι μεταβλητές pid,pname,pscore,pnumofTiles είναι μεταβλητές που χρησιμοποιούνται για να "περάσουμε"
	  //τις αντίστοιχες τιμές στις μεταβλητές(id,name,score,numOfTiles) στο τμήμα του κώδικα που ορίζεται η συνάρτηση.   
	  public void setId(int pid){
		  id=pid;
	  }
	  public void setName(String pname){
		  name=pname;
	  }
	  public void setScore(int pscore){
		  score=pscore;
	  }
	  public void setNumOfTiles(int pnumofTiles){
		  numOfTiles=pnumofTiles ;
	  }
	  
	  
	  //Οι παρακάτω getters χρησιμοποιούνται για να επιστρέψουμε τις τιμές των 4 μεταβλητών της κλάσης.
	  public int getId(){return id;}
	  public String getName(){return name;}
	  public int getScore(){return score;}
	  public int getNumOfTiles(){return numOfTiles;}
	  
	  //Δηλώνουμε τις δύο μεταβλητές x,y οι οποίες αντιστοιχούν σε τυχαίες συντεταγμένες.
	  int x,y;
	  
	  //Αρχικά επιλέγονται τυχαία οι τιμές των συντεταγμένων (x,y) και ελέγχεται αν ανήκουν στα όρια του ταμπλό.
	  //Με την ίδια συνθήκη ελέγχεται αν η συγκεκριμένη θέση δεν είναι κατειλημμένη, δηλαδή εάν το χρώμα του πλακιδίου
	  //είναι γκρι (δηλαδή 0).Αν ναι, τότε οι αντίστοιχες συντεταγμένες τοποθετούνται στον πίνακα compArray και επιστρέφεται.
	  //Αν όχι, επαναλαμβάνεται η διαδικασία έως ότου οι συντεταγμένες να τηρούν την συνθήκη.
	  public int[] getNextMove (Board board){
			
		int[] compArray= new int[2];
		do{
		   x= (int)(Math.random()*ProximityUtilities.NUMBER_OF_COLUMNS);
		   y= (int)(Math.random()*ProximityUtilities.NUMBER_OF_ROWS) ;		  
		}while(board.getTile(x,y).getColor()!=0);
		
		compArray[0]=x;
		compArray[1]=y;
		return compArray; 
	  }
	  
	  //Η συνάρτηση αυτή εντοπίζει και επιστρέφει τις συντεταγμένες των γειτονικών πλακιδίων, οι οποίες
	  //αποθηκεύονται στον πίνακα nbArray.
	  public static int[][] getNeighborsCoordinates(Board board,int x, int y){
		//Δήλωση και δημιουργία του πίνακα nbArray.
		int[][] nbArray=new int[6][2];
		//Αρχικοποίηση του πίνακα με την τιμή -1.
		for(int i=0;i<6;i++){
			nbArray[i][0]=-1;
			nbArray[i][1]=-1;
		}
		//Διαχωρίζουμε δύο περιπτώσεις, ανάλογα με το αν είναι ζυγός ή όχι η τεταγμένη y.
		//Παρατηρώντας τις συντεταγμένες των εξαγώνων (Εικόνα 2) προκύπτουν οι παρακάτω σχέσεις,
		//για το πως μεταβάλλονται οι συντεταγμένες των γειτονικών πλακιδίων, ανάλογα την περίπτωση.
        if(y%2==1){
        	//Με την συνθήκη board.isInsideBoard( , ) ελέγχουμε αν το συγκεκριμένο γειτονικό πλακίδιο,
        	//βρίσκεται εντός του ταμπλό.Εάν όχι παραμένει η τιμή -1 (που αρχικοποιήσαμε πιο πανω).
        	if(board.isInsideBoard(x+1, y)){
        		nbArray[0][0]=x+1;
        		nbArray[0][1]=y;
        	}
            if(board.isInsideBoard(x+1, y+1)){
            	nbArray[1][0]=x+1;
        		nbArray[1][1]=y+1;
        	}
            if(board.isInsideBoard(x, y+1)){
        		nbArray[2][0]=x;
        		nbArray[2][1]=y+1;
        	}
            if(board.isInsideBoard(x-1, y)){
        		nbArray[3][0]=x-1;
        		nbArray[3][1]=y;
        	}
            if(board.isInsideBoard(x, y-1)){
        		nbArray[4][0]=x;
        		nbArray[4][1]=y-1;
        	}
            if(board.isInsideBoard(x+1, y-1)){
        		nbArray[5][0]=x+1;
        		nbArray[5][1]=y-1;
        	}
        }else{
        	if(board.isInsideBoard(x+1, y)){
        		nbArray[0][0]=x+1;
        		nbArray[0][1]=y;
        	}
            if(board.isInsideBoard(x, y+1)){
            	nbArray[1][0]=x;
        		nbArray[1][1]=y+1;
        	}
            if(board.isInsideBoard(x-1, y+1)){
        		nbArray[2][0]=x-1;
        		nbArray[2][1]=y+1;
        	}
            if(board.isInsideBoard(x-1, y)){
        		nbArray[3][0]=x-1;
        		nbArray[3][1]=y;
        	}
            if(board.isInsideBoard(x-1, y-1)){
        		nbArray[4][0]=x-1;
        		nbArray[4][1]=y-1;
        	}
            if(board.isInsideBoard(x, y-1)){
        		nbArray[5][0]=x;
        		nbArray[5][1]=y-1;
        	}
        }
		
		//Επιστρέφουμε τον πίνακα.
		return nbArray ;
	  }
	
};