package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_Dynamic5QIDescriptor;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_NonDynamic5QIDescriptor;

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
