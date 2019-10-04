package com.runsim.backend.demo;

import com.runsim.backend.Constants;

public class AppServer {
    public static void main(String[] args) throws Exception {
        SCTPTestServer server = new SCTPTestServer();
        server.run(Constants.AMF_PORT);
    }
}
