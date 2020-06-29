package tr.havelsan.ueransim.events;

public class GnbCommandEvent extends GnbEvent {
    public final String cmd;

    public GnbCommandEvent(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "GnbCommandEvent{" +
                "cmd='" + cmd + '\'' +
                '}';
    }
}
