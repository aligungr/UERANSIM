/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0.ies.octet_strings;

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

public class NGAP_EN_DCSONConfigurationTransfer extends NGAP_OctetString {

    public NGAP_EN_DCSONConfigurationTransfer(OctetString value) {
        super(value);
    }

    public NGAP_EN_DCSONConfigurationTransfer(BitString value) {
        super(value);
    }

    public NGAP_EN_DCSONConfigurationTransfer(Octet[] octets) {
        super(octets);
    }

    public NGAP_EN_DCSONConfigurationTransfer(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_EN_DCSONConfigurationTransfer(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_EN_DCSONConfigurationTransfer(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "EN-DCSONConfigurationTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "EN-DCSONConfigurationTransfer";
    }
}
