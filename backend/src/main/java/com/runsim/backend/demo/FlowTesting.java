package com.runsim.backend.demo;

import com.runsim.backend.protocols.nas.NASDecoder;
import com.runsim.backend.utils.Utils;

public class FlowTesting {

    public static void main(String[] args) throws Exception {
        var test = "7e00560002000078006c0101006c3201000001050000c1c855df1555ab38342f5e5242e286b202050000adca1f49a09c8000167c4316a3a016d1180100011709002035473a6d6e633030312e6d63633030312e336770706e6574776f726b2e6f72670b0500005addcf552b22f2909f7dde0050e22cbd";
        var data = Utils.hexStringToByteArray(test);
        var pdu = new NASDecoder(data).decodeNAS();

        /*var flow = new RegistrationFlow();
        flow.start();*/
    }
}
