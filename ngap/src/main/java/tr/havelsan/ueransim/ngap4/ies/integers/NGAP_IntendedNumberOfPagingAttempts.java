package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_IntendedNumberOfPagingAttempts extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "IntendedNumberOfPagingAttempts";
    }

    @Override
    protected String getXmlTagName() {
        return "IntendedNumberOfPagingAttempts";
    }
}
