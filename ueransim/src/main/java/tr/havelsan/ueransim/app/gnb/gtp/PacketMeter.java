package tr.havelsan.ueransim.app.gnb.gtp;

import java.util.LinkedList;

class PacketMeter {

    private final Accumulator accumulator;
    private long thisSecond;
    private long thisTotal;
    private long lastPeek;

    public PacketMeter() {
        this.thisSecond = 0;
        this.thisTotal = 0;
        this.lastPeek = 0;
        this.accumulator = new Accumulator();
    }

    private static long getCurrentSecond() {
        return System.currentTimeMillis() / 1000L;
    }

    public synchronized void notify(int packetLength) {
        var currentSecond = getCurrentSecond();

        if (thisSecond == currentSecond) {
            thisTotal += packetLength;
        } else {
            lastPeek = thisTotal + packetLength;
            accumulator.notify(lastPeek);
            thisSecond = currentSecond;
            thisTotal = packetLength;
        }
    }

    public String speedMbsPerSec() {
        return String.format("%.2f Mbits/sec", (accumulator.getTotal() / (float) Accumulator.SIZE) * 8f / 1024f / 1024f);
    }

    private static class Accumulator {
        public static final int SIZE = 4;

        private final LinkedList<Long> data;
        private long total;

        public Accumulator() {
            this.data = new LinkedList<>();
        }

        public void notify(long v) {
            if (data.size() >= SIZE) {
                total -= data.removeFirst();
                total += v;
                data.addLast(v);
            } else {
                total += v;
                data.addLast(v);
            }
        }

        public long getTotal() {
            return total;
        }
    }
}
