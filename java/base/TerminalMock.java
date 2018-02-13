package base;

import edu.kit.informatik.Terminal;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;

/**
 * Created by wagnva on 09.02.18.
 */
public class TerminalMock {

    private ArgumentCaptor<String> captor;

    private boolean errorOccurred = false;

    public TerminalMock(){
        this.captor = ArgumentCaptor.forClass(String.class);
    }

    public BDDMockito.BDDMyOngoingStubbing<String> mock() throws Exception{
        PowerMockito.mockStatic(Terminal.class);

        PowerMockito.doNothing().when(Terminal.class, "printLine", captor.capture());
        PowerMockito.when(Terminal.class, "printError", Mockito.anyString())
                .then((invocation -> {
                    errorOccurred = true;
                    return null;
                }));
        return BDDMockito.given(Terminal.readLine());
    }

    public ArgumentCaptor<String> getCaptor(){
        return this.captor;
    }

    public boolean isError(){
        if(errorOccurred){
            errorOccurred = false;
            return true;
        }
        return false;
    }

}
