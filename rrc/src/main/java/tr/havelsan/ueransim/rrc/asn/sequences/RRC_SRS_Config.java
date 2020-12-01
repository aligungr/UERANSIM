/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SRS_Config__srs_ResourceSetToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SRS_Config__srs_ResourceSetToReleaseList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SRS_Config__srs_ResourceToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SRS_Config__srs_ResourceToReleaseList;

public class RRC_SRS_Config extends RRC_Sequence {

    public RRC_SRS_Config__srs_ResourceSetToReleaseList srs_ResourceSetToReleaseList;
    public RRC_SRS_Config__srs_ResourceSetToAddModList srs_ResourceSetToAddModList;
    public RRC_SRS_Config__srs_ResourceToReleaseList srs_ResourceToReleaseList;
    public RRC_SRS_Config__srs_ResourceToAddModList srs_ResourceToAddModList;
    public RRC_Integer tpc_Accumulation;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "srs-ResourceSetToReleaseList","srs-ResourceSetToAddModList","srs-ResourceToReleaseList","srs-ResourceToAddModList","tpc-Accumulation" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "srs_ResourceSetToReleaseList","srs_ResourceSetToAddModList","srs_ResourceToReleaseList","srs_ResourceToAddModList","tpc_Accumulation" };
    }

    @Override
    public String getAsnName() {
        return "SRS-Config";
    }

    @Override
    public String getXmlTagName() {
        return "SRS-Config";
    }

}
