package tr.havelsan.ueransim.parameterised;

public class EapAkaInputs {
    public final String OP;
    public final String SQN;
    public final String AMF;
    public final String KEY;

    public EapAkaInputs(String OP, String SQN, String AMF, String KEY) {
        this.OP = OP;
        this.SQN = SQN;
        this.AMF = AMF;
        this.KEY = KEY;
    }
}
