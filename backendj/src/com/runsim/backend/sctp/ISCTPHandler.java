package com.runsim.backend.sctp;

import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.SctpChannel;

public interface ISCTPHandler {
    void handleSCTPMessage(byte[] receivedBytes, MessageInfo messageInfo, SctpChannel channel) throws Exception;
}
