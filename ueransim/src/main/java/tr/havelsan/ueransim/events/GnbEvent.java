package tr.havelsan.ueransim.events;

public class GnbEvent extends SimulationEvent {
    public final String cmd;

    public GnbEvent(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "GnbEvent{" +
                "cmd='" + cmd + '\'' +
                '}';
    }
}
