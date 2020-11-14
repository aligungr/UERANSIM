/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

import java.util.List;

public class NGAP_CauseProtocol extends NGAP_Enumerated {

    public static final NGAP_CauseProtocol TRANSFER_SYNTAX_ERROR = new NGAP_CauseProtocol("transfer-syntax-error");
    public static final NGAP_CauseProtocol ABSTRACT_SYNTAX_ERROR_REJECT = new NGAP_CauseProtocol("abstract-syntax-error-reject");
    public static final NGAP_CauseProtocol ABSTRACT_SYNTAX_ERROR_IGNORE_AND_NOTIFY = new NGAP_CauseProtocol("abstract-syntax-error-ignore-and-notify");
    public static final NGAP_CauseProtocol MESSAGE_NOT_COMPATIBLE_WITH_RECEIVER_STATE = new NGAP_CauseProtocol("message-not-compatible-with-receiver-state");
    public static final NGAP_CauseProtocol SEMANTIC_ERROR = new NGAP_CauseProtocol("semantic-error");
    public static final NGAP_CauseProtocol ABSTRACT_SYNTAX_ERROR_FALSELY_CONSTRUCTED_MESSAGE = new NGAP_CauseProtocol("abstract-syntax-error-falsely-constructed-message");
    public static final NGAP_CauseProtocol UNSPECIFIED = new NGAP_CauseProtocol("unspecified");

    protected NGAP_CauseProtocol(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "CauseProtocol";
    }

    @Override
    public String getXmlTagName() {
        return "CauseProtocol";
    }
}
