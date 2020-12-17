package tr.havelsan.ueransim.app.link.rlc.pdu;

import tr.havelsan.ueransim.app.link.rlc.utils.ESegmentInfo;

public interface IRxPdu {
    int getSn();

    int getSo();

    int getSize();

    boolean isProcessed();

    ESegmentInfo getSi();
}
