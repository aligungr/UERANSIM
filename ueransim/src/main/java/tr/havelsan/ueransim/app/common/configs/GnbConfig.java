/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.configs;

import tr.havelsan.ueransim.app.gnb.utils.Nssai;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_PagingDRX;
import tr.havelsan.ueransim.utils.bits.BitString;

public class GnbConfig {
    public final int gnbId;
    public final int tac;
    public final BitString nci;
    public final VPlmn plmn;
    public final GnbAmfConfig[] amfConfigs;
    public final boolean ignoreStreamIds;
    public final String ngapIp;
    public final String gtpIp;
    public final Nssai[] nssais;
    public final NGAP_PagingDRX pagingDrx = NGAP_PagingDRX.V64; // TODO config

    public GnbConfig(int gnbId, int tac, String nci, VPlmn plmn, GnbAmfConfig[] amfConfigs,
                     boolean ignoreStreamIds, String ngapIp, String gtpIp, Nssai[] nssais) {
        this.gnbId = gnbId;
        this.tac = tac;
        this.nci = BitString.fromBits(BitString.fromHex(nci).toBinaryString().substring(0, 36)); // TODO: refactor
        this.plmn = plmn;
        this.amfConfigs = amfConfigs;
        this.ignoreStreamIds = ignoreStreamIds;
        this.ngapIp = ngapIp;
        this.gtpIp = gtpIp;
        this.nssais = nssais;
    }
}
