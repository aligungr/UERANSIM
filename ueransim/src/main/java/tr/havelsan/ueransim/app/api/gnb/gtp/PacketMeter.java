package tr.havelsan.ueransim.app.api.gnb.gtp;

import java.util.LinkedList;

class PacketMeter {

    private static final int TRACK_LIMIT = 1000;

    private final LinkedList<Long> timeQueue;
    private final LinkedList<Integer> sizeQueue;

    private long totalSize;

    public PacketMeter() {
        this.timeQueue = new LinkedList<>();
        this.sizeQueue = new LinkedList<>();
        this.totalSize = 0;
    }

    public synchronized void notify(int packetLength) {
        if (timeQueue.size() > TRACK_LIMIT) throw new RuntimeException();
        if (timeQueue.size() == TRACK_LIMIT) {
            timeQueue.removeFirst();
            totalSize -= sizeQueue.removeFirst();
        }

        timeQueue.addLast(System.currentTimeMillis());
        sizeQueue.addLast(packetLength);
        totalSize += packetLength;
    }

    public float speed() {
        if (timeQueue.isEmpty()) return 0;

        long delta = timeQueue.peekLast() - timeQueue.peekFirst();
        if (delta == 0) return 0;
        return (float) totalSize / delta;
    }

    public String speedMbsPerSec() {
        return speed() * 8 * 1000f / 1024f / 1024f + "Mbits/sec";
    }
}
