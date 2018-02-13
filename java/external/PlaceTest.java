package external;

import static org.junit.Assert.*;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import base.Start;
import base.TerminalMock;
import edu.kit.informatik.Terminal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * PlaceTest
 *
 * @author Valentin Wagner
 *         09.02.18
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Terminal.class)
class PlaceTest {

    private TerminalMock terminalMock;

    public PlaceTest() {
    }

    @Before
    public void init() {
        this.terminalMock = new TerminalMock();
    }


    @Test
    public void testArgsParsing() throws Exception {
        terminalMock.mock()
                .willReturn("place ") //missing args
                .willReturn("quit");

        Start.game("standard", "18", "3");

        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("place -1;2;5;2") //-pos on standart
                .willReturn("quit");

        Start.game("standard", "18", "3");

        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("place 1;2;5;2 ") //space at the end
                .willReturn("quit");

        Start.game("standard", "18", "3");

        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("place 1;1;1;1") //same pos for both
                .willReturn("quit");

        Start.game("standard", "18", "3");

        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("place -1;2;5;2") //-pos on torus
                .willReturn("quit");

        Start.game("torus", "18", "3");

        assertThat(terminalMock.isError()).isFalse();

    }

    @Test
    public void testPlaceWithOneWrongDoesntPlaceAnything() throws Exception{
        terminalMock.mock()
                .willReturn("place 0;0;1;0")
                .willReturn("place 5;0;1;0")
                .willReturn("print")
                .willReturn("quit");

        Start.game("standard", "18", "2");

        assertThat(terminalMock.isError()).isTrue();
        assertThat(terminalMock.getCaptor().getValue())
                .isEqualTo(
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
                                "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                                "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                                "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                                "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                                "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **"
                );
    }


}