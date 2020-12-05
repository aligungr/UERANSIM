/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.asn.interop;

import java.util.List;

public class AsnMeta_TypeDesc {
    public int type_info_id;
    public String asn_name;
    public String xml_name;
    public int asn_type;
    public AsnMeta_Limits limits;
    public int element_count;
    public List<AsnMeta_Member> members;
    public AsnMeta_IntSpec int_specs; // may be null
    public boolean probably_anonymous;

    // only meaningfull for some sequence_of.
    // -1: undefined, 0: XMLDelimetedItemList  1,2:XMLValueList
    public int list_type;
}
