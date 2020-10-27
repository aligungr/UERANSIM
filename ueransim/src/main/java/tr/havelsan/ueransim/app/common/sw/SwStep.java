package tr.havelsan.ueransim.app.common.sw;

public class SwStep extends SocketWrapper {
    public final String messageName;
    public final boolean isSuccess;

    public SwStep(String messageName, boolean isSuccess) {
        this.messageName = messageName;
        this.isSuccess = isSuccess;
    }
}
