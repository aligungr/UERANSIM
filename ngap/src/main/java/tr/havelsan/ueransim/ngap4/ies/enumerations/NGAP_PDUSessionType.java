package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_PDUSessionType extends NgapEnumerated {

    public static final NGAP_PDUSessionType IPV4 = new NGAP_PDUSessionType("ipv4");
    public static final NGAP_PDUSessionType IPV6 = new NGAP_PDUSessionType("ipv6");
    public static final NGAP_PDUSessionType IPV4V6 = new NGAP_PDUSessionType("ipv4v6");
    public static final NGAP_PDUSessionType ETHERNET = new NGAP_PDUSessionType("ethernet");
    public static final NGAP_PDUSessionType UNSTRUCTURED = new NGAP_PDUSessionType("unstructured");

    protected NGAP_PDUSessionType(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "PDUSessionType";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionType";
    }
}
