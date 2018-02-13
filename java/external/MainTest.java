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
 * Tests the Main class
 * @author Valentin Wagner
 * 09.02.2018
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Terminal.class)
class MainTest {

    private TerminalMock terminalMock;

    public MainTest(){}

    @Before
    public void init(){
        this.terminalMock = new TerminalMock();
    }

    @Test
    public void main() throws Exception {

        terminalMock.mock().willReturn("quit");

        Start.game("standard", "18", "3"); //standard correct
        assertThat(terminalMock.isError()).isFalse();

        Start.game("torus", "20", "4");//torus correct
        assertThat(terminalMock.isError()).isFalse();

        Start.game("standard2", "18", "3"); //wrong game mode
        assertThat(terminalMock.isError()).isTrue();

        Start.game("standard", "19", "3"); //wrong board size
        assertThat(terminalMock.isError()).isTrue();

        Start.game("standard", "18", "5"); //too many players
        assertThat(terminalMock.isError()).isTrue();

        Start.game(null, null, null); //no args at all
        assertThat(terminalMock.isError()).isTrue();

        Start.game(new String[]{"standard", "18", "4", "ago"}); //too many args
        assertThat(terminalMock.isError()).isTrue();
    }



}