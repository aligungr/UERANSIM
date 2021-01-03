/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.*;

public class ServiceRequest extends PlainMmMessage {
    public IENasKeySetIdentifier ngKSI;
    public IEServiceType serviceType;
    public IE5gsMobileIdentity tmsi;
    public IEUplinkDataStatus uplinkDataStatus;
    public IEPduSessionStatus pduSessionStatus;
    public IEAllowedPduSessionStatus allowedPduSessionStatus;
    public IENasMessageContainer nasMessageContainer;

    public ServiceRequest() {
        super(EMessageType.SERVICE_REQUEST);
    }

    public ServiceRequest(IENasKeySetIdentifier ngKSI, IEServiceType serviceType, IE5gsMobileIdentity tmsi, IEUplinkDataStatus uplinkDataStatus, IEPduSessionStatus pduSessionStatus, IEAllowedPduSessionStatus allowedPduSessionStatus, IENasMessageContainer nasMessageContainer) {
        this();
        this.ngKSI = ngKSI;
        this.serviceType = serviceType;
        this.tmsi = tmsi;
        this.uplinkDataStatus = uplinkDataStatus;
        this.pduSessionStatus = pduSessionStatus;
        this.allowedPduSessionStatus = allowedPduSessionStatus;
        this.nasMessageContainer = nasMessageContainer;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE1("serviceType", "ngKSI");
        builder.mandatoryIE("tmsi");

        builder.optionalIE(0x40, "uplinkDataStatus");
        builder.optionalIE(0x50, "pduSessionStatus");
        builder.optionalIE(0x25, "allowedPduSessionStatus");
        builder.optionalIE(0x71, "nasMessageContainer");
    }
}
