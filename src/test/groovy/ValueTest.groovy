import spock.lang.Specification

class ValueTest extends Specification{
    def "hand value test 1 - Under 21"(){
        given:
        Hand hand = new Hand();
        when:
        hand.addCard(new Card("%", "3"))
        then:
        hand.getValue() == 3;
    }
    def "hand value test 2 - Over 21"(){
        given:
        Hand hand = new Hand();
        when:
        hand.addCard(new Card("%", "#"))
        hand.addCard(new Card("%", "8"))
        hand.addCard(new Card("%", "5"))
        then:
        hand.getValue() == 23;
    }
    def "hand value test 3 - Blackjack"(){
        given:
        Hand hand = new Hand();
        when:
        hand.addCard(new Card("%", "#"))
        hand.addCard(new Card("%", "A"))
        then:
        hand.getValue() == 21;
    }
    def "hand value test 4 - Ace as 11"(){
        given:
        Hand hand = new Hand();
        when:
        hand.addCard(new Card("%", "9"))
        hand.addCard(new Card("%", "A"))
        then:
        hand.getValue() == 20;
    }
    def "hand value test 5 - Ace as 1"() {
        given:
        Hand hand = new Hand();
        when:
        hand.addCard(new Card("%", "9"))
        hand.addCard(new Card("%", "A"))
        hand.addCard(new Card("%", "4"))
        then:
        hand.getValue() == 14;
    }
}
