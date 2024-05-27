package org.server.execution.executables;

import org.protocol.InstructionBuilder;
import org.protocol.protocolHandling.Executable;
import org.protocol.protocolHandling.ExecutionBundle;
import org.server.ServerConnectionHandler;
import org.server.socketData.ArrayReading;

import java.io.IOException;

public class DONE implements Executable {
    @Override
    public void execute(ExecutionBundle executionBundle) throws IOException {
        ServerConnectionHandler handler = (ServerConnectionHandler) executionBundle.connectionHandler;

        if(!handler.containsData(ArrayReading.class)){
            executionBundle.connection.writeInstruction(InstructionBuilder.error("Array Reading not initialized"));
            return;
        }

        handler.removeData(ArrayReading.class);

        executionBundle.connection.writeInstruction(InstructionBuilder.done());

    }
}
