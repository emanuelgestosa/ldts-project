import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class LanternaGUI implements GUI{

    private Screen screen;
    private Terminal terminal;
    private TextGraphics graphics;

    public LanternaGUI() throws IOException {
        try {
            terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphics = screen.newTextGraphics();
    }

    public LanternaGUI(Screen screen) throws IOException {
        try {
            terminal = new DefaultTerminalFactory().createTerminal();
            this.screen = screen;
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphics = screen.newTextGraphics();
    }

    @Override
    public KeyStroke getKey() throws IOException {
        return screen.readInput();
    }

    @Override
    public void drawMenu() throws IOException {
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
    public void drawTable() throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#2d8c17"));
        graphics.fillRectangle(new TerminalPosition(0, 0), terminal.getTerminalSize(), ' ');
    }

    @Override
    public void drawHand(CardHolder holder) throws IOException {
        Hand hand = holder.getHand();
        int row;
        String holderString;
        if (holder.getClass().equals(Player.class)) {row = 0; holderString = "Player";}
        else {row = 1; holderString = "Dealer";}
        List<Card> cards = hand.getCards();
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffcc"));
        graphics.putString(0, row, holderString + ": ");
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (Objects.equals(card.getSuit(), "S") || Objects.equals(card.getSuit(), "C")) graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
            else graphics.setForegroundColor(TextColor.Factory.fromString("#fc1111"));
            graphics.putString(i * 3 + 8, row, card.getSymbol() + card.getSuit());
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
