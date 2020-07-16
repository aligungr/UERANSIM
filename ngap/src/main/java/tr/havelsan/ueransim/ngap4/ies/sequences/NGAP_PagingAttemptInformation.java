package tr.havelsan.ueransim.ngap4.ies.sequences;

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

public class NGAP_PagingAttemptInformation extends NgapSequence {

    public NGAP_PagingAttemptCount pagingAttemptCount;
    public NGAP_IntendedNumberOfPagingAttempts intendedNumberOfPagingAttempts;
    public NGAP_NextPagingAreaScope nextPagingAreaScope;

    @Override
    protected String getAsnName() {
        return "PagingAttemptInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "PagingAttemptInformation";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pagingAttemptCount", "intendedNumberOfPagingAttempts", "nextPagingAreaScope"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pagingAttemptCount", "intendedNumberOfPagingAttempts", "nextPagingAreaScope"};
    }
}
