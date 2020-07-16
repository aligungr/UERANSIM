package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_AMFPagingTarget;

public class NGAP_RecommendedRANNodeItem extends NgapSequence {

    public NGAP_AMFPagingTarget aMFPagingTarget;

    @Override
    protected String getAsnName() {
        return "RecommendedRANNodeItem";
    }

    @Override
    protected String getXmlTagName() {
        return "RecommendedRANNodeItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"aMFPagingTarget"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"aMFPagingTarget"};
    }
}
