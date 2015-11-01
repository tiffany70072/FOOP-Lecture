import java.util.*;
import java.lang.*;
import java.io.*;

class Player{ //Player interacts with the decisions from the player
	int i, j;
    private int count;
	public int bet;
	public Scanner scanner = new Scanner(System.in);
	
	public void Bet(int round){ //ask player to bet
        bet = -1;
        System.out.printf("\nRound %d: ",round);

        while (bet <0 || bet >5){ //avoid player enter other than 0 to 5
            System.out.print("Please enter your P-dollar bet ");
            System.out.print("(1-5 or 0 for quitting the game): ");
            bet = scanner.nextInt();
        }   
    }

    public void Keep(int card[], int keep[], String option[]){ //the card player want to keep at first draw
        char keeping[] = PlayerWantToKeep();
        ChangeKeepTypeToInt(keep, keeping);
        
        System.out.print("Okay. I will discard ");
        count = 0;
        for (i=0; i<5; i++){
            if (keep[i]==1)
                count++;
            if (keep[i]==0){ //card for discard
                System.out.print(option[i]);
                Card.ShowCard(card[i]);
            }
        }
        if (count==5)
            System.out.print("nothing.");
        System.out.println();
    }

    public char[] PlayerWantToKeep(){ //avoid player enter other than a to e
        char keeping[] = new char[100]; 
        while (true){
            System.out.print("Which cards do you want to keep? (Please enter a to e) ");  
        
            keeping = scanner.next().toCharArray(); //string type
            if (keeping[0]=='n' && keeping.length==1) //if player keeps none
                return keeping;

            count = 0;
            for (i=0; i<keeping.length; i++){
                if (keeping[i]=='a' || keeping[i]=='b' || keeping[i]=='c' || keeping[i]=='d' || keeping[i]=='e')
                        count++; 
            }

            if (count==keeping.length) //all terms is in a to e, then continue
                return keeping;
        }
    }

    public void ChangeKeepTypeToInt(int keep[], char keeping[])
    { //change string type to integer for later convenience
        int s; //s=1 for keep, s=0 for discard
        char check[] = {'a','b','c','d','e'}; //just for check
        for (i=0; i<5; i++){ 
            s = 0;
            for (j=0; j<keeping.length; j++){
                if (check[i]==keeping[j]){
                    s++;
                    j = keeping.length;
                }
            }
            if (s==1) keep[i]=1; //keep
            else keep[i]=0; //discard
        }
    }

    public int Announcement(int theBestHand, int bet, int money){ //display the result to the player
        int payoff;
        String result;
        switch (theBestHand){ //10 cases
            case 0:
                payoff = 250;
                result = "a royal flush";
                break;
            case 1:
                payoff = 50;
                result = "a straight flush";
            break;
            case 2:
                payoff = 25;
                result = "four of a kind";
            break;
            case 3:
                payoff = 9;
                result = "a full House";
            break;
            case 4:
                payoff = 6;
                result = "a flush hand";
            break;
            case 5:
                payoff = 4;
                result = "a straight hand";
            break;
            case 6:
                payoff = 3;
                result = "three of a kind";
            break;
            case 7:
                payoff = 2;
                result = "two pairs";
            break;
            case 8:
                payoff = 1;
                result = "Jacks or better";
            break;
            default:
                payoff = 0;
                result = "others";
            break;
        }

        if (theBestHand==0 && bet==5){ //for royal flush and bet 5
            payoff=800;
        }

        payoff *= bet;
        money = money + payoff;
        System.out.print("You get " + result + ". ");
        System.out.println("The payoff is " + payoff + ".");
        System.out.println("Your have " + money + " P-dollars now.");
        return money;
    }
}