package tr.havelsan.ueransim.sctp;

public interface ISCTPClient {
    void start() throws Exception;

    void send(int streamNumber, byte[] data);

    void receiverLoop(ISCTPHandler handler) throws Exception;

    void close();

    void abortReceiver();

    boolean isOpen();
}
