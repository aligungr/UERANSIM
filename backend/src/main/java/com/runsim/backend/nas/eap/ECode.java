package com.runsim.backend.nas.eap;

import com.runsim.backend.nas.core.ProtocolEnum;

public class ECode extends ProtocolEnum {

    public static final ECode REQUEST
            = new ECode(1, "Request");
    public static final ECode RESPONSE
            = new ECode(2, "Response");
    public static final ECode SUCCESS
            = new ECode(3, "Success");
    public static final ECode FAILURE
            = new ECode(4, "Failure");
    public static final ECode INITIATE
            = new ECode(5, "Initiate");
    public static final ECode FINISH
            = new ECode(6, "Finish");

    private ECode(int value, String name) {
        super(value, name);
    }

    public static ECode fromValue(int value) {
        return fromValueGeneric(ECode.class, value);
    }
}
