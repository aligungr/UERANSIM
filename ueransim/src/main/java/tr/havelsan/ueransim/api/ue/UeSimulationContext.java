package tr.havelsan.ueransim.api.ue;

import tr.havelsan.ueransim.api.nas.NasSecurityContext;
import tr.havelsan.ueransim.api.ue.sm.SmContext;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.nas.impl.messages.RegistrationRequest;
import tr.havelsan.ueransim.structs.UeConfig;
import tr.havelsan.ueransim.structs.UeData;
import tr.havelsan.ueransim.structs.UeTimers;

public class UeSimulationContext {

    public final SimulationContext simCtx;

    public UeData ueData;
    public UeConfig ueConfig;
    public NasSecurityContext currentNsc;
    public NasSecurityContext nonCurrentNsc;
    public UeTimers ueTimers;
    public RegistrationRequest registrationRequest;
    public SmContext smCtx;

    public UeSimulationContext(SimulationContext simCtx) {
        this.simCtx = simCtx;
    }
}
