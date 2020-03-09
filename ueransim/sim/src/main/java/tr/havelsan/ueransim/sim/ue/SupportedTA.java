package tr.havelsan.ueransim.sim.ue;

import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.utils.octets.Octet3;

public class SupportedTA {
    public final Octet3 tac;
    public final BroadcastPlmn[] broadcastPlmns;

    public SupportedTA(Octet3 tac, BroadcastPlmn[] broadcastPlmns) {
        this.tac = tac;
        this.broadcastPlmns = broadcastPlmns;
    }

    public static class BroadcastPlmn {
        public final VPlmn plmn;
        public final IESNssai[] taiSliceSupportNssais;

        public BroadcastPlmn(VPlmn plmn, IESNssai[] taiSliceSupportNssais) {
            this.plmn = plmn;
            this.taiSliceSupportNssais = taiSliceSupportNssais;
        }
    }
}
