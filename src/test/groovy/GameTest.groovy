import spock.lang.IgnoreRest
import spock.lang.Specification

class GameTest extends Specification {
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
    def "hand value test 5 - Ace as 1"(){
        given:
        Hand hand = new Hand();
        when:
        hand.addCard(new Card("%", "9"))
        hand.addCard(new Card("%", "A"))
        hand.addCard(new Card("%", "4"))
        then:
        hand.getValue() == 14;
    }
    def "hit test 1 - Less than 21"(){
        given:
        Player player = new Player("Liberato", 100);
        player.getHand().addCard(new Card("H", "3"))
        player.getHand().addCard(new Card("H", "T"))
        when:
        player.hit(new Deck(2), player.getHand());
        then:
        player.getHand().getHand().size() == 3;
    }
    def "hit test 2 - More than 21"(){
        given:
        def mockGUI = Mock(GUI);
        Player player = new Player("Liberato", 100);
        player.getHand().addCard(new Card("H", "T"))
        player.getHand().addCard(new Card("H", "8"))
        player.getHand().addCard(new Card("H", "4"))
        when:
        player.processKey(mockGUI, new Dealer(),new Deck(2), 'a' as char, player.getHand());
        then:
        player.getHand().getHand().size() == 3;
    }
    def "hit test 3 - 21"(){
        given:
        def mockGUI = Mock(GUI);
        Player player = new Player("Liberato", 100);
        player.getHand().addCard(new Card("H", "J"))
        player.getHand().addCard(new Card("H", "A"))
        when:
        player.processKey(mockGUI, new Dealer(),new Deck(2), 'a' as char, player.getHand());
        then:
        player.getHand().getHand().size() == 2;
    }
    def "stand test"(){
        given:
        def mockGUI = Mock(GUI);
        Player player = new Player("Liberato", 100);
        player.getHand().addCard(new Card("H", "3"))
        player.getHand().addCard(new Card("H", "A"))
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
        player.getHand().addCard(new Card("H", "3"))
        player.getHand().addCard(new Card("H", "A"))
        boolean inTurn;
        when:
        inTurn = player.processKey(mockGUI, new Dealer(),new Deck(2), 'd' as char, player.getHand());
        then:
        player.getHand().getHand().size() == 2;
        inTurn;
    }
    def "double down test 2 - Different Suits"(){
        given:
        def mockGUI = Mock(GUI);
        Player player = new Player("Liberato", 100);
        player.getHand().addCard(new Card("H", "6"))
        player.getHand().addCard(new Card("S", "6"))
        boolean inTurn;
        when:
        inTurn = player.processKey(mockGUI, new Dealer(),new Deck(2), 'd' as char, player.getHand());
        then:
        player.getHand().getHand().size() == 3;
        !inTurn;
    }
    def "double down test 3 - 3 cards"(){
        given:
        def mockGUI = Mock(GUI);
        Player player = new Player("Liberato", 100);
        player.getHand().addCard(new Card("H", "6"))
        player.getHand().addCard(new Card("S", "6"))
        player.getHand().addCard(new Card("S", "6"))
        boolean inTurn;
        when:
        inTurn = player.processKey(mockGUI, new Dealer(),new Deck(2), 'd' as char, player.getHand());
        then:
        player.getHand().getHand().size() == 3;
        inTurn;
    }
    def "double down test 4 - Blackjack"(){
        given:
        def mockGUI = Mock(GUI);
        Player player = new Player("Liberato", 100);
        player.getHand().addCard(new Card("H", "Q"))
        player.getHand().addCard(new Card("S", "A"))
        boolean inTurn;
        when:
        inTurn = player.processKey(mockGUI, new Dealer(),new Deck(2), 'd' as char, player.getHand());
        then:
        player.getHand().getHand().size() == 2;
        inTurn;
    }
    def "split test 1"(){
        given:
        def mockGUI = Mock(GUI);
        Player player = new Player("Liberato", 100);
        Card one = new Card("Test1", "Q");
        Card two = new Card("Test2", "Q");
        player.getHand().addCard(one)
        player.getHand().addCard(two)
        boolean inTurn;
        when:
        inTurn = player.processKey(mockGUI, new Dealer(),new Deck(2), 'w' as char, player.getHand());
        then:
        player.getHand().getHand().get(1) == one;
        player.getHand().getHand().get(2) != two;
        player.getSplitHand().getHand().get(1) == two;
        inTurn;
    }
    def "calculateWhoWon test 1 - player won"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("H", "8"))
        table.getPlayer().getHand().addCard(new Card("S", "A"))
        table.getDealer().getHand().addCard(new Card("H", "8"))
        table.getDealer().getHand().addCard(new Card("S", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand(), 0)
        then:
        result == 2;
    }
    def "calculateWhoWon test 2 - dealer won"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("H", "8"))
        table.getPlayer().getHand().addCard(new Card("S", "A"))
        table.getDealer().getHand().addCard(new Card("H", "Q"))
        table.getDealer().getHand().addCard(new Card("S", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand(), 0)
        then:
        result == 0;
    }
    def "calculateWhoWon test 3 - tie"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("H", "8"))
        table.getPlayer().getHand().addCard(new Card("S", "A"))
        table.getDealer().getHand().addCard(new Card("H", "9"))
        table.getDealer().getHand().addCard(new Card("S", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand(), 0)
        then:
        result == 1;
    }
    def "calculateWhoWon test 4 - player blackjack"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("H", "K"))
        table.getPlayer().getHand().addCard(new Card("S", "A"))
        table.getDealer().getHand().addCard(new Card("H", "Q"))
        table.getDealer().getHand().addCard(new Card("S", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand(), 0)
        then:
        result == 2.5;
    }
    def "calculateWhoWon test 5 - dealer blackjack"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("H", "9"))
        table.getPlayer().getHand().addCard(new Card("S", "A"))
        table.getDealer().getHand().addCard(new Card("H", "A"))
        table.getDealer().getHand().addCard(new Card("S", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand(), 0)
        then:
        result == -0.5;
    }
    def "calculateWhoWon test 6 - both blackjack"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("H", "Q"))
        table.getPlayer().getHand().addCard(new Card("S", "A"))
        table.getDealer().getHand().addCard(new Card("H", "A"))
        table.getDealer().getHand().addCard(new Card("S", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand(), 0)
        then:
        result == 1;
    }
    def "calculateWhoWon test 7 - player bust"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("H", "9"))
        table.getPlayer().getHand().addCard(new Card("S", "K"))
        table.getPlayer().getHand().addCard(new Card("S", "5"))
        table.getDealer().getHand().addCard(new Card("H", "A"))
        table.getDealer().getHand().addCard(new Card("S", "6"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand(), 0)
        then:
        result == 0;
    }
    def "calculateWhoWon test 8 - player bust, dealer blackjack"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("H", "9"))
        table.getPlayer().getHand().addCard(new Card("S", "K"))
        table.getPlayer().getHand().addCard(new Card("S", "5"))
        table.getDealer().getHand().addCard(new Card("H", "A"))
        table.getDealer().getHand().addCard(new Card("S", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand(), 0)
        then:
        result == -0.5;
    }
    def "calculateWhoWon test 9 - dealer bust"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("H", "9"))
        table.getPlayer().getHand().addCard(new Card("S", "K"))
        table.getDealer().getHand().addCard(new Card("H", "8"))
        table.getDealer().getHand().addCard(new Card("S", "6"))
        table.getDealer().getHand().addCard(new Card("S", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand(), 0)
        then:
        result == 2;
    }
    def "calculateWhoWon test 10 - split"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("H", "9"))
        table.getPlayer().getHand().addCard(new Card("S", "K"))
        table.getPlayer().getHand().addCard(new Card("S", "K"))
        table.getPlayer().setSplitHand(new Hand());
        table.getPlayer().getSplitHand().addCard(new Card("S", "K"))
        table.getPlayer().getSplitHand().addCard(new Card("S", "9"))
        table.getDealer().getHand().addCard(new Card("H", "8"))
        table.getDealer().getHand().addCard(new Card("S", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand(), 2)
        then:
        result == 2;
    }
    def "calculateWhoWon test 11 - split blackjack"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("H", "9"))
        table.getPlayer().getHand().addCard(new Card("S", "K"))
        table.getPlayer().getHand().addCard(new Card("S", "K"))
        table.getPlayer().setSplitHand(new Hand());
        table.getPlayer().getSplitHand().addCard(new Card("S", "K"))
        table.getPlayer().getSplitHand().addCard(new Card("S", "A"))
        table.getDealer().getHand().addCard(new Card("H", "8"))
        table.getDealer().getHand().addCard(new Card("S", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand(), 2)
        then:
        result == 2;
    }
}