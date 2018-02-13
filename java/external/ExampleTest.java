package external;

import static org.junit.Assert.*;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import base.Start;
import base.TerminalMock;
import com.google.common.truth.Correspondence;
import edu.kit.informatik.Terminal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.annotation.Nullable;
import java.util.List;

/**
 * ExampleTest
 *
 * @author Valentin Wagner
 *         12.02.18
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Terminal.class)
class ExampleTest {

    private TerminalMock terminalMock;

    public ExampleTest() {
    }

    @Before
    public void init() {
        this.terminalMock = new TerminalMock();
    }


    @Test
    public void testExampleFromThePDF() throws Exception {
        terminalMock.mock()
                .willReturn("place 6;3;6;8")
                .willReturn("place 3;2;1;7")
                .willReturn("place 6;4;6;7")
                .willReturn("reset")
                .willReturn("place 6;3;6;8")
                .willReturn("place 3;2;1;7")
                .willReturn("place 6;4;6;7")
                .willReturn("place 6;9;6;2")
                .willReturn("place 6;5;6;6")
                .willReturn("rowprint 6")
                .willReturn("quit");

        String[] outputs = new String[]{
                "OK", "OK", "OK", "OK",
                "OK", "OK", "OK", "OK",
                "P1 wins",
                "** ** P2 P1 P1 P1 P1 P1 P1 P2 ** ** ** ** ** ** ** **"
        };

        Start.game("standard", "18", "2");

        List<String> actualOutput = terminalMock.getCaptor().getAllValues();
        for(int i = 0; i < actualOutput.size(); i++){
            assertThat(actualOutput.get(i)).isEqualTo(outputs[i]);
        }

        assertThat(terminalMock.isError()).isFalse();    }


}