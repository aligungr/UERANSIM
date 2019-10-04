package com.runsim.backend;

import com.runsim.backend.otn.OtnElement;

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

    public static Action sendElement(String schema, OtnElement element, String nextState) {
        return new SendElement(schema, element, nextState);
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

    public static class SendElement extends Action {
        private final String schema;
        private final OtnElement element;
        private final String nextState;
        private final int streamNumber;

        public SendElement(String schema, OtnElement element, String nextState) {
            this(schema, element, nextState, Constants.DEFAULT_STREAM_NUMBER);
        }

        public SendElement(String schema, OtnElement element, String nextState, int streamNumber) {
            this.schema = schema;
            this.element = element;
            this.nextState = nextState;
            this.streamNumber = streamNumber;
        }

        public String getSchema() {
            return schema;
        }

        public OtnElement getElement() {
            return element;
        }

        public String getNextState() {
            return nextState;
        }

        public int getStreamNumber() {
            return streamNumber;
        }
    }
}
