package tr.havelsan.ueransim.app.common.sw;

public class SwCommand extends SocketWrapper {
    public final String commandName;

    public SwCommand(String commandName) {
        this.commandName = commandName;
    }
}
