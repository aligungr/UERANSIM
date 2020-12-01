/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_DRB_ToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_DRB_ToReleaseList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SRB_ToAddModList;

public class RRC_RadioBearerConfig extends RRC_Sequence {

    public RRC_SRB_ToAddModList srb_ToAddModList;
    public RRC_Integer srb3_ToRelease;
    public RRC_DRB_ToAddModList drb_ToAddModList;
    public RRC_DRB_ToReleaseList drb_ToReleaseList;
    public RRC_SecurityConfig securityConfig;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "srb-ToAddModList","srb3-ToRelease","drb-ToAddModList","drb-ToReleaseList","securityConfig" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "srb_ToAddModList","srb3_ToRelease","drb_ToAddModList","drb_ToReleaseList","securityConfig" };
    }

    @Override
    public String getAsnName() {
        return "RadioBearerConfig";
    }

    @Override
    public String getXmlTagName() {
        return "RadioBearerConfig";
    }

}
