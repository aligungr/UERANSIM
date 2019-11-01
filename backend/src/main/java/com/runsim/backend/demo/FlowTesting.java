package com.runsim.backend.demo;

import com.runsim.backend.protocols.nas.NASDecoder;
import com.runsim.backend.utils.Utils;

public class FlowTesting {

    public static void main(String[] args) throws Exception {
        var test = "7E004171000D010011000000000000000000F12E0480808080";
        var data = Utils.hexStringToByteArray(test);
        var pdu = new NASDecoder(data).decodeNAS();

        System.out.println("-");

        /*var flow = new RegistrationFlow();
        flow.start();*/
    }
}
