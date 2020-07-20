package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_NextPagingAreaScope extends NGAP_Enumerated {

    public static final NGAP_NextPagingAreaScope SAME = new NGAP_NextPagingAreaScope("same");
    public static final NGAP_NextPagingAreaScope CHANGED = new NGAP_NextPagingAreaScope("changed");

    protected NGAP_NextPagingAreaScope(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "NextPagingAreaScope";
    }

    @Override
    public String getXmlTagName() {
        return "NextPagingAreaScope";
    }
}
