import spock.lang.Specification

class PrepareForNewRoundTest extends Specification{
    def "prepareForNewRound test 1"(){
        given:
        Table table = new Table("Liberato", 100, 2)
        table.getPlayer().getHand().addCard(new Card("%", "9"))
        table.getPlayer().getHand().addCard(new Card("*", "K"))
        table.getPlayer().getHand().setBet(10)
        table.getPlayer().setMoney(40);
        table.getPlayer().setSplitHand(new Hand())
        table.getPlayer().getSplitHand().addCard(new Card("*", "K"))
        table.getPlayer().getSplitHand().addCard(new Card("*", "A"))
        table.getPlayer().getSplitHand().setBet(10)
        table.getDealer().getHand().addCard(new Card("%", "Q"))
        table.getDealer().getHand().addCard(new Card("*", "K"))
        when:
        table.prepareForNewRound()
        then:
        table.getPlayer().getMoney()==60
        table.getPlayer().getHand().getHand().size()==0
        table.getPlayer().getHand().getBet()==0
        table.getPlayer().getSplitHand().getHand().size()==0
        table.getPlayer().getSplitHand().getBet()==0
        table.getDealer().getHand().getHand().size()==0
    }
}
