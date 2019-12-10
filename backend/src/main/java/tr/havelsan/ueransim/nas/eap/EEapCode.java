package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class EEapCode extends ProtocolEnum {

    public static final EEapCode REQUEST
            = new EEapCode(1, "Request");
    public static final EEapCode RESPONSE
            = new EEapCode(2, "Response");
    public static final EEapCode SUCCESS
            = new EEapCode(3, "Success");
    public static final EEapCode FAILURE
            = new EEapCode(4, "Failure");
    public static final EEapCode INITIATE
            = new EEapCode(5, "Initiate");
    public static final EEapCode FINISH
            = new EEapCode(6, "Finish");

    private EEapCode(int value, String name) {
        super(value, name);
    }

    public static EEapCode fromValue(int value) {
        return fromValueGeneric(EEapCode.class, value, null);
    }
}
