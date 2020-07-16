package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_TAI;

public class NGAP_AMFPagingTarget extends NgapChoice {

    public NGAP_GlobalRANNodeID globalRANNodeID;
    public NGAP_TAI tAI;

    @Override
    protected String getAsnName() {
        return "AMFPagingTarget";
    }

    @Override
    protected String getXmlTagName() {
        return "AMFPagingTarget";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"globalRANNodeID", "tAI"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"globalRANNodeID", "tAI"};
    }
}
