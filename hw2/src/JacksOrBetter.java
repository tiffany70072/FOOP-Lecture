import java.util.*;
import java.lang.*;
import java.io.*;

public class JacksOrBetter{ //整理instant variables，
	public static void main (String[] args)
	{   
	    int money = 1000;
        int round = 0;

        Casino casino = new Casino(); 
        Player player = new Player(); //interact with players
        Computer computer = new Computer(); 
        Shuffler shuffler = new Shuffler(); //draw the cards

        int deck[] = new int[52];        //52 poker cards
        int card[] = new int[5];         //5 cards in player's hand
        int keep[] = new int[5];   //the cards players want to keep after frist draw
        String option[] = {"(a) ","(b) ","(c) ","(d) ","(e) "}; //option for display
        
        casino.GameStart(money);
        while (true){ //Game start
            round ++;
	        player.Bet(round);
            if (player.bet==0) break; //if player quit the game
            money -= player.bet;
	        
	        computer.OpenNewDeck(deck);
	        shuffler.ShuffleCard(deck, 52); //52 poker cards
	        
	        shuffler.FirstDraw(deck, card, option);
	        player.Keep(card, keep, option);
	        shuffler.Exchange(deck, card, keep);

            computer.Judge(card); //determine the best hand
            money = player.Announcement(computer.bestHand, player.bet, money); 
            //display to the player
            if (money < 0) break; //if player goes bankrupt
        }
        casino.GameFinish(casino.name, round-1, money); 
	}
    
}