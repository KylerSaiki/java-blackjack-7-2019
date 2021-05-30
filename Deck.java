import java.util.Random;

public class Deck {

    Card[] deckCards;
    int amountOfCards = 52;

    //create a deck and see if it should be shuffled.
	public Deck(Boolean shuffled) {
        //create array of 52 cards (in card order)
        Card[] orderedDeck = new Card[52];
        int amountOfCards = 52;
        String[] suits = {"S","C","H","D"};
        String[] values = {"2","3","4","5","6","7","8","9","10","11","12","13","14"};

        //create an array of Cards by looping through the suits array and assigning it values from 2 to 14
        int deckIndex = 0;
        for (int s = 0; s < suits.length; s++) {
            for (int v = 0; v < values.length; v++) {
                orderedDeck[deckIndex] = new Card(suits[s], values[v]);
                deckIndex++;
            }
        }

        deckCards = orderedDeck;

        //if the deck is shuffled, switch around random cards in the deck a large amount of times to shuffle it
        if (shuffled == true) {
            Random ran = new Random();
        
            int[] cardIndexes = new int[52];
            Card[] shuffledDeck = new Card[52];

            for(int i = 0; i < 52; i++) {
                cardIndexes[i] = i;
            }

            for(int t = 0; t < 500; t++) {
                int initialValue = ran.nextInt(52);
                int selectedValue = ran.nextInt(52);

                int tempValue = cardIndexes[initialValue];
                cardIndexes[initialValue] = cardIndexes[selectedValue];
                cardIndexes[selectedValue] = tempValue;
            
            }

            for (int x = 0; x < 52; x++) {
                shuffledDeck[x] = orderedDeck[cardIndexes[x]]; 
            }

            deckCards = shuffledDeck;
        }

        amountOfCards = 52;
	}

    //create a deck object of an array of cards inputted
    public Deck(Card[] inputCards) {
        deckCards = inputCards;
        amountOfCards = inputCards.length;
    }

    //deal the card at index 0 of the array
    public Card dealCard() {        
        return this.deckCards[0];
    }

    //after a card is drawn, the deck will remove the first card in the array and create a new array of cards without it
    public Card[] updateDeck() {
        this.amountOfCards = this.amountOfCards - 1;
        Card[] updatedDeck = new Card[amountOfCards];

        for (int y = 0; y < amountOfCards; y++) {
            updatedDeck[y] = this.deckCards[y + 1];
        }
        return updatedDeck;
    }
}







