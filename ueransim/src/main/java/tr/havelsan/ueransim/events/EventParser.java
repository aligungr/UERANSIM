package tr.havelsan.ueransim.events;

public class EventParser {

    public static SimulationEvent parse(String command) {
        if (command == null) {
            return null;
        }
        if (command.equals("ngsetup")) {
            return new GnbEvent(command);
        }
        return null;
    }
}
