/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.configs;

import tr.havelsan.ueransim.app.common.Supi;
import tr.havelsan.ueransim.app.ue.nas.mm.MmKeyManagement;
import tr.havelsan.ueransim.nas.impl.ies.IEDnn;
import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class UeConfig {
    public final boolean emulationMode;

    public final OctetString key;
    public final OctetString op;
    public final OctetString amf;
    public final String imei;
    public final Supi supi;
    public final VPlmn plmn;

    public final IESNssai[] requestedNssai;
    public final IEDnn dnn;

    public final String snn; // Auto constructed

    public UeConfig(OctetString key, OctetString op, OctetString amf, String imei, Supi supi, VPlmn plmn,
                    IESNssai[] requestedNssai, IEDnn dnn) {
        this.emulationMode = true; // TODO: make configurable later
        this.key = key;
        this.op = op;
        this.amf = amf;
        this.imei = imei;
        this.supi = supi;
        this.plmn = plmn;
        this.requestedNssai = requestedNssai;
        this.dnn = dnn;

        this.snn = MmKeyManagement.constructServingNetworkName(plmn);
    }

    public UeConfig(String key, String op, String amf, String imei, String supi, VPlmn plmn,
                    IESNssai[] requestedNssai, String dnn) {
        this(new OctetString(key), new OctetString(op), new OctetString(amf), imei,
                Supi.parse(supi), plmn, requestedNssai, new IEDnn(dnn));
    }
}
