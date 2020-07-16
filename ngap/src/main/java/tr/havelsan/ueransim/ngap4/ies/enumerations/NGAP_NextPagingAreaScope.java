package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_NextPagingAreaScope extends NgapEnumerated {

    public static final NGAP_NextPagingAreaScope SAME = new NGAP_NextPagingAreaScope("same");
    public static final NGAP_NextPagingAreaScope CHANGED = new NGAP_NextPagingAreaScope("changed");

    protected NGAP_NextPagingAreaScope(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "NextPagingAreaScope";
    }

    @Override
    protected String getXmlTagName() {
        return "NextPagingAreaScope";
    }
}
