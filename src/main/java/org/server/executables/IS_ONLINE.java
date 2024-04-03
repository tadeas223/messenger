package org.server.executables;

import org.protocol.InstructionBuilder;
import org.server.ServerConnectionHandler;
import org.protocolHandling.Executable;
import org.protocolHandling.ExecutionBundle;
import org.server.ServerExecutionBundle;

import java.io.IOException;
import java.util.ArrayList;

public class IS_ONLINE implements Executable {
    @Override
    public void execute(ExecutionBundle executionBundle) throws IOException {
        ServerExecutionBundle serverExecutionBundle = (ServerExecutionBundle) executionBundle;

        ServerConnectionHandler serverHandler = (ServerConnectionHandler) serverExecutionBundle.connectionHandler;
        if(!serverHandler.checkUser()){
            serverExecutionBundle.connection.writeInstruction(InstructionBuilder.error("User is not logged in"));
            return;
        }

        String username = serverExecutionBundle.instruction.getParam("username");

        if(username == null){
            serverExecutionBundle.connection.writeInstruction(InstructionBuilder.error("Missing argument"));
            return;
        }

        ArrayList<ServerConnectionHandler> handlers = serverHandler.getServer().getHandlers();

        for(ServerConnectionHandler handler : handlers){
            if(handler.getUser() != null){
                if(handler.getUser().getUsername().equals(username)){
                    serverExecutionBundle.connection.writeInstruction(InstructionBuilder.trueInstruction());
                    return;
                }
            }
        }

        serverExecutionBundle.connection.writeInstruction(InstructionBuilder.falseInstruction());
    }
}
