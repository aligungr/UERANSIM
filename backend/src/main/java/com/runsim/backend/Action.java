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

    public static Action switchState(MessageContext msgCtx, String nextState) {
        return new SwitchState(msgCtx, nextState);
    }

    public static class NoOperation extends Action {
    }

    public static class CloseConnection extends Action {
    }

    public static class SendData extends Action {
        public final byte[] data;
        public final String nextState;
        public final int streamNumber;

        public SendData(byte[] data, String nextState) {
            this(data, nextState, Constants.DEFAULT_STREAM_NUMBER);
        }

        public SendData(byte[] data, String nextState, int streamNumber) {
            this.data = data;
            this.nextState = nextState;
            this.streamNumber = streamNumber;
        }
    }

    public static class SendElement extends Action {
        public final String schema;
        public final OtnElement element;
        public final String nextState;
        public final int streamNumber;

        public SendElement(String schema, OtnElement element, String nextState) {
            this(schema, element, nextState, Constants.DEFAULT_STREAM_NUMBER);
        }

        public SendElement(String schema, OtnElement element, String nextState, int streamNumber) {
            this.schema = schema;
            this.element = element;
            this.nextState = nextState;
            this.streamNumber = streamNumber;
        }
    }

    public static class SwitchState extends Action {
        public final MessageContext msgCtx;
        public final String nextState;

        public SwitchState(MessageContext msgCtx, String nextState) {
            this.msgCtx = msgCtx;
            this.nextState = nextState;
        }
    }
}
