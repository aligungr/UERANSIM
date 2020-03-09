package tr.havelsan.ueransim.inputs;

public class EapAkaInput {
    public final String OP;
    public final String SQN;
    public final String AMF;
    public final String KEY;

    public EapAkaInput(String OP, String SQN, String AMF, String KEY) {
        this.OP = OP;
        this.SQN = SQN;
        this.AMF = AMF;
        this.KEY = KEY;
    }
}
