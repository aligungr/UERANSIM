package com.runsim.backend.mts;

import com.runsim.backend.Constants;
import com.runsim.backend.Utils;
import com.runsim.backend.otn.OtnElement;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class MTSAdapter {

    public static byte[] encodeAper(String schema, OtnElement value) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(Constants.MTS_HOST + "/encode?schema=" + schema))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(value.toJson()))
                .timeout(Duration.ofSeconds(3))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("MTS request failed (" + response.statusCode() + "):\n" + response.body());
        }

        JSONObject obj = (JSONObject) new JSONParser().parse(response.body());
        String dataString = (String) obj.get("data");
        return Utils.hexStringToByteArray(dataString);
    }
}
