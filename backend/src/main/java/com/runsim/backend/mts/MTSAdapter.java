package com.runsim.backend.mts;

import com.google.gson.JsonParser;
import com.runsim.backend.Constants;
import com.runsim.backend.utils.Utils;
import com.runsim.backend.otn.OtnElement;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class MTSAdapter {

    public static byte[] encodeAper(String schema, OtnElement value) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(Constants.MTS_HOST + "/encode?schema=" + URLEncoder.encode(schema, StandardCharsets.UTF_8)))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(value.toString()))
                .timeout(Duration.ofSeconds(3))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("MTS request failed (" + response.statusCode() + "):\n" + response.body());
        }

        var obj = JsonParser.parseString(response.body()).getAsJsonObject();
        String dataString = obj.get("data").getAsString();
        return Utils.hexStringToByteArray(dataString);
    }
}
