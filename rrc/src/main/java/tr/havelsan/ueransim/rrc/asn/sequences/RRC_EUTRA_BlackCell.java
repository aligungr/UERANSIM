package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_EUTRA_CellIndex;

public class RRC_EUTRA_BlackCell extends AsnSequence {
    public RRC_EUTRA_CellIndex cellIndexEUTRA; // mandatory
    public RRC_EUTRA_PhysCellIdRange physCellIdRange; // mandatory
}

