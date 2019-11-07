package com.runsim.backend.demo.transcoder;

import com.runsim.backend.demo.TranscoderTesting;
import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.messages.NasMessage;

public class TestAuthenticationResult extends TranscoderTesting.PduTest {

    @Override
    public String getPdu() {
        return "7e005a00000403010004020000";
    }

    @Override
    public void compare(NasMessage message) {
        throw new NotImplementedException("this test not implemented yet");
    }

    @Override
    public NasMessage getMessage() {
        throw new NotImplementedException("");
    }
}
