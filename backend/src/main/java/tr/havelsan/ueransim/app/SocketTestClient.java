package tr.havelsan.ueransim.app;

import com.google.gson.*;
import tr.havelsan.ueransim.web.SessionManager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SocketTestClient {

    private static final UUID connectionId = UUID.randomUUID();

    public static void main(String[] args) throws Exception {
        connect();

        sendMessage("hello", new HashMap<>() {{
            put("arg0", 3);
        }});
    }

    private static void connect() {
        SessionManager.onConnect(connectionId, System.out::println);
    }

    private static void sendMessage(String command, HashMap<String, Object> args) throws Exception {
        JsonObject params = new JsonObject();
        for (Map.Entry<String, Object> entry : args.entrySet()) {
            JsonElement value = JsonParser.parseString(new Gson().toJson(entry.getValue()));
            params.add(entry.getKey(), value);
        }

        JsonObject message = new JsonObject();
        message.add("cmd", new JsonPrimitive(command));
        message.add("args", params);

        SessionManager.onMessage(connectionId, message.toString());
    }
}
