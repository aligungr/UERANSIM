/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_NGRAN_CGI;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_IndexToRFSP;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_RRCContainer;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_E_RABInformationList;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_PDUSessionResourceInformationList;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_UEHistoryInformation;

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
