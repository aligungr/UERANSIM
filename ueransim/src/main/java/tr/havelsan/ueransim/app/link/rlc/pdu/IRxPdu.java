package tr.havelsan.ueransim.app.link.rlc.pdu;

public interface IRxPdu {
    int getSn();

    int getSo();

    int getSize();

    boolean isProcessed();
}
