/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SRS_Resource__resourceMapping extends RRC_Sequence {

    public RRC_Integer startPosition;
    public RRC_Integer nrofSymbols;
    public RRC_Integer repetitionFactor;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "startPosition","nrofSymbols","repetitionFactor" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "startPosition","nrofSymbols","repetitionFactor" };
    }

}
