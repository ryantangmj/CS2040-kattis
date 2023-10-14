import java.io.*;
import java.util.*;

class Cardtrading {
    public static void main(String[] args) throws IOException {
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        String[] lineDetails = inp.readLine().split(" ");

        int numCards = Integer.parseInt(lineDetails[0]);
        int typeCards = Integer.parseInt(lineDetails[1]);
        int combos = Integer.parseInt(lineDetails[2]);

        int[] cardsOnHand = new int[typeCards];
        String[] eachCard = inp.readLine().split(" ");
        for (int i = 0; i < numCards; i++) {
            int cardNum = Integer.parseInt(eachCard[i]); 
            cardsOnHand[cardNum - 1] = cardsOnHand[cardNum - 1] + 1;
        }

        Card[] cards = new Card[typeCards];
        for (int i = 0; i < typeCards; i++) {
            String[] details = inp.readLine().split(" ");
            cards[i] = new Card(cardsOnHand[i], Integer.parseInt(details[0]), Integer.parseInt(details[1]));
        }

        List<Card> sortedCards = Arrays.asList(cards);
        Collections.sort(sortedCards, new CardComparator());

        long totalSpent = 0;
        for (int i = 0; i < combos; i++) {
            totalSpent -= (2 - sortedCards.get(i).getCount()) * sortedCards.get(i).getBuyPrice();
        } 

        for (int i = combos; i < typeCards; i++) {
            totalSpent += sortedCards.get(i).getCount() * sortedCards.get(i).getSellPrice();
        }

        System.out.println(totalSpent);
    }
}

class Card {
    private final int count;
    private final int buyPrice;
    private final int sellPrice;
    private final int value;

    public Card(int count, int buyPrice, int sellPrice) {
        this.count = count;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.value = (2 - count) * buyPrice + count * sellPrice;
    }

    public int getCount() {
        return count;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getValue() {
        return value;
    }
}


class CardComparator implements Comparator<Card> {
    public int compare(Card c1, Card c2) { 
       return c1.getValue() - c2.getValue() != 0 ? c1.getValue() - c2.getValue() : c1.getBuyPrice() - c2.getBuyPrice();
    }
}