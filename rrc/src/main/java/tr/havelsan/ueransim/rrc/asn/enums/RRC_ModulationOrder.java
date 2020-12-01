/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_ModulationOrder extends RRC_Enumerated {

    public static final RRC_ModulationOrder BPSK_HALFPI = new RRC_ModulationOrder("bpsk-halfpi");
    public static final RRC_ModulationOrder BPSK = new RRC_ModulationOrder("bpsk");
    public static final RRC_ModulationOrder QPSK = new RRC_ModulationOrder("qpsk");
    public static final RRC_ModulationOrder QAM16 = new RRC_ModulationOrder("qam16");
    public static final RRC_ModulationOrder QAM64 = new RRC_ModulationOrder("qam64");
    public static final RRC_ModulationOrder QAM256 = new RRC_ModulationOrder("qam256");

    protected RRC_ModulationOrder(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "ModulationOrder";
    }

    @Override
    public String getXmlTagName() {
        return "ModulationOrder";
    }

}
