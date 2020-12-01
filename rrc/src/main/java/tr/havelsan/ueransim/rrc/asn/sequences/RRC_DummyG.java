/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_DummyG extends RRC_Sequence {

    public RRC_Integer maxNumberSSB_CSI_RS_ResourceOneTx;
    public RRC_Integer maxNumberSSB_CSI_RS_ResourceTwoTx;
    public RRC_Integer supportedCSI_RS_Density;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "maxNumberSSB-CSI-RS-ResourceOneTx","maxNumberSSB-CSI-RS-ResourceTwoTx","supportedCSI-RS-Density" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "maxNumberSSB_CSI_RS_ResourceOneTx","maxNumberSSB_CSI_RS_ResourceTwoTx","supportedCSI_RS_Density" };
    }

    @Override
    public String getAsnName() {
        return "DummyG";
    }

    @Override
    public String getXmlTagName() {
        return "DummyG";
    }

}
