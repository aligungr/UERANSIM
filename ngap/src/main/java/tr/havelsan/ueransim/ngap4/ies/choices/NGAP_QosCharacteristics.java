package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

public class NGAP_QosCharacteristics extends NgapChoice {

    public NGAP_NonDynamic5QIDescriptor nonDynamic5QI;
    public NGAP_Dynamic5QIDescriptor dynamic5QI;

    @Override
    protected String getAsnName() {
        return "QosCharacteristics";
    }

    @Override
    protected String getXmlTagName() {
        return "QosCharacteristics";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"nonDynamic5QI", "dynamic5QI"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"nonDynamic5QI", "dynamic5QI"};
    }
}
