/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_PDCP_Parameters extends RRC_Sequence {

    public RRC_PDCP_Parameters__supportedROHC_Profiles supportedROHC_Profiles;
    public RRC_Integer maxNumberROHC_ContextSessions;
    public RRC_Integer uplinkOnlyROHC_Profiles;
    public RRC_Integer continueROHC_Context;
    public RRC_Integer outOfOrderDelivery;
    public RRC_Integer shortSN;
    public RRC_Integer pdcp_DuplicationSRB;
    public RRC_Integer pdcp_DuplicationMCG_OrSCG_DRB;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "supportedROHC-Profiles","maxNumberROHC-ContextSessions","uplinkOnlyROHC-Profiles","continueROHC-Context","outOfOrderDelivery","shortSN","pdcp-DuplicationSRB","pdcp-DuplicationMCG-OrSCG-DRB" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "supportedROHC_Profiles","maxNumberROHC_ContextSessions","uplinkOnlyROHC_Profiles","continueROHC_Context","outOfOrderDelivery","shortSN","pdcp_DuplicationSRB","pdcp_DuplicationMCG_OrSCG_DRB" };
    }

    @Override
    public String getAsnName() {
        return "PDCP-Parameters";
    }

    @Override
    public String getXmlTagName() {
        return "PDCP-Parameters";
    }

}
