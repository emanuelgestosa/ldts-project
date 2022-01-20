import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LanternaGUI implements GUI{

    private final Screen screen;
    private final Terminal terminal;

    public LanternaGUI(int width, int height) throws IOException, URISyntaxException, FontFormatException {
        URL resource = getClass().getClassLoader().getResource("DealerplateCalifornia-Regular10.ttf");
        File fontFile = new File(resource.toURI());
        Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        Font loadedFont = font.deriveFont(Font.PLAIN,45);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        terminal = createTerminal(width, height, fontConfig);
        screen = createScreen(terminal);
    }

    public LanternaGUI(Screen screen, Terminal terminal) {
        this.terminal = terminal;
        this.screen = screen;
    }

    public Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize).setForceAWTOverSwing(true).setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        ((AWTTerminalFrame)terminal).addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
            }
        });
        return terminal;
    }

    public Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen;
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    @Override
    public KeyStroke getKey() throws IOException {
        return screen.readInput();
    }

    @Override
    public void drawMenu() throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        drawBackground(graphics);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        drawCenter(graphics,"( S )tart", 2);
        drawCenter(graphics,"( C )onfigs", 0);
        drawCenter(graphics,"( Q )uit", -2);
    }

    private void drawBackground(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#2d8c17"));
        graphics.fillRectangle(new TerminalPosition(0, 0), terminal.getTerminalSize(), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#43C824"));
        graphics.fill('.');
    }

    private void drawCenter(TextGraphics graphics, String str, int incrementToHeight)throws IOException{
        var terminalSize = terminal.getTerminalSize();
        graphics.putString((terminalSize.getColumns()-str.length()-1)/2, (terminalSize.getRows()/2)-incrementToHeight, str);
    }

    @Override
    public void drawConfigs() throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        drawBackground(graphics);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        drawCenter(graphics, "(D)eck Number", 1);
        drawCenter(graphics, "(Q)uit", -1);
    }

    @Override
    public void drawAlterDecks(int nDecks) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        drawBackground(graphics);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        drawCenter(graphics, "Please enter a valid(between 1 and 10) number of Decks:", 0);
        drawCenter(graphics, Integer.toString(nDecks), -2);
    }

    @Override
    public void drawTable(int money, int phase) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        drawBackground(graphics);
        graphics.setBackgroundColor(TextColor.Factory.fromString("#a2b536"));
        graphics.fillRectangle(new TerminalPosition(0, terminal.getTerminalSize().getRows() - 4), new TerminalSize(terminal.getTerminalSize().getColumns(), 4), ' ');
        graphics.setBackgroundColor(TextColor.Factory.fromString("#753216"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#9E4A27"));
        graphics.fillRectangle(new TerminalPosition(20, terminal.getTerminalSize().getRows() - 4), new TerminalSize(2, 4), ' ');
        graphics.fillRectangle(new TerminalPosition(0, terminal.getTerminalSize().getRows() - 4), new TerminalSize(terminal.getTerminalSize().getColumns(), 1), '=');
        drawBalance(money);
        if(phase ==0)
            drawAskForBet();
        else
            drawOptions();
    }

    private void drawAskForBet() throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#a2b536"));
        graphics.putString(23, terminal.getTerminalSize().getRows() - 2, "Please type in a valid bet:");
    }

    @Override
    public void drawBet(int bet) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#a2b536"));
        graphics.putString(51, terminal.getTerminalSize().getRows() - 2,"      ");
        graphics.putString(51, terminal.getTerminalSize().getRows() - 2, Integer.toString(bet));
    }

    private void drawBalance(int money) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#a2b536"));
        graphics.putString(1, terminal.getTerminalSize().getRows() - 2,"Balance: " + money) ;
    }

    private void drawOptions() throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#a2b536"));
        graphics.putString(23, terminal.getTerminalSize().getRows() - 2, "Hit(a) Stand(s) DoubleDown(d) Split(w)");
    }

    @Override
    public void drawHand(CardHolder holder, int split) throws IOException {
        Hand hand = holder.getHand();
        if (holder.getClass().equals(Player.class)) drawPlayerHand(hand, split);
        else drawDealerHand(hand);
    }

    private void drawDealerHand(Hand hand) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#2d8c17"));
        List<Card> cards = new ArrayList<Card>(hand.getCards());
        if (cards.size() < 2) cards.add(new Card("?", "?"));
        int drawColumn = (terminal.getTerminalSize().getColumns() - cards.size() * 3) / 2;
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (Objects.equals(card.getSuit(), "&") || Objects.equals(card.getSuit(), "%")) graphics.setForegroundColor(TextColor.Factory.fromString("#fc1111"));
            else graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
            graphics.putString(drawColumn + i*3, 1, card.getSymbol() + card.getSuit());
        }
    }

    private void drawPlayerHand(Hand hand, int split) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#2d8c17"));
        List<Card> cards = hand.getCards();
        int fac=2;
        if (split != 0)
            fac = 4;
        int drawColumn = (terminal.getTerminalSize().getColumns() - cards.size() * 3) / fac;
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (Objects.equals(card.getSuit(), "&") || Objects.equals(card.getSuit(), "%")) graphics.setForegroundColor(TextColor.Factory.fromString("#fc1111"));
            else graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
            graphics.putString(drawColumn + i*3, terminal.getTerminalSize().getRows() - 6, card.getSymbol() + card.getSuit());
        }
    }

    @Override
    public void drawSplitHand(Hand hand) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#2d8c17"));
        List<Card> cards = hand.getCards();
        int drawColumn = (3* (terminal.getTerminalSize().getColumns() - cards.size() * 3) / 4) - 2;
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (Objects.equals(card.getSuit(), "&") || Objects.equals(card.getSuit(), "%")) graphics.setForegroundColor(TextColor.Factory.fromString("#fc1111"));
            else graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
            graphics.putString(drawColumn + i*3, terminal.getTerminalSize().getRows() - 6, card.getSymbol() + card.getSuit());
        }
    }

    @Override
    public void drawChip(Player player, int split) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#2d8c17"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#E7E938"));
        if(split ==0)
            graphics.putString(40, 5, Integer.toString(player.getHand().getBet()));
        else{
            graphics.putString(20, 5, Integer.toString(player.getHand().getBet()));
            graphics.putString(50, 5, Integer.toString(player.getSplitHand().getBet()));
        }
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
