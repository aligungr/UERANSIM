package com.runsim.backend;

public abstract class Action {

    public static Action noOperation() {
        return new NoOperation();
    }

    public static Action closeConnection() {
        return new CloseConnection();
    }

    public static Action sendData(byte[] data, String nextState) {
        return new SendData(data, nextState);
    }

    public static class NoOperation extends Action { }

    public static class CloseConnection extends Action { }

    public static class SendData extends Action {
        private final byte[] data;
        private final String nextState;
        private final int streamNumber;

        public SendData(byte[] data, String nextState) {
            this(data, nextState, Constants.DEFAULT_STREAM_NUMBER);
        }

        public SendData(byte[] data, String nextState, int streamNumber) {
            this.data = data;
            this.nextState = nextState;
            this.streamNumber = streamNumber;
        }

        public byte[] getData() {
            return data;
        }

        public String getNextState() {
            return nextState;
        }

        public int getStreamNumber() {
            return streamNumber;
        }
    }
}
