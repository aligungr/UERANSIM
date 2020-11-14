/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

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

public class NGAP_SourceNGRANNode_ToTargetNGRANNode_TransparentContainer extends NGAP_Sequence {

    public NGAP_RRCContainer rRCContainer;
    public NGAP_PDUSessionResourceInformationList pDUSessionResourceInformationList;
    public NGAP_E_RABInformationList e_RABInformationList;
    public NGAP_NGRAN_CGI targetCell_ID;
    public NGAP_IndexToRFSP indexToRFSP;
    public NGAP_UEHistoryInformation uEHistoryInformation;

    @Override
    public String getAsnName() {
        return "SourceNGRANNode-ToTargetNGRANNode-TransparentContainer";
    }

    @Override
    public String getXmlTagName() {
        return "SourceNGRANNode-ToTargetNGRANNode-TransparentContainer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"rRCContainer", "pDUSessionResourceInformationList", "e-RABInformationList", "targetCell-ID", "indexToRFSP", "uEHistoryInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"rRCContainer", "pDUSessionResourceInformationList", "e_RABInformationList", "targetCell_ID", "indexToRFSP", "uEHistoryInformation"};
    }
}
