package org.messenger.server.socketData;

import org.messenger.connection.socketData.SocketData;

public class InitCallData implements SocketData {
    private boolean initCalled;

    public boolean isInitCalled() {
        return initCalled;
    }

    public void setInitCalled(boolean initCalled) {
        this.initCalled = initCalled;
    }

    public InitCallData(boolean initCalled) {
        this.initCalled = initCalled;
    }
}
