import spock.lang.Specification

class CalculateWhoWonTest extends Specification{
    def "calculateWhoWon test 1 - player won"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("%", "8"))
        table.getPlayer().getHand().addCard(new Card("*", "A"))
        table.getDealer().getHand().addCard(new Card("%", "8"))
        table.getDealer().getHand().addCard(new Card("*", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand())
        then:
        result == 2;
    }
    def "calculateWhoWon test 2 - dealer won"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("%", "8"))
        table.getPlayer().getHand().addCard(new Card("*", "A"))
        table.getDealer().getHand().addCard(new Card("%", "Q"))
        table.getDealer().getHand().addCard(new Card("*", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand())
        then:
        result == 0;
    }
    def "calculateWhoWon test 3 - tie"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("%", "8"))
        table.getPlayer().getHand().addCard(new Card("*", "A"))
        table.getDealer().getHand().addCard(new Card("%", "9"))
        table.getDealer().getHand().addCard(new Card("*", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand())
        then:
        result == 1;
    }
    def "calculateWhoWon test 4 - player blackjack"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("%", "K"))
        table.getPlayer().getHand().addCard(new Card("*", "A"))
        table.getDealer().getHand().addCard(new Card("%", "Q"))
        table.getDealer().getHand().addCard(new Card("*", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand())
        then:
        result == 2.5f;
    }
    def "calculateWhoWon test 5 - dealer blackjack"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("%", "9"))
        table.getPlayer().getHand().addCard(new Card("*", "A"))
        table.getDealer().getHand().addCard(new Card("%", "A"))
        table.getDealer().getHand().addCard(new Card("*", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand())
        then:
        result == 0;
    }
    def "calculateWhoWon test 6 - both blackjack"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("%", "Q"))
        table.getPlayer().getHand().addCard(new Card("*", "A"))
        table.getDealer().getHand().addCard(new Card("%", "A"))
        table.getDealer().getHand().addCard(new Card("*", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand())
        then:
        result == 1;
    }
    def "calculateWhoWon test 7 - player bust"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("%", "9"))
        table.getPlayer().getHand().addCard(new Card("*", "K"))
        table.getPlayer().getHand().addCard(new Card("%", "5"))
        table.getDealer().getHand().addCard(new Card("*", "A"))
        table.getDealer().getHand().addCard(new Card("%", "6"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand())
        then:
        result == 0;
    }
    def "calculateWhoWon test 8 - player bust, dealer blackjack"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("%", "9"))
        table.getPlayer().getHand().addCard(new Card("*", "K"))
        table.getPlayer().getHand().addCard(new Card("*", "5"))
        table.getDealer().getHand().addCard(new Card("%", "A"))
        table.getDealer().getHand().addCard(new Card("*", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand())
        then:
        result == 0;
    }
    def "calculateWhoWon test 9 - dealer bust"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("%", "9"))
        table.getPlayer().getHand().addCard(new Card("*", "K"))
        table.getDealer().getHand().addCard(new Card("%", "8"))
        table.getDealer().getHand().addCard(new Card("*", "6"))
        table.getDealer().getHand().addCard(new Card("*", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand())
        then:
        result == 2;
    }
    def "calculateWhoWon test 10 - split"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("%", "9"))
        table.getPlayer().getHand().addCard(new Card("*", "K"))
        table.getPlayer().getHand().addCard(new Card("*", "K"))
        table.getPlayer().setSplitHand(new Hand());
        table.getPlayer().getSplitHand().addCard(new Card("*", "K"))
        table.getPlayer().getSplitHand().addCard(new Card("*", "9"))
        table.getDealer().getHand().addCard(new Card("%", "8"))
        table.getDealer().getHand().addCard(new Card("*", "K"))
        float result;
        when:
        result = table.calculateWhoWon(table.getPlayer().getHand())
        then:
        result == 0;
        when:
        result = table.calculateWhoWon(table.getPlayer().getSplitHand())
        then:
        result == 2;
    }
    def "calculateWhoWon test 11 - split blackjack"(){
        given:
        Table table = new Table("Liberato", 100, 2);
        table.getPlayer().getHand().addCard(new Card("%", "9"))
        table.getPlayer().getHand().addCard(new Card("*", "K"))
        table.getPlayer().getHand().addCard(new Card("*", "K"))
        table.getPlayer().setSplitHand(new Hand());
        table.getPlayer().getSplitHand().addCard(new Card("*", "K"))
        table.getPlayer().getSplitHand().addCard(new Card("*", "A"))
        table.getDealer().getHand().addCard(new Card("%", "8"))
        table.getDealer().getHand().addCard(new Card("*", "K"))
        float result1;
        float result2;
        when:
        result1 = table.calculateWhoWon(table.getPlayer().getHand())
        result2 = table.calculateWhoWon(table.getPlayer().getSplitHand())
        then:
        result1+result2 == 2;
    }
}
