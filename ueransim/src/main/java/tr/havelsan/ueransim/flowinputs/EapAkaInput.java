package tr.havelsan.ueransim.flowinputs;

import tr.havelsan.ueransim.utils.octets.OctetString;

public class EapAkaInput {
    public final OctetString OP;
    public final OctetString SQN;
    public final OctetString AMF;
    public final OctetString KEY;

    public EapAkaInput(OctetString OP, OctetString SQN, OctetString AMF, OctetString KEY) {
        this.OP = OP;
        this.SQN = SQN;
        this.AMF = AMF;
        this.KEY = KEY;
    }
}
