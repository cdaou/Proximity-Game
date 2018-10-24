//������ ������ - ���:8305 - 6981553265 - elpidafal@gmail.com
//�������� �������-���������� - ���:8208 - 6982538435 - dchp95@hotmail.com


package gr.auth.ee.dsproject.proximity.defplayers;

import gr.auth.ee.dsproject.proximity.board.Board;
import gr.auth.ee.dsproject.proximity.board.ProximityUtilities;

public class RandomPlayer implements AbstractPlayer
{
	int id;
	String name ;
	int score ;
	int numOfTiles ;
	  
	 //�������������� ��� 1� constructor, ��� ����������� ��� ���� ��� ���������� id.  
	  public RandomPlayer(Integer pid){
		  id=pid ;
	  }
      
	  //�������������� ��� 2� constructor, ���������� ��� ����� ��� ���������� id,name,score,numOfTiles.
	  public RandomPlayer(Integer pid,String pname, Integer pscore, Integer pnumofTiles ){
		  id=pid;
		  name=pname;
		  score=pscore;
		  numOfTiles=pnumofTiles;
	  }
	  
	  //����� �������� setters, o� ���������� pid,pname,pscore,pnumofTiles ����� ���������� ��� ���������������� ��� �� "���������"
	  //��� ����������� ����� ���� ����������(id,name,score,numOfTiles) ��� ����� ��� ������ ��� �������� � ���������.   
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
	  
	  
	  //�� �������� getters ���������������� ��� �� ������������ ��� ����� ��� 4 ���������� ��� ������.
	  public int getId(){return id;}
	  public String getName(){return name;}
	  public int getScore(){return score;}
	  public int getNumOfTiles(){return numOfTiles;}
	  
	  //��������� ��� ��� ���������� x,y �� ������ ������������ �� ������� �������������.
	  int x,y;
	  
	  //������ ����������� ������ �� ����� ��� ������������� (x,y) ��� ��������� �� ������� ��� ���� ��� ������.
	  //�� ��� ���� ������� ��������� �� � ������������ ���� ��� ����� ������������, ������ ��� �� ����� ��� ���������
	  //����� ���� (������ 0).�� ���, ���� �� ����������� ������������� ������������� ���� ������ compArray ��� ������������.
	  //�� ���, ��������������� � ���������� ��� ���� �� ������������� �� ������ ��� �������.
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
	  
	  //� ��������� ���� ��������� ��� ���������� ��� ������������� ��� ���������� ���������, �� ������
	  //������������� ���� ������ nbArray.
	  public static int[][] getNeighborsCoordinates(Board board,int x, int y){
		//������ ��� ���������� ��� ������ nbArray.
		int[][] nbArray=new int[6][2];
		//������������ ��� ������ �� ��� ���� -1.
		for(int i=0;i<6;i++){
			nbArray[i][0]=-1;
			nbArray[i][1]=-1;
		}
		//������������ ��� �����������, ������� �� �� �� ����� ����� � ��� � ��������� y.
		//������������ ��� ������������� ��� �������� (������ 2) ���������� �� �������� �������,
		//��� �� ��� ������������� �� ������������� ��� ���������� ���������, ������� ��� ���������.
        if(y%2==1){
        	//�� ��� ������� board.isInsideBoard( , ) ��������� �� �� ������������ ��������� ��������,
        	//��������� ����� ��� ������.��� ��� ��������� � ���� -1 (��� �������������� ��� ����).
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
		
		//������������ ��� ������.
		return nbArray ;
	  }
	
};