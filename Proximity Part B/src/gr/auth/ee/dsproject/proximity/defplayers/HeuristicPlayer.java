//������ ������-8305-6981553265-elpidafal@gmail.com
//�������� �������-����������-8208-6982538435-dchp95@hotmail.com

package gr.auth.ee.dsproject.proximity.defplayers;

import gr.auth.ee.dsproject.proximity.board.Board;
import gr.auth.ee.dsproject.proximity.board.Tile;


import java.util.HashMap;


import gr.auth.ee.dsproject.proximity.board.ProximityUtilities;


/*� ����� HeuristicPlayer 11 ����������� �� ��� ������ �� ��� ���������� ����� 
����������� �� �� ��������� ������ */
public class HeuristicPlayer implements AbstractPlayer
{	
//���������� ��� ������
  int score;
  int id;
  String name;
  int numOfTiles;

 /*Constructor-����� ���� ���� ���������-����� ��� ������(��������� id) ��� �� ����� ���
 (��������� name)*/
  public HeuristicPlayer (Integer pid)
  {
    id = pid;
    name = "Heuristic";
  }
  
//������������ �� ����� ��� ������(��������� name)
  public String getName ()
  {
    return name;
  }
  
//������������ ��� ������ ��� ��������� ��� ������(��������� numOfTiles)
  public int getNumOfTiles ()
  {
    return numOfTiles;
  }

 //�������� � ������� ��� ��������� ��� ������ ��� ��������� ��� ������ ����
 //�������� ������(�������� numOfTiles)
  public void setNumOfTiles (int tiles)
  {
    numOfTiles = tiles;
  }

 //������������ � ���������-����� ��� ������(��������� id)
  public int getId ()
  {
    return id;
  }

 //�������� �� ���� ��� ������(��������� score)
  public void setScore (int score)
  {
    this.score = score;
  }

 //������������ �� ���� ��� ������(��������� score)
  public int getScore ()
  {
    return score;
  }

 //�������� � ���������-����� ��� ������(��������� id)
  public void setId (int id)
  {
    this.id = id;
  }
 
//�������� �� ����� ��� ������(��������� name)
  public void setName (String name)
  {
    this.name = name;
  }
  
  
/*��������� ��� ���������� ���� ������ ������������ ����� ������ ����� int ��� ��������
��� ������������� ��� �������� ��������� ������� ��� ���
������ ������ randomNumber(����� �� ���� ��� ��������� ��� �� �������� ��� �� 
�����������)��� ������� �� ������.������, �� ������ ������� ��� ���������� ����� board.
quot:����� ��� ����� HashMap, max:��������� ��� �������� �� ������� ������� ��� ���� 
��� �������� ���� quot, temp:����������� ����� Tile ��� �������� �� �������������� ��� 
��������� ��� ���� �� max, nextArray:� ���������� ������� 3 ������ ��� ������������.*/
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
/*��������� ��� ��������� ���� ���� ��� ���� ��� ������ ����� ���� ��� �� �����������
 * �� ��������.������� �� �������� ��� ����������� ����� Board,��� ������ ������ ���
 * ���� �� �������� ��� ��������� �� ���������� ��� ��� ����������� ����� Tile.
 * nb:������� ��� ������ ����� Tile, ���� ���� ���� ����� ��� ��������� �����������
 * (����� Tile) ��� ��������� ��� �� ����� ������� � ����������.
 * total:��������� ��� �������� �� �������� ���� ��� ����������� ��� ��������� ��������,
 * ��� ������ �� ���� �������� ��� ����������� �� ������ nb.
 * quot:��������� ��� �������� �� ������� �������������� ���� ��������� �����[x,y] ��� �� 
 * ����������� ��� ��������, ��� ������� ������������.�� ������� ���� ����� �������� 
 * ��� ����� ������������. */
  double getEvaluation (Board board, int randomNumber, Tile tile){
	  Tile[] nb=new Tile[6];
	  double total=0;
	  double quot=0;
	  	  
// ������ ������ ����������� 
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
		
// �������� ������ �����������
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
				
// ������ ������ �����������		
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
 


