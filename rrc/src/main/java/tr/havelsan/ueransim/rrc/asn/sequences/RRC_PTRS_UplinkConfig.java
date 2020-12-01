/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_PTRS_UplinkConfig extends RRC_Sequence {

    public RRC_PTRS_UplinkConfig__transformPrecoderDisabled transformPrecoderDisabled;
    public RRC_PTRS_UplinkConfig__transformPrecoderEnabled transformPrecoderEnabled;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "transformPrecoderDisabled","transformPrecoderEnabled" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "transformPrecoderDisabled","transformPrecoderEnabled" };
    }

    @Override
    public String getAsnName() {
        return "PTRS-UplinkConfig";
    }

    @Override
    public String getXmlTagName() {
        return "PTRS-UplinkConfig";
    }

}
