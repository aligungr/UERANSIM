/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CSI_ReportConfig__reportConfigType__aperiodic;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CSI_ReportConfig__reportConfigType__periodic;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CSI_ReportConfig__reportConfigType__semiPersistentOnPUCCH;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CSI_ReportConfig__reportConfigType__semiPersistentOnPUSCH;

public class RRC_CSI_ReportConfig__reportConfigType extends RRC_Choice {

    public RRC_CSI_ReportConfig__reportConfigType__periodic periodic;
    public RRC_CSI_ReportConfig__reportConfigType__semiPersistentOnPUCCH semiPersistentOnPUCCH;
    public RRC_CSI_ReportConfig__reportConfigType__semiPersistentOnPUSCH semiPersistentOnPUSCH;
    public RRC_CSI_ReportConfig__reportConfigType__aperiodic aperiodic;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "periodic","semiPersistentOnPUCCH","semiPersistentOnPUSCH","aperiodic" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "periodic","semiPersistentOnPUCCH","semiPersistentOnPUSCH","aperiodic" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
