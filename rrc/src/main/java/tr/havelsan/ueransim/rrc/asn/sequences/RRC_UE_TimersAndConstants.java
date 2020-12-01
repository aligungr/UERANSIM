/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UE_TimersAndConstants extends RRC_Sequence {

    public RRC_Integer t300;
    public RRC_Integer t301;
    public RRC_Integer t310;
    public RRC_Integer n310;
    public RRC_Integer t311;
    public RRC_Integer n311;
    public RRC_Integer t319;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "t300","t301","t310","n310","t311","n311","t319" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "t300","t301","t310","n310","t311","n311","t319" };
    }

    @Override
    public String getAsnName() {
        return "UE-TimersAndConstants";
    }

    @Override
    public String getXmlTagName() {
        return "UE-TimersAndConstants";
    }

}
