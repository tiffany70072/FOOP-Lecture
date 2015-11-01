import java.util.*;
import java.lang.*;
import java.io.*;

class Computer{ 
	private int i, j;
    public int bestHand;   //return the result of method Judge

	public void OpenNewDeck(int deck[]){ //Computer open new deck of 52 poker cards
		for (i=0; i<52; i++)
            deck[i] = i;
    }

    public void Judge(int card[]){ //Computer determines the best hand
        //TestResult(card);
           
        int straight=0; //if straight
        int royal=0;    //if royal
        int jack=0;     //if Jacks or better

        int count = CountPairs(card); //if any pair 
        int flush = IsFlush(card);

        if (count==1) //if only one pair, then determine if it is Jacks or better
            jack = IsJacksOrBetter(card); 
            
        if (count==6) bestHand = 2;      //2 four of a kind
        else if (count==4) bestHand = 3; //3 full House
        else if (count==3) bestHand = 6; //6 three of a kind
        else if (count==2) bestHand = 7; //7 two pairs
        else if (count==1 && jack==1) bestHand = 8; //8 Jacks or better
        else if (count==0){              //no pair, each number only appears once
            if (card[4]/4-card[0]/4==4){ //number of last card minus first card equal four
                straight=1; 
                if (card[4]>=48) royal++; //if (10 J Q K A), card no.48 to 51 is A
            }
            else if (card[3]/4==3 && card[0]/4==0 && card[4]>=48) //if (A 2 3 4 5)
                straight=1;

            if (straight==1 && flush==1 && royal==1) //0 royal flush
                bestHand = 0;
            else if (straight==1 && flush==1) //1 straight flush
                bestHand = 1; 
            else if (flush==1) //4 flush
                bestHand = 4;
            else if (straight==1) //5 straight
                bestHand = 5;
            else //9 others
                bestHand = 9;
        }
        else bestHand = 9;
    }

    public int CountPairs(int card[]){
        int count=0;
        for (i=0; i<4; i++){
            for (j=i+1; j<5; j++){
                if (card[i]/4 == card[j]/4) 
                    count++;
            }
        } 
        return count;  
    }

    public int IsFlush(int card[]){ //flush=1 if flush, or flush=0
        int S[] = new int[4];  //how many cards of every suit, from C to S;
        int suit;
        for (i=0; i<5; i++){
            suit = card[i]%4;
            S[suit]++;
            if (S[suit]==5)
                return 1;
        }
        return 0; 
    }

    public int IsJacksOrBetter(int card[]){
        for (i=0; i<4; i++){
            if (card[i]/4==card[i+1]/4 && card[i]>=36) //card no.36 is CJ
                return 1;
        }
        return 0;
    }

    public void TestResult(int card[]){
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Please enter 5 integers for test (0 to 51): ");
        System.out.println("For example: 2(0-3), 3(4-7)..., K(44-47), A(48-51)");
        System.out.println("For example: 35 39 43 47 51 => S10 SJ SQ SK SA => royal flush");
        System.out.print("here: ");
        for (i=0; i<5; i++){
            card[i] = scanner.nextInt();
        }
        Arrays.sort(card);
        System.out.print("Your cards are ");
        for (i=0; i<5; i++){
            Card.ShowCard(card[i]);
        }
        System.out.println();
    }
}
