/* package whatever; // don't place package name! */
 
import java.util.*;
import java.lang.*;
import java.io.*;
 
/* Name of the class has to be "Main" only if the class is public. */
public class Main
{   
	public static void name(int x){ //顯示撲克牌
		String y,z;
		if (x>1) {
			if (x%4==0) y="H";
			else if (x%4==1) y="S";
			else if (x%4==2) y="C";
			else y="D";
			if (x<38) 
				z=Integer.toString((x+6)/4);
		    else if (x<42) z="J";
		    else if (x<46) z="Q";
	    	else if (x<50) z="K";
	    	else z="A";
		}
		else if (x==1) 
			{y="B"; z="0";}
		else 
			{y="R"; z="0";}
 
		System.out.printf( "%3s ",(y+z)); 
	}
 
	public static int[][] dropCards(int x[], int xl, int k){ //配對＋顯示
		int i,j,m,n;
	    for (i=0; i<xl-1; i++){
			m=(x[i]+2)/4; 
			n=(x[i+1]+2)/4;
			if (m==n && m!=0) {
				xl-=2;
				for (j=i; j<xl; j++) x[j]=x[j+2];
				i--;}
		}
	    System.out.printf("\nPlayer%d:",k);
		for (i=0; i<xl; i++) name(x[i]);
		int drop[][] = {x,{xl}};
		return drop;
	}
 
	public static int[][] drawCard(int x[], int xl, int kx, int y[], int yl, int ky){ //抽牌＋顯示
		int i,j,k;
		int drop[][] = new int[2][];
		Random ran = new Random();
 
		j=ran.nextInt(yl);
		System.out.printf("\nPlayer%d  draws a card from Player%d ",kx,ky);
		name(y[j]);
 
		x[xl]=y[j];
		xl+=1; 
		Arrays.sort(x,0,xl);
 
		yl-=1; 
		for (k=j; k<yl; k++) y[k]=y[k+1];
 
		drop = dropCards(x,xl,kx);
		xl = drop[1][0];
		for (i=0; i<xl; i++) x[i] = drop[0][i];
 
		drop = dropCards(y,yl,ky);
		yl = drop[1][0];
		for (i=0; i<yl; i++) y[i] = drop[0][i];
 
        int draw[][] = {x,{xl},y,{yl}};
        return draw;
	}
    // 註解，continue之後的下一個玩家，players陣列的位子，分行，一開始就獲勝
	public static void main (String[] args) throws java.lang.Exception
	{
		int i,j,k,m,n;
		int al=14, bl=14, cl=13, dl=13, total=54; //牌數
	    int s[] = new int[total]; //total poker cards
		int x[] = new int[total]; //次序-亂數陣列
		int x0[]= new int[total]; //次序-原始陣列
	    int a[] = new int[al]; //player0
	    int b[] = new int[al]; //player1
	    int c[] = new int[cl]; //player2
	    int d[] = new int[cl]; //player3
	    Random ran = new Random(); //宣告一個Random，變數名稱為ran
	    int drop[][] = new int[2][]; //回傳dropCards的值
	    int draw[][] = new int [6][]; //回傳drawCard的值
	    int y[],yl,yk,z[],zl,zk; //抽牌陣列
	    int round=0;
 
	    //建立撲克牌
	    for (i=0; i<total; i++){
	        x0[i] = i; s[i] = i;} 
 
	    //建立亂數陣列  
	    for (i=0; i<total; i++){
	    	j = ran.nextInt(total-i);
	        x[i] = x0[j];
	       	for (k=j; k<x0.length-1; k++) {
	       		x0[k] = x0[k+1];}
	    } 
 
	    //開始發牌
		System.out.print("Deal cards"); 
		System.out.print("\nPlayer0:");
		for (i=0; i<al; i++) a[i] = s[x[i]];
		Arrays.sort(a);
	    for (i=0; i<al; i++) name(a[i]);
 
		System.out.print("\nPlayer1:");
		for (i=0; i<bl; i++) b[i] = s[x[i+al]];
		Arrays.sort(b);
	    for (i=0; i<bl; i++) name(b[i]);
 
		System.out.print("\nPlayer2:");
		for (i=0; i<cl; i++) c[i] = s[x[i+2*al]];
		Arrays.sort(c);
	    for (i=0; i<cl; i++) name(c[i]);
 
		System.out.print("\nPlayer3:");
		for (i=0; i<dl; i++) d[i] = s[x[i+2*al+cl]];
		Arrays.sort(d);
	    for (i=0; i<dl; i++) name(d[i]);

        int players = 4; //玩家數
        int player[][]= {a,b,c,d}; //將玩家丟進一個陣列
        int playerl[] = {al,bl,cl,dl}; //每個玩家的牌數
        int playerk[] = {0,1,2,3}; //玩家次序

	    //開始丟牌
	    System.out.print("\nDrop cards"); 

	    for (j=0; j<players; j++){
	    	drop = dropCards(player[j],playerl[j],playerk[j]);
		    playerl[j] = drop[1][0];
		    for (i=0; i<playerl[j]; i++) {
		    	player[j][i] = drop[0][i];
		    }
	    }
 
		//開始抽牌
		System.out.print("\nGame start");

        while (players>1){
        	j = round%players; //指定抽牌的玩家為j
        	k = (round+1)%players; //指定被抽牌的玩家為k
        	y = player[j];
        	z = player[k];
        	yl= playerl[j];
        	zl= playerl[k];
        	yk= playerk[j];
        	zk= playerk[k];
 
	    	draw = drawCard(y,yl,yk,z,zl,zk); //抽牌
	    	yl = draw[1][0];
	     	for (i=0; i<yl; i++) y[i] = draw[0][i];
	    	zl = draw[3][0];
	    	for (i=0; i<yl; i++) z[i] = draw[2][i];

	        player[j] = y;
        	player[k] = z;
        	playerl[j] = yl;
        	playerl[k] = zl;
        	playerk[j] = yk;
        	playerk[k] = zk;

            if (playerl[j]==0 && playerl[k]==0) { //若同時有兩人獲勝
	    		System.out.printf("\nPlayer%d and Player%d win\n",playerk[j],playerk[k]);
	    		for (i=j; i<players-2; i++){
	    			    player [i] = player [i+2];
                        playerl[i] = playerl[i+2];
                        playerk[i] = playerk[i+2];
	    		    }
	    		if (players==4) {
	    			System.out.print("Basic game over\n\nContinue");
	    		}
	    		players -= 2;
	    		round = 0;
	    	}

        	else if (playerl[j]==0 || playerl[k]==0) { //若有一人獲勝
	    		System.out.print("\nPlayer");
	    		if (playerl[j]==0){
	    			System.out.printf("%d",playerk[j]); 
	    		    for (i=j; i<players-1; i++){
	    			    player [i] = player [i+1];
                        playerl[i] = playerl[i+1];
                        playerk[i] = playerk[i+1];
                        round = j-1;
	    		    }
	    		}
	    		if (playerl[k]==0){
	    			System.out.printf("%d",playerk[k]); 
	    		    for (i=k; i<players-1; i++){
	    			    player [i] = player [i+1];
                        playerl[i] = playerl[i+1];
                        playerk[i] = playerk[i+1];
                        round = k-1;
	    		    }
	    		}
	    		System.out.println(" wins");
	    		if (players==4) {
	    			System.out.print("Basic game over\n\nContinue");
	    		}
	    		players -= 1;
	    	}  
		    round+=1; //下一次抽牌
	    }
	    //遊戲結束
	    System.out.println("Bonus game over"); 
	    
	    //finish line*/
	}
}