package tr.havelsan.ueransim.app.ue.app;

class PingEntry {
    public final long timestamp;
    public final String name;
    public final String address;

    public PingEntry(long timestamp, String name, String address) {
        this.timestamp = timestamp;
        this.name = name;
        this.address = address;
    }
}
