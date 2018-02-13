package external;

import static org.junit.Assert.*;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import base.Start;
import base.TerminalMock;
import edu.kit.informatik.Main;
import edu.kit.informatik.Terminal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * WinTest
 *
 * @author Valentin Wagner
 *         10.02.18
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Terminal.class)
class WinTest {

    private TerminalMock terminalMock;

    public WinTest() {
    }

    @Before
    public void init() {
        this.terminalMock = new TerminalMock();
    }


    @Test
    public void testWinStandardRow() throws Exception {
        terminalMock.mock()
                .willReturn("place 0;0;0;1")
                .willReturn("place 5;4;3;2")
                .willReturn("place 0;2;0;3")
                .willReturn("place 9;7;6;4")
                .willReturn("place 0;4;0;5")
                .willReturn("quit");

        Start.game("standard", "18", "2");

        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("P1 wins");
    }

    @Test
    public void testWinTorusRow() throws Exception {
        terminalMock.mock()
                .willReturn("place 0;0;0;1")
                .willReturn("place 5;4;3;2")
                .willReturn("place 0;2;0;3")
                .willReturn("place 9;7;6;4")
                .willReturn("place 0;-1;0;-2")
                .willReturn("quit");

        Start.game("torus", "18", "2");

        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("P1 wins");
    }

    @Test
    public void testWinStandardColumn() throws Exception {
        terminalMock.mock()
                .willReturn("place 0;0;1;0")
                .willReturn("place 0;1;1;1")
                .willReturn("place 2;0;3;0")
                .willReturn("place 2;1;3;1")
                .willReturn("place 4;0;5;0")
                .willReturn("quit");

        Start.game("standard", "18", "2");

        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("P1 wins");
    }

    @Test
    public void testWinTorusColumn() throws Exception {
        terminalMock.mock()
                .willReturn("place 0;0;1;0")
                .willReturn("place 0;1;1;1")
                .willReturn("place 2;0;3;0")
                .willReturn("place 2;1;3;1")
                .willReturn("place -1;0;-2;0")
                .willReturn("quit");

        Start.game("torus", "18", "2");

        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("P1 wins");
    }

    @Test
    public void testWinStandartDiagonalLeftToRight() throws Exception {
        terminalMock.mock()
                .willReturn("place 0;0;1;1")
                .willReturn("place 5;6;4;5")
                .willReturn("place 2;2;3;3")
                .willReturn("place 8;7;6;5")
                .willReturn("place 4;4;5;5")
                .willReturn("quit");

        Start.game("standard", "18", "2");

        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("P1 wins");
    }

    @Test
    public void testWinTorusDiagonalLeftToRight() throws Exception {
        terminalMock.mock()
                .willReturn("place 0;0;1;1")
                .willReturn("place 5;6;4;5")
                .willReturn("place 2;2;3;3")
                .willReturn("place 8;7;6;5")
                .willReturn("place -1;-1;-2;-2")
                .willReturn("quit");

        Start.game("torus", "18", "2");

        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("P1 wins");
    }

    @Test
    public void testWinStandartDiagonalRightToLeft() throws Exception {
        terminalMock.mock()
                .willReturn("place 17;17;16;16")
                .willReturn("place 5;6;4;5")
                .willReturn("place 15;15;14;14")
                .willReturn("place 8;7;6;5")
                .willReturn("place 13;13;12;12")
                .willReturn("quit");

        Start.game("standard", "18", "2");

        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("P1 wins");
    }

    @Test
    public void testWinTorusDiagonalRightToLeft() throws Exception {
        terminalMock.mock()
                .willReturn("place 17;17;16;16")
                .willReturn("place 5;6;4;5")
                .willReturn("place 15;15;14;14")
                .willReturn("place 8;7;6;5")
                .willReturn("place 18;18;19;19")
                .willReturn("quit");

        Start.game("torus", "18", "2");

        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("P1 wins");
    }

    @Test
    public void testPlaceAfterWinError() throws Exception {
        terminalMock.mock()
                .willReturn("place 0;0;1;0")
                .willReturn("place 0;1;1;1")
                .willReturn("place 2;0;3;0")
                .willReturn("place 2;1;3;1")
                .willReturn("place 4;0;5;0") //p1 wins here
                .willReturn("place 4;1;5;1") //this should not be allowed
                .willReturn("print")
                .willReturn("quit");

        Start.game("standard", "18", "2");

        assertThat(terminalMock.isError()).isTrue();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo( //make sure the invalid placement is not actually placed
                "P1 P2 ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                "P1 P2 ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                "P1 P2 ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                "P1 P2 ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                "P1 ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                "P1 ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **");
    }

    @Test
    public void testDraw() throws Exception{

        int boardSize = 18;

        BDDMockito.BDDMyOngoingStubbing<String> mocking = terminalMock.mock();
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j += 2){
                mocking = mocking.willReturn("place " + i + ";" + j + ";" + i + ";" + (j + 1));
            }
        }

        mocking.willReturn("quit");

        Start.game("standard", boardSize+"", "4");

        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("draw");
    }
}