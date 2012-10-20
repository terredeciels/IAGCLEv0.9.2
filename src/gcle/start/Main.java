package gcle.start;

import static gcle.ICodage.fen_initiale;
import gcle.position.GPosition;
import static gcle.position.GPosition.getInstance;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        final String fen = fen_initiale;

        final GPosition position = getInstance();
        position.init(fen);

        ArrayList<String> sCoups = position.toStringListGCoups();
        System.out.println(position.getFen() + '\n' + sCoups);
    }
}
