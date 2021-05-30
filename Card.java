import java.util.Scanner;

public class Card {

	private String suit; //the cards suit in letter form
	private String value; //the cards value in blackjack points
	private String displayValue; //the cards value displayed
	private String displaySuit; //the cards suit in word form

	public Card(String suit, String value) {
		this.value = value;
		this.suit = suit;

		//deterine if the card isnt a number card and change the display value accordingly
		if (this.value == "11") {
			this.displayValue = "Jack";
		} else if (this.value == "12") {
			this.displayValue = "Queen";
		} else if (this.value == "13") {
			this.displayValue = "King";
		} else if (this.value == "14") {
			this.displayValue = "Ace";
		} else {
			this.displayValue = value;
		}

		//determine the cards display suit according to the letter-form suit.
		if (this.suit == "S") {
			this.displaySuit = "Spades";
		} else if (this.suit == "C") {
			this.displaySuit = "Clubs";
		} else if (this.suit == "H") {
			this.displaySuit = "Hearts";
		} else if (this.suit == "D"){
			this.displaySuit = "Diamonds";
		} else {
			this.displaySuit = "Blank";
		}

		
	}

	//return the cards display value and suit
    public String outputCard() {

    	return this.displayValue + " of " + this.displaySuit;
    }

    //get the value of the card
    public String getValue() {
    	return this.value;
    }

    //get the value of the card in blackjack points.  if the value is an ace, prompt the user if the ace should be worth 1 or 11 points.  if the value is a jack, queen, or king, give it a value of 10
    public int getIntValue() {
    	Scanner input = new Scanner( System.in );
    	if (Integer.valueOf(this.value) <= 10) {
    		return Integer.valueOf(this.value);
    	} else if ((Integer.valueOf(this.value) > 10) && (Integer.valueOf(this.value) <= 13)) {
    		return 10;
    	} else {
    		System.out.println("There is an Ace.  Do you want Ace to be worth 1 point or 11 points?");
    		Integer acePoints = input.nextInt();
    		return acePoints;
    	}
    	
    }
}