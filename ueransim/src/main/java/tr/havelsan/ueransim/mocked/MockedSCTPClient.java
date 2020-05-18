package tr.havelsan.ueransim.mocked;

import tr.havelsan.ueransim.sctp.ISCTPClient;
import tr.havelsan.ueransim.sctp.ISCTPHandler;

import java.util.ArrayDeque;
import java.util.Queue;

public class MockedSCTPClient implements ISCTPClient {
    private final Queue<Byte[]> queue;
    private final IMockedRemote mockedRemote;

    private boolean receiving;
    private boolean isOpen;

    public MockedSCTPClient(IMockedRemote mockedRemote) {
        this.queue = new ArrayDeque<>();
        this.mockedRemote = mockedRemote;

        this.receiving = false;
        this.isOpen = false;
    }

    @Override
    public void start() throws Exception {
        isOpen = true;
    }

    @Override
    public void send(int streamNumber, byte[] data) {
        mockedRemote.onMessage(data, queue);
    }

    @Override
    public void receiverLoop(ISCTPHandler handler) throws Exception {
        receiving = true;

        while (receiving && isOpen) {
            while (true) {
                var entry = queue.poll();
                if (entry == null) break;

                byte[] response = new byte[entry.length];
                for (int i = 0; i < response.length; i++) {
                    response[i] = entry[i];
                }
                handler.handleSCTPMessage(response, null, null);
            }
        }
    }

    @Override
    public void close() {
        isOpen = false;
    }

    @Override
    public void abortReceiver() {
        receiving = false;
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    public interface IMockedRemote {
        void onMessage(byte[] data, Queue<Byte[]> queue);
    }
}
