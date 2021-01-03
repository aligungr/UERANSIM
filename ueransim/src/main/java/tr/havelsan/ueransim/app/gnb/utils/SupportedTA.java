/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.gnb.utils;

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
        public final Nssai[] taiSliceSupportNssais;

        public BroadcastPlmn(VPlmn plmn, Nssai[] taiSliceSupportNssais) {
            this.plmn = plmn;
            this.taiSliceSupportNssais = taiSliceSupportNssais;
        }
    }
}
