package com.runsim.backend;

public class AppClient {
    public static void main(String[] args) throws Exception {
        SCTPClient client = new SCTPClient();
        client.send("::1", 3457, 60, 1, new byte[]{65, 65, 65});
    }
}
