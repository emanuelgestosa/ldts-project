import spock.lang.Specification

class GameTest extends Specification {
    def "hit test 1 - Less than 21"(){
        given:
        Player player = new Player("Liberato", 100);
        player.getHand().addCard(new Card("%", "3"))
        player.getHand().addCard(new Card("%", "#"))
        when:
        player.hit(new Deck(2), player.getHand());
        then:
        player.getHand().getHand().size() == 3;
    }
    def "hit test 2 - More than 21"(){
        given:
        def mockGUI = Mock(GUI);
        Player player = new Player("Liberato", 100);
        player.getHand().addCard(new Card("%", "#"))
        player.getHand().addCard(new Card("%", "8"))
        player.getHand().addCard(new Card("%", "4"))
        when:
        player.processKey(mockGUI, new Dealer(),new Deck(2), 'a' as char, player.getHand());
        then:
        player.getHand().getHand().size() == 3;
    }
    def "hit test 3 - 21"(){
        given:
        def mockGUI = Mock(GUI);
        Player player = new Player("Liberato", 100);
        player.getHand().addCard(new Card("%", "J"))
        player.getHand().addCard(new Card("%", "A"))
        when:
        player.processKey(mockGUI, new Dealer(),new Deck(2), 'a' as char, player.getHand());
        then:
        player.getHand().getHand().size() == 2;
    }
    def "stand test"(){
        given:
        def mockGUI = Mock(GUI);
        Player player = new Player("Liberato", 100);
        player.getHand().addCard(new Card("%", "3"))
        player.getHand().addCard(new Card("%", "A"))
        boolean inTurn;
        when:
        inTurn = player.processKey(mockGUI, new Dealer(),new Deck(2), 's' as char, player.getHand());
        then:
        player.getHand().getHand().size() == 2;
        !inTurn;
    }
    def "double down test 1 - Different Symbols"(){
        given:
        def mockGUI = Mock(GUI);
        Player player = new Player("Liberato", 100);
        player.getHand().addCard(new Card("%", "3"))
        player.getHand().addCard(new Card("%", "A"))
        player.getHand().setBet(10);
        boolean inTurn;
        when:
        inTurn = player.processKey(mockGUI, new Dealer(),new Deck(2), 'd' as char, player.getHand());
        then:
        player.getHand().getHand().size() == 3;
        player.getHand().getBet()==20;
        !inTurn;
    }
    def "double down test 2 - Different Suits"(){
        given:
        def mockGUI = Mock(GUI);
        Player player = new Player("Liberato", 100);
        player.getHand().addCard(new Card("%", "6"))
        player.getHand().addCard(new Card("*", "6"))
        player.getHand().setBet(10);
        boolean inTurn;
        when:
        inTurn = player.processKey(mockGUI, new Dealer(),new Deck(2), 'd' as char, player.getHand());
        then:
        player.getHand().getHand().size() == 3;
        player.getHand().getBet()==20;
        !inTurn;
    }
    def "double down test 3 - 3 cards"(){
        given:
        def mockGUI = Mock(GUI);
        Player player = new Player("Liberato", 100)
        player.getHand().addCard(new Card("%", "6"))
        player.getHand().addCard(new Card("*", "6"))
        player.getHand().addCard(new Card("*", "6"))
        player.getHand().setBet(10)
        boolean inTurn
        when:
        inTurn = player.processKey(mockGUI, new Dealer(),new Deck(2), 'd' as char, player.getHand())
        then:
        player.getHand().getHand().size() == 3
        player.getHand().getBet()==10
        inTurn
    }
    def "double down test 4 - Blackjack"(){
        given:
        def mockGUI = Mock(GUI)
        Player player = new Player("Liberato", 100)
        player.getHand().addCard(new Card("%", "Q"))
        player.getHand().addCard(new Card("*", "A"))
        player.getHand().setBet(10)
        boolean inTurn
        when:
        inTurn = player.processKey(mockGUI, new Dealer(),new Deck(2), 'd' as char, player.getHand())
        then:
        player.getHand().getHand().size() == 2
        player.getHand().getBet()==10
        inTurn
    }
}