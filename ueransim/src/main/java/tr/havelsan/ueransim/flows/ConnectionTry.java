package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.Message;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

public class ConnectionTry extends BaseFlow {
    @Override
    public State main(Message message) throws Exception {
        Console.println(Color.GREEN_BRIGHT, "connected");
        Console.println(Color.WHITE_BRIGHT, "closing connection");
        return closeConnection();
    }
}
