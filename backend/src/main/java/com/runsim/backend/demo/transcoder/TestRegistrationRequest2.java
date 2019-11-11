package com.runsim.backend.demo.transcoder;

import com.runsim.backend.demo.TranscoderTesting;
import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.messages.NasMessage;

public class TestRegistrationRequest2 extends TranscoderTesting.PduTest {

    @Override
    public String getPdu() {
        return "7e004171000d010011000000000099898877f71001002e04804080402f020101";
    }

    @Override
    public void compare(NasMessage message) {
        throw new NotImplementedException("");
    }

    @Override
    public NasMessage getMessage() {
        throw new NotImplementedException("");
    }
}
