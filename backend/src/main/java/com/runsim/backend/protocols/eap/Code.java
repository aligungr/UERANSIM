package com.runsim.backend.protocols.eap;

import com.runsim.backend.protocols.core.ProtocolEnum;

public class Code extends ProtocolEnum {

    public static final Code REQUEST
            = new Code(1, "Request");
    public static final Code RESPONSE
            = new Code(2, "Response");
    public static final Code SUCCESS
            = new Code(3, "Success");
    public static final Code FAILURE
            = new Code(4, "Failure");
    public static final Code INITIATE
            = new Code(5, "Initiate");
    public static final Code FINISH
            = new Code(6, "Finish");

    private Code(int value, String name) {
        super(value, name);
    }

    public static Code fromValue(int value) {
        return fromValueGeneric(Code.class, value);
    }
}
