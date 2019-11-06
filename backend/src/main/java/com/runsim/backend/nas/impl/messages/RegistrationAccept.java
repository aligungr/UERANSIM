package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.Decoder;
import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.IE5gsRegistrationResult;
import com.runsim.backend.utils.OctetInputStream;

public class RegistrationAccept extends PlainNasMessage {

    public IE5gsRegistrationResult registrationResult;

    @Override
    public RegistrationAccept decodeMessage(OctetInputStream stream) {
        var resp = new RegistrationAccept();
        resp.registrationResult = Decoder.ie4(stream, false, IE5gsRegistrationResult.class);

        // todo, diğer fieldlar
        throw new NotImplementedException("RegistrationAccept NOT İMPLEMENTED YET");
    }
}
