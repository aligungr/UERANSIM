package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;

public class NGAP_RecommendedRANNodeItem extends NGAP_Sequence {

    public NGAP_AMFPagingTarget aMFPagingTarget;

    @Override
    public String getAsnName() {
        return "RecommendedRANNodeItem";
    }

    @Override
    public String getXmlTagName() {
        return "RecommendedRANNodeItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"aMFPagingTarget"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"aMFPagingTarget"};
    }
}
