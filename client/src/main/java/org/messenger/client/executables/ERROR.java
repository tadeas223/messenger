package org.messenger.client.executables;

import org.messenger.protocol.protocolHandling.Executable;
import org.messenger.protocol.protocolHandling.ExecutionBundle;

import java.io.IOException;

/**
 * This executable handles any errors thrown by the server.
 */
public class ERROR implements Executable {
    @Override
    public void execute(ExecutionBundle executionBundle) throws IOException {
        // Do nothing for now
    }
}
