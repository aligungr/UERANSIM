package com.runsim.backend.demo;

import com.runsim.backend.*;
import com.runsim.backend.annotations.Starter;
import com.runsim.backend.annotations.State;
import com.runsim.backend.otn.*;

public class AppClient {

    public static class TestMachine extends StateMachine {

        @Starter
        public Action starter(MachineContext machineContext) {

            OtnElement obj = new OtnObject()
                    .put("criticality", "ignore")
                    .put("procedure-number", 23);

            return Action.sendElement("DSSADDFS" ,obj, "someState");

            //return Action.sendElement("NGAP_CommonDataTypes.Criticality", new OtnString("ignore"), "someState");
            //return Action.sendData(Utils.hexStringToByteArray("00000304000600000000000031390800450200701be24000408493caac10191dac10191e960c960c6bdd3441000000000003004f67417f1e000010e10000003c000f403b00000400550002000000790013503253120000000010325312100000e1416bd2005a00011800260012117e0041f1000bf23253120e0201b11fb11f00"), "someState");


        }

        @State
        public Action someState(MessageContext messageContext, MachineContext machineContext) {
            return Action.closeConnection();
        }
    }

    public static void main(String[] args) throws Exception {
        OtnElement parsed = OtnXmlParser.parseXml("<object>\n" +
                "    <array name=\"protocolIEs\">\n" +
                "        <object>\n" +
                "            <number name=\"id\">85</number>\n" +
                "            <string name=\"criticality\">reject</string>\n" +
                "            <tuple name=\"value\">\n" +
                "                <string>RAN-UE-NGAP-ID</string>\n" +
                "                <number>5</number>\n" +
                "            </tuple>\n" +
                "        </object>\n" +
                "            <number name=\"id\">38</number>\n" +
                "            <string name=\"criticality\">reject</string>\n" +
                "            <tuple name=\"value\">\n" +
                "                <string>UserLocationInformation</string>\n" +
                "                <base16>7e004171000d010011000000000099898877f71001002e04804080402f020101</base16>\n" +
                "            </tuple>\n" +
                "        <object>\n" +
                "            <number name=\"id\">121</number>\n" +
                "            <string name=\"criticality\">reject</string>\n" +
                "            <tuple name=\"value\">\n" +
                "                <string>UserLocationInformation</string>\n" +
                "                <tuple>\n" +
                "                    <string>userLocationInformationNR</string>\n" +
                "                    <object>\n" +
                "                        <object name=\"nR-CGI\">\n" +
                "                            <base16 name=\"pLMNIdentity\">000110</base16>\n" +
                "                            <tuple name=\"nRCellIdentity\">\n" +
                "                                <number>69632</number>\n" +
                "                                <number>36</number>\n" +
                "                            </tuple>\n" +
                "                        </object>\n" +
                "                        <object name=\"tAI\">\n" +
                "                            <base16 name=\"pLMNIdentity\">000110</base16>\n" +
                "                            <base16 name=\"tAC\">000075</base16>\n" +
                "                        </object>\n" +
                "                    </object>\n" +
                "                </tuple>\n" +
                "            </tuple>\n" +
                "        </object>\n" +
                "        <object>\n" +
                "            <number name=\"id\">90</number>\n" +
                "            <string name=\"criticality\">ignore</string>\n" +
                "            <tuple name=\"value\">\n" +
                "                <string>RRCEstablishmentCause</string>\n" +
                "                <string>mo-Signalling</string>\n" +
                "            </tuple>\n" +
                "        </object>\n" +
                "    </array>\n" +
                "</object>");

        System.out.println(parsed);

        if (1 == 1)
            return;

        MachineController controller = new MachineController(TestMachine.class, Constants.AMF_HOST, Constants.AMF_PORT);
        controller.run();
    }
}
