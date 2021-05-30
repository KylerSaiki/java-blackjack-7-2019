import java.util.Scanner;

public class Project2 {

    public static void main( String [] args ) {
        Scanner input = new Scanner( System.in );
        System.out.println("\nWould you like to play Blackjack? Enter Y or N");
        char wantsToPlayBlackjack = input.next().charAt(0);
        
        //check if the player wants to play blackjack.  the player will keep playing until they enter N 
        while (wantsToPlayBlackjack == 'Y') {
            playGame();
            System.out.println("\nWould you like to play Blackjack again? Enter Y or N");
            wantsToPlayBlackjack = input.next().charAt(0);
        }

    }

    public static void playGame() {
        Deck gameDeck = new Deck(true);
        Card[] playerHand = new Card[2];
        Card[] dealerHand = new Card[2];

        //draw the player's first two cards and print them to the console.  
        playerHand[0] = drawCard(gameDeck);
        System.out.println("\nYour first card is: " + playerHand[0].outputCard());
        gameDeck = new Deck(gameDeck.updateDeck());

        playerHand[1] = drawCard(gameDeck);
        System.out.println("Your second card is: " + playerHand[1].outputCard());
        gameDeck = new Deck(gameDeck.updateDeck());

        //display the player's total points
        System.out.println("Your point total is: " + checkPointTotal(playerHand));

        //if the player's hand doesn't equal 21, do the code inside
        if (checkPointTotal(playerHand) != 21) {

            //draw the dealer's first two cards and print the face up card to the console.
            dealerHand[0] = drawCard(gameDeck);
            gameDeck = new Deck(gameDeck.updateDeck());

            dealerHand[1] = drawCard(gameDeck);
            gameDeck = new Deck(gameDeck.updateDeck());

            System.out.println("\nThe dealer's face up card is: " + dealerHand[0].outputCard());

            boolean hitting = true;
            Card[] updatedPlayerHand = new Card[2];
            updatedPlayerHand[0] = playerHand[0];
            updatedPlayerHand[1] = playerHand[1];

            //check if the player wants to hit
            while(hitting == true) {

                //if the player wants to hit, deal him another card, calculate the total points, and determine the result.  check if he wants to hit again
                if (checkPlayerAction() == 'Y') {
                    Card[] previousPlayerHand = updatedPlayerHand;
                    updatedPlayerHand = new Card[updatedPlayerHand.length + 1];

                    for (int h = 0; h < (updatedPlayerHand.length - 1); h++) {
                        updatedPlayerHand[h] = previousPlayerHand[h];

                    }

                    updatedPlayerHand[updatedPlayerHand.length - 1] = drawCard(gameDeck);
                    gameDeck = new Deck(gameDeck.updateDeck());
                    System.out.println("Your drew a: " + updatedPlayerHand[updatedPlayerHand.length - 1].outputCard());
                    
                    System.out.print("Your hand consists of: ");   
                    for (int j = 0; j < updatedPlayerHand.length; j++) {
                        System.out.print(updatedPlayerHand[j].outputCard() + ", ");
                    }

                    System.out.println("\nYour point total is: " + checkPointTotal(updatedPlayerHand));


                    if (checkPointTotal(updatedPlayerHand) >= 21) {
                        break;
                    }

                    continue;

                //if the player doesn't want to hit, print out his final cards and break from the loop
                } else if (checkPlayerAction() == 'N') {
                    System.out.print("Your hand consists of: \n");   

                    for (int j = 0; j < updatedPlayerHand.length; j++) {
                        System.out.print(updatedPlayerHand[j].outputCard() + ", ");
                    }

                    hitting = false;
                    break;

                //if the player doesnt enter a Y or N, check again for new input
                } else {
                    System.out.println("input not recognized.  please enter either Y or N");
                    continue;

                }
            }

            //check result after the while loop and determine the results
            if (checkPointTotal(updatedPlayerHand) == 21) {
                System.out.println("You have Blackjack.  You win");
                return;
            } else if (checkPointTotal(updatedPlayerHand) > 21) {
                System.out.println("You bust.  You Lose");
                return;
            }

            Card[] updatedDealerHand =  new Card[2]; 
            updatedDealerHand[0] = dealerHand[0];
            updatedDealerHand[1] = dealerHand[1];

            //hit the dealer until his point total is above 17
            while (checkPointTotal(dealerHand) < 17) {
                Card[] previousDealerHand = updatedDealerHand;
                updatedDealerHand = new Card[updatedDealerHand.length + 1];

                for (int k = 0; k < (updatedDealerHand.length - 1); k++) {
                        updatedDealerHand[k] = previousDealerHand[k];
                }

                updatedDealerHand[updatedDealerHand.length - 1] = drawCard(gameDeck);
                gameDeck = new Deck(gameDeck.updateDeck());

                System.out.println("\n\nDealer hand consists of: ");   
                for (int x = 0; x < updatedDealerHand.length; x++) {
                        System.out.print(updatedDealerHand[x].outputCard() + ", ");
                }

                System.out.println("\nDealer point total is: " + checkPointTotal(updatedDealerHand));

                if (checkPointTotal(updatedDealerHand) >= 17) {
                    break;
                }

            }

            if (checkPointTotal(updatedDealerHand) == 21) {
                System.out.println("\nDealer has 21.  You Lose");
                return;
            } else if (checkPointTotal(updatedDealerHand) > 21) {
                System.out.println("\nDealer busts.  You Win");
                return;
            }

            if (checkPointTotal(updatedPlayerHand) > checkPointTotal(updatedDealerHand)) {
                System.out.println("\nYou win");
            } else if (checkPointTotal(updatedPlayerHand) < checkPointTotal(updatedDealerHand)) {
                System.out.println("\nYou lose");
            } else {
                System.out.println("Draw");
            }

        } else {
            System.out.println("You have blackjack.  You Win");
        }

    }

    //when the player wants to draw a card, return a Card when called
    public static Card drawCard (Deck gameDeck) {
        return gameDeck.dealCard();
    }

    //update the deck after a card has been drawn
    public static Card[] updatedDeck(Deck gameDeck) {
        return gameDeck.updateDeck();
    }

    //check how many points the player or dealer has
    public static int checkPointTotal(Card[] cardsPossessed) {
    	int currentPointTotal = 0;
    	for (int cardIndex = 0; cardIndex < cardsPossessed.length; cardIndex++) {
    		currentPointTotal = currentPointTotal + cardsPossessed[cardIndex].getIntValue();
    	}
    	return currentPointTotal;
    }

    //check if the player wants to hit or stay
    public static char checkPlayerAction() {
        Scanner input = new Scanner( System.in );
        System.out.println("\nWould you like to hit? Enter Y or N");
        char playerAction = input.next().charAt(0);
        return playerAction;
    }

}