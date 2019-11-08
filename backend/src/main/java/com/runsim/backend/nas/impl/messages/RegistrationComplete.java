package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.IESorTransparentContainer;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class RegistrationComplete extends PlainNasMessage {

    /* Optional */
    public IESorTransparentContainer sorTransparentContainer;

    @Override
    public RegistrationComplete decodeMessage(OctetInputStream stream) {
        var res = new RegistrationComplete();

        while (stream.hasNext()) {
            int iei = stream.readOctetI();
            switch (iei) {
                case 0x73:
                    res.sorTransparentContainer = NasDecoder.ie2346(stream, false, IESorTransparentContainer.class);
                    break;
            }
        }

        return res;
    }

    @Override
    public void encodeMessage(OctetOutputStream stream) {
        super.encodeMessage(stream);
        if (sorTransparentContainer != null) {
            NasEncoder.ie2346(stream, 0x73, sorTransparentContainer);
        }
    }
}
