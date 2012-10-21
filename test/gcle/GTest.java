package gcle;

import gcle.position.GPosition;
import java.util.ArrayList;
import main.Fen;
import main.Ui;
import static org.junit.Assert.*;
import org.junit.Test;

public final class GTest {
    private GPosition position;
    @Test
    public void testInit() {
        System.out.println("Test par comparaison avec chesspresso");
        position = GPosition.getInstance();
        //position.init(fen_initiale);
        //System.out.println(position);
        String[] command = new String[3];
        command[0] = "-cli";
        command[1] = "F:/ProgmEchecsNotes/shirov.pgn";//814 parties
//        command[1] = "F:/ProgmEchecsNotes/paulsen.pgn";//322
//        command[1] = "F:/ProgmEchecsNotes/ashley.pgn";//414
//          command[1] = "F:/ProgmEchecsNotes/bird.pgn";//353 
//        command[1] = "F:/ProgmEchecsNotes/Tartakower.pgn";//1290
//            command[1] = "F:/ProgmEchecsNotes/Capablanca.pgn";//597
//        command[1] = "F:/ProgmEchecsNotes/Boleslavsky.pgn";//651
//        command[1] = "F:/ProgmEchecsNotes/Soltis.pgn";//370
//        command[1] = "F:/ProgmEchecsNotes/Motylev.pgn";//1169
        Ui.main(command);
        String result = "";
//        cp_position = position.getCPosition();
        boolean bdiff;

        for (String f : Fen.getFenList()) {
            position.init(f);
            ArrayList<String> diff = getTest().getDiffStringList();
            bdiff = diff.isEmpty();
            assertTrue(bdiff);
            if (!bdiff) {
                result = position.getFen()
                        + '\n' + "Coups ChessPresso:" + "\n"
                        + position.getCPosition().toStringListCPCoups()
                        + '\n' + "Coups GCLE:" + "\n" + position.toStringListGCoups()
                        + "\n" + "Diff:" + "\n" + diff + "\n";
                System.out.print(result);
            }

        }
//   String fen = "8/1pR1P3/6b1/1k1p2p1/pP1r2P1/8/4KR2/8 w - - 1 57";   
//        position = GPosition.getInstance();
//        position.init(fen);
//        System.out.println(position);
    }
    private Tester getTest() {
        Tester valid = new Tester();
        ArrayList<String> lg_coups = position.toStringListGCoups();
        ArrayList<String> lcp_coups = position.getCPosition().toStringListCPCoups();
        if (lg_coups.size() <= lcp_coups.size()) {
            valid.setDiffStringList(getDiff(lg_coups, lcp_coups));
        } else {
            valid.setDiffStringList(getDiff(lcp_coups, lg_coups));
        }

        return valid;
    }
    private ArrayList<String> getDiff(ArrayList<String> L1, ArrayList<String> L2) {
        L2.removeAll(L1);
        return L2;

    }
    private class Tester {
        private ArrayList<String> diffStringList;
        protected Tester() {
            diffStringList = new ArrayList<>();
        }
        protected ArrayList<String> getDiffStringList() {
            return diffStringList;
        }
        protected void setDiffStringList(ArrayList<String> diffStringList) {
            this.diffStringList = diffStringList;
        }
    }
}
