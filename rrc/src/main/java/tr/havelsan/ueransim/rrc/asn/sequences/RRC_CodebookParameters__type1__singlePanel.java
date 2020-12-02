/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CodebookParameters__type1__singlePanel__supportedCSI_RS_ResourceList;

public class RRC_CodebookParameters__type1__singlePanel extends RRC_Sequence {

    public RRC_CodebookParameters__type1__singlePanel__supportedCSI_RS_ResourceList supportedCSI_RS_ResourceList;
    public RRC_Integer modes;
    public RRC_Integer maxNumberCSI_RS_PerResourceSet;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "supportedCSI-RS-ResourceList","modes","maxNumberCSI-RS-PerResourceSet" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "supportedCSI_RS_ResourceList","modes","maxNumberCSI_RS_PerResourceSet" };
    }

}
