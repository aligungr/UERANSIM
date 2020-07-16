package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_ProcedureCode extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "ProcedureCode";
    }

    @Override
    protected String getXmlTagName() {
        return "ProcedureCode";
    }
}
