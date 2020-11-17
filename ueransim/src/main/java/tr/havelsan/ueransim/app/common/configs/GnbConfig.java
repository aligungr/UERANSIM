/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.configs;

import tr.havelsan.ueransim.app.gnb.utils.SupportedTA;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.utils.bits.BitString;

public class GnbConfig {
    public final int gnbId;
    public final int tac;
    public final BitString nci;
    public final VPlmn gnbPlmn;
    public final GnbAmfConfig[] amfConfigs;
    public final SupportedTA[] supportedTAs;
    public final boolean ignoreStreamIds;
    public final String host;
    public final int gtpPort;

    public GnbConfig(int gnbId, int tac, String nci, VPlmn gnbPlmn, GnbAmfConfig[] amfConfigs, SupportedTA[] supportedTAs, boolean ignoreStreamIds, String host, int gtpPort) {
        this.gnbId = gnbId;
        this.tac = tac;
        this.nci = BitString.fromBits(nci);
        this.gnbPlmn = gnbPlmn;
        this.amfConfigs = amfConfigs;
        this.supportedTAs = supportedTAs;
        this.ignoreStreamIds = ignoreStreamIds;
        this.host = host;
        this.gtpPort = gtpPort;
    }
}
