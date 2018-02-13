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
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * PrintTest
 * @author Valentin Wagner
 *         09.02.18
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Terminal.class)
class PrintTest {

    private TerminalMock terminalMock;

    public PrintTest() {
    }

    @Before
    public void init() {
        this.terminalMock = new TerminalMock();
    }

    @Test
    public void testPrintWorks() throws Exception{
        terminalMock.mock()
                .willReturn("print") //empty board at the beginning
                .willReturn("quit");

        Start.game("torus", "18", "4");

        assertThat(terminalMock.getCaptor().getValue()).isEqualTo(
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
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **"
        );
    }

    @Test
    public void testPrintWithoutArgs() throws Exception {
        terminalMock.mock()
                .willReturn(" print") //space at the beginning
                .willReturn("quit");

        Start.game("standard", "18", "3");

        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("print ") //space at the end
                .willReturn("quit");

        Start.game("standard", "18", "3");

        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("print") //correct command
                .willReturn("quit");

        Start.game("standard", "18", "3");

        assertThat(terminalMock.isError()).isFalse();
    }


}