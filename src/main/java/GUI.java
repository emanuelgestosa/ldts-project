import java.io.IOException;

public interface GUI {

    void drawMenu() throws IOException;

    void drawTable() throws IOException;

    void refresh() throws IOException;

    void clear();

    void close() throws IOException;
}
