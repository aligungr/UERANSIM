package com.runsim.backend.flows;

import com.runsim.backend.BaseFlow;
import com.runsim.backend.Message;

public class TestFlow extends BaseFlow {

    public State main(Message message) {
        sendData(3, 4, 5, 6);
        return this::someInvalidDataSentAndWaitingAResponse;
    }

    private State someInvalidDataSentAndWaitingAResponse(Message message) {
        System.err.println("message received " + message.getLength() + " bytes.");
        return closeConnection();
    }
}
