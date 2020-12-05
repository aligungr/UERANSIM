package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_INT_ConfigurationPerServingCell extends AsnSequence {
    public RRC_ServCellIndex servingCellId; // mandatory
    public AsnInteger positionInDCI; // mandatory, VALUE(0..125)
}

