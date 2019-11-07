package com.runsim.backend.nas.core.messages;

import com.runsim.backend.utils.octets.Octet;
import com.runsim.backend.utils.octets.Octet4;

public abstract class SecurityProtectedNasMessage extends NasMessage {
    public Octet4 messageAuthenticationCode;
    public Octet sequenceNumber;
    public PlainNasMessage plainNasMessage;
}
