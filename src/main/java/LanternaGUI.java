import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LanternaGUI implements GUI{

    private final Screen screen;
    private final Terminal terminal;

    public LanternaGUI(int width, int height) throws IOException {
        terminal = createTerminal(width, height);
        screen = createScreen(terminal);
    }

    public LanternaGUI(Screen screen, Terminal terminal) throws IOException {
        this.terminal = terminal;
        this.screen = screen;
    }

    public Terminal createTerminal(int width, int height) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
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
        graphics.putString(0, 0, "(S)tart");
        graphics.putString(0, 1, "(Q)uit");
        graphics.putString(0, 2, "(C)onfigs");
    }

    @Override
    public void drawConfigs() throws IOException {
        graphics.putString(0, 0, "(D)eck Number");
        graphics.putString(0, 1, "(Q)uit");
    }

    @Override
    public void drawTable(int money) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#2d8c17"));
        graphics.fillRectangle(new TerminalPosition(0, 0), terminal.getTerminalSize(), ' ');
        graphics.setBackgroundColor(TextColor.Factory.fromString("#a2b536"));
        graphics.fillRectangle(new TerminalPosition(0, terminal.getTerminalSize().getRows() - 4), new TerminalSize(terminal.getTerminalSize().getColumns(), 4), ' ');
        graphics.setBackgroundColor(TextColor.Factory.fromString("#753216"));
        graphics.fillRectangle(new TerminalPosition(0, terminal.getTerminalSize().getRows() - 4), new TerminalSize(terminal.getTerminalSize().getColumns(), 1), ' ');
        graphics.fillRectangle(new TerminalPosition(20, terminal.getTerminalSize().getRows() - 4), new TerminalSize(2, 4), ' ');
        drawBalance(money);
        drawOptions();
    }
    private void drawBalance(int money) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#a2b536"));
        graphics.putString(1, terminal.getTerminalSize().getRows() - 2,"Balance: 50") ;
    }

    private void drawOptions() throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#a2b536"));
        graphics.putString(23, terminal.getTerminalSize().getRows() - 2, "Hit(a) Stand(s) DoubleDown(d) Split(w)");

    }

    @Override
    public void drawHand(CardHolder holder) throws IOException {
        Hand hand = holder.getHand();
        if (holder.getClass().equals(Player.class)) drawPlayerHand(hand);
        else drawDealerHand(hand);
    }

    private void drawDealerHand(Hand hand) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#2d8c17"));
        List<Card> cards = new ArrayList<Card>(hand.getCards());
        if (cards.size() < 2) cards.add(new Card("#", "#"));
        int drawColumn = (terminal.getTerminalSize().getColumns() - cards.size() * 3) / 2;
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (Objects.equals(card.getSuit(), "H") || Objects.equals(card.getSuit(), "D")) graphics.setForegroundColor(TextColor.Factory.fromString("#fc1111"));
            else graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
            graphics.putString(drawColumn + i*3, 1, card.getSymbol() + card.getSuit());
        }
    }

    private void drawPlayerHand(Hand hand) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#2d8c17"));
        List<Card> cards = hand.getCards();
        int drawColumn = (terminal.getTerminalSize().getColumns() - cards.size() * 3) / 2;
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (Objects.equals(card.getSuit(), "H") || Objects.equals(card.getSuit(), "D")) graphics.setForegroundColor(TextColor.Factory.fromString("#fc1111"));
            else graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
            graphics.putString(drawColumn + i*3, terminal.getTerminalSize().getRows() - 6, card.getSymbol() + card.getSuit());
        }}

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
