package cn.ycf.game;

import java.util.StringTokenizer;


/**
 * @author yuchaofei
 * @date 2017-7-7   
 * 下午11:43:03
 */
public class TicTacToe_I {
	
	
	private static final char EMPTY = '0';
	private static  final int CROSS = 1;  //表示叉叉 
	private static final int ROUND = 2;  //表示圆圈
	
	//以下是4种状态
	private static  final int PLAYING = 0;
	private static final int DRAW = 1;
	private static final int CROSS_WON = 2;
	private static final int ROUND_WON = 3;
	   
	private static int currentState = PLAYING;  // (PLAYING, DRAW, CROSS_WON, ROUND_WON), CROSS plays first
	private static int currentPlayer = CROSS;  // the current player (CROSS or ROUND)
	private static int currentRow, currentCol;  // current seed's row and column
	 
	Result result = null;
	private char[][] data = new char[][]{{'0', '0', '0'}, {'0', '0', '0'}, {'0', '0', '0'}};
	
	
	/**
     * Game的接口方法，我们会通过该方法进行测试
     * @param s为输入的操作序列
     * @return 游戏结果
     */
    public Result playGame(String s){
    	
		StringTokenizer str = new StringTokenizer(s, ",");  //使用逗号分隔，
		while (str.hasMoreElements() && currentState == PLAYING ) {
			
			 String string = (String) str.nextElement();
			 currentCol = string.charAt(0)-65;
			 currentRow = Character.getNumericValue(string.charAt(1))-1;
			
			playerMove(currentPlayer);
			updateGame(currentPlayer, currentRow, currentCol);
			
			if (currentState == CROSS_WON) {
	        	 return result.X_WIN;
			} else if (currentState == ROUND_WON) {
	        	 return result.O_WIN;
			}  else if (currentState == DRAW) {
		        	 return result.DRAW;	
			} // nothing  继续执行	 
			currentPlayer = (currentPlayer == CROSS) ? ROUND : CROSS;
		}
		printData();
		return result.GAMING;
   }   

    /* 填充二组数组*/
    public  void playerMove(int theSeed) {
    	
    	if (currentPlayer == CROSS) {
    		data[currentRow][currentCol] = 'X';
		} else {
			data[currentRow][currentCol] = 'O';
		}
    }
    
    public  void updateGame(int theSeed, int currentRow, int currentCol) {
        if (hasWon(theSeed, currentRow, currentCol)) {  // check if winning move
           currentState = (theSeed == CROSS) ? CROSS_WON : ROUND_WON;
        } else if (isDraw()) {  // check for draw
           currentState = DRAW;
        }
        printData();
        // Otherwise, no change to currentState (still PLAYING).
     }
    
    public  boolean hasWon(int theSeed, int currentRow, int currentCol) {
    	
    	if (theSeed == CROSS ) {
    		return (data[currentRow][0] == 'X'         // 三行
                    && data[currentRow][1] == 'X'
                    && data[currentRow][2] == 'X'
               || data[0][currentCol] == 'x'      // 三列
                    && data[1][currentCol] == 'X'
                    && data[2][currentCol] == 'X'
               || currentRow == currentCol            // 斜对角
                    && data[0][0] == 'X'
                    && data[1][1] == 'X'
                    && data[2][2] == 'X'
               || currentRow + currentCol == 2  // 斜对角
                    && data[0][2] == 'X'
                    && data[1][1] == 'X'
                    && data[2][0] == 'X');
		} else {
			return (data[currentRow][0] == 'O'        
                    && data[currentRow][1] == 'O' 
                    && data[currentRow][2] == 'O' 
               || data[0][currentCol] == 'O'       
                    && data[1][currentCol] == 'O' 
                    && data[2][currentCol] == 'O' 
               || currentRow == currentCol            
                    && data[0][0] == 'O' 
                    && data[1][1] == 'O' 
                    && data[2][2] == 'O' 
               || currentRow + currentCol == 2  
                    && data[0][2] == 'O' 
                    && data[1][1] == 'O' 
                    && data[2][0] == 'O' );
		}
        
     }
    
    
  	public  boolean isDraw() {
      for (int row = 0; row < 3; ++row) {
         for (int col = 0; col < 3; ++col) {
            if (data[row][col] == EMPTY) {
               return false;            // an empty cell found, not draw, exit
            }
         }
      }
      return true;  // no empty cell, it's a draw
   }
  	
  	 /** Print the game board */
    public  void printData() {
       int lineNum = 0;
       System.out.print(" A B C"+"\n");
       for (int row = 0; row < 3; row++) {
    	  lineNum = row + 1;  //打印行号
     	  System.out.print(lineNum+" ");
          for (int col = 0; col < 3; col++) {
        	  
             if(data[row][col] == '0'){
            	 System.out.print("_ ");
             } else {
            	 System.out.print(data[row][col]+" ");
			 } 
          }
          System.out.println();
       } 
    }
    
    
}
