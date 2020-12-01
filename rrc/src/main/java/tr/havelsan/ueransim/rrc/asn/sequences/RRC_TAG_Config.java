/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_TAG_Config__tag_ToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_TAG_Config__tag_ToReleaseList;

public class RRC_TAG_Config extends RRC_Sequence {

    public RRC_TAG_Config__tag_ToReleaseList tag_ToReleaseList;
    public RRC_TAG_Config__tag_ToAddModList tag_ToAddModList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "tag-ToReleaseList","tag-ToAddModList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "tag_ToReleaseList","tag_ToAddModList" };
    }

    @Override
    public String getAsnName() {
        return "TAG-Config";
    }

    @Override
    public String getXmlTagName() {
        return "TAG-Config";
    }

}
