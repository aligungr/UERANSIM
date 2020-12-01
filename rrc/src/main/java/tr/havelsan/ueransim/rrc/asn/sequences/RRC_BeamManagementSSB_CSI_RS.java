/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_BeamManagementSSB_CSI_RS extends RRC_Sequence {

    public RRC_Integer maxNumberSSB_CSI_RS_ResourceOneTx;
    public RRC_Integer maxNumberCSI_RS_Resource;
    public RRC_Integer maxNumberCSI_RS_ResourceTwoTx;
    public RRC_Integer supportedCSI_RS_Density;
    public RRC_Integer maxNumberAperiodicCSI_RS_Resource;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "maxNumberSSB-CSI-RS-ResourceOneTx","maxNumberCSI-RS-Resource","maxNumberCSI-RS-ResourceTwoTx","supportedCSI-RS-Density","maxNumberAperiodicCSI-RS-Resource" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "maxNumberSSB_CSI_RS_ResourceOneTx","maxNumberCSI_RS_Resource","maxNumberCSI_RS_ResourceTwoTx","supportedCSI_RS_Density","maxNumberAperiodicCSI_RS_Resource" };
    }

    @Override
    public String getAsnName() {
        return "BeamManagementSSB-CSI-RS";
    }

    @Override
    public String getXmlTagName() {
        return "BeamManagementSSB-CSI-RS";
    }

}
