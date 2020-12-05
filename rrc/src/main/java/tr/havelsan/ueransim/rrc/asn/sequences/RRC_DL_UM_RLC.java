package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SN_FieldLengthUM;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_T_Reassembly;

public class RRC_DL_UM_RLC extends AsnSequence {
    public RRC_SN_FieldLengthUM sn_FieldLength; // optional
    public RRC_T_Reassembly t_Reassembly; // mandatory
}

