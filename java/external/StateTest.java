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
 * StateTest
 *
 * @author Valentin Wagner
 *         12.02.18
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Terminal.class)
class StateTest {

    private TerminalMock terminalMock;

    public StateTest() {
    }

    @Before
    public void init() {
        this.terminalMock = new TerminalMock();
    }


    @Test
    public void testStandartError() throws Exception {
        terminalMock.mock()
                .willReturn("state")
                .willReturn("quit");

        Start.game("standard", "18", "2");
        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("state -1;2")
                .willReturn("quit");

        Start.game("standard", "18", "2");
        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("state 1;2 ")
                .willReturn("quit");

        Start.game("standard", "18", "2");
        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("state 1;18")
                .willReturn("quit");

        Start.game("standard", "18", "2");
        assertThat(terminalMock.isError()).isTrue();
    }

    @Test
    public void testTorusError() throws Exception {
        terminalMock.mock()
                .willReturn("state")
                .willReturn("quit");

        Start.game("torus", "18", "2");
        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("state 1;2 ")
                .willReturn("quit");

        Start.game("torus", "18", "2");
        assertThat(terminalMock.isError()).isTrue();
    }

    @Test
    public void testStandartEmpty() throws Exception {
        terminalMock.mock()
                .willReturn("state 0;0")
                .willReturn("quit");

        Start.game("standard", "18", "2");
        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("**");
    }


    @Test
    public void testTorusEmpty() throws Exception {
        terminalMock.mock()
                .willReturn("state -1;-1")
                .willReturn("quit");

        Start.game("torus", "18", "2");
        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("**");
    }


    @Test
    public void testStandartWithPlacement() throws Exception {
        terminalMock.mock()
                .willReturn("place 0;0;1;1")
                .willReturn("state 0;0")
                .willReturn("quit");

        Start.game("standard", "18", "2");
        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("P1");
    }

    @Test
    public void testTorusWithPlacement() throws Exception {
        terminalMock.mock()
                .willReturn("place -1;-1;1;1")
                .willReturn("state -1;-1")
                .willReturn("quit");

        Start.game("torus", "18", "2");
        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("P1");
    }
}