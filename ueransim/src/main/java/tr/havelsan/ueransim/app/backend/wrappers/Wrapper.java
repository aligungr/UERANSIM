package tr.havelsan.ueransim.app.backend.wrappers;

public abstract class Wrapper {
    public final String type;

    public Wrapper() {
        this.type = this.getClass().getSimpleName();
    }
}
