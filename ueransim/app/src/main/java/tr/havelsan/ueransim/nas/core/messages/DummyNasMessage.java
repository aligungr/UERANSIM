package tr.havelsan.ueransim.nas.core.messages;

import tr.havelsan.ueransim.utils.OctetInputStream;

public class DummyNasMessage extends NasMessage {

    @Override
    public NasMessage decodeMessage(OctetInputStream stream) {
        throw new RuntimeException("dummy instance used");
    }
}
