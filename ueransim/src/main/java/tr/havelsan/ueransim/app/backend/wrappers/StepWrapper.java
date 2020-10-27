package tr.havelsan.ueransim.app.backend.wrappers;

public class StepWrapper extends Wrapper {
    public final String messageName;
    public final boolean isSuccess;

    public StepWrapper(String messageName, boolean isSuccess) {
        this.messageName = messageName;
        this.isSuccess = isSuccess;
    }
}
