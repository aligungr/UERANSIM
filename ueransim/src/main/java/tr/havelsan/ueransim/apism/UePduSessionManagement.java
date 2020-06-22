package tr.havelsan.ueransim.apism;

import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionIdentity;

public class UePduSessionManagement {

    public static EPduSessionIdentity allocatePduSessionId(SimulationContext ctx) {
        return EPduSessionIdentity.VAL_8;
    }
}
