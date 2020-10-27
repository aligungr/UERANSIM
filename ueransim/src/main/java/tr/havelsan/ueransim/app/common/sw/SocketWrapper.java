package tr.havelsan.ueransim.app.common.sw;

public abstract class SocketWrapper {
    public final String type;

    public SocketWrapper() {
        this.type = this.getClass().getSimpleName();
    }
}
