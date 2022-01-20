package blackjack.gui;


import blackjack.model.Position;

import java.io.IOException;

public interface GUI {

    ACTION getNextAction() throws IOException;

    void drawText(Position position, String text, String color);

    void refresh() throws IOException;

    void clear();

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT};
}
