package com.runsim.backend;

public class AppServer {
    public static void main(String[] args) throws Exception {
        SCTPTestServer server = new SCTPTestServer();
        server.start(3457);
    }
}
