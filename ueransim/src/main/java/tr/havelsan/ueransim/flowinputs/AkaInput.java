package tr.havelsan.ueransim.flowinputs;

import tr.havelsan.ueransim.utils.octets.OctetString;

public class AkaInput {
    public final String SSN;
    public final OctetString KEY;
    public final OctetString OP;
    public final OctetString SQN;
    public final OctetString AMF;

    public AkaInput(String SSN, OctetString KEY, OctetString OP, OctetString SQN, OctetString AMF) {
        this.SSN = SSN;
        this.KEY = KEY;
        this.OP = OP;
        this.SQN = SQN;
        this.AMF = AMF;
    }
}
