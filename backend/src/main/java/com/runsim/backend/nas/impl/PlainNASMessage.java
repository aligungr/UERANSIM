package com.runsim.backend.nas.impl;

import com.runsim.backend.nas.core.BitInputStream;
import com.runsim.backend.nas.core.BitOutputStream;
import com.runsim.backend.nas.core.NASValue;
import com.runsim.backend.nas.core.SpareHalfOctetValue;

public class PlainNASMessage extends NASValue {

    private ExtendedProtocolDiscriminator extendedProtocolDiscriminator;
    private SecurityHeaderType securityHeaderType;
    private SpareHalfOctetValue spareHalfOctetValue;

    public PlainNASMessage() {
        this.extendedProtocolDiscriminator = new ExtendedProtocolDiscriminator();
        this.securityHeaderType = new SecurityHeaderType();
        this.spareHalfOctetValue = new SpareHalfOctetValue();
    }

    @Override
    public void encode(BitOutputStream stream) {
        extendedProtocolDiscriminator.encode(stream);
        securityHeaderType.encode(stream);
        spareHalfOctetValue.encode(stream);
    }

    @Override
    public void decode(BitInputStream stream) {
        extendedProtocolDiscriminator.decode(stream);
        securityHeaderType.decode(stream);
        spareHalfOctetValue.decode(stream);
    }

    @Override
    public String display() {
        return "Plain NAS Message";
    }

    public ExtendedProtocolDiscriminator getExtendedProtocolDiscriminator() {
        return extendedProtocolDiscriminator;
    }

    public void setExtendedProtocolDiscriminator(ExtendedProtocolDiscriminator extendedProtocolDiscriminator) {
        this.extendedProtocolDiscriminator = extendedProtocolDiscriminator;
    }

    public SecurityHeaderType getSecurityHeaderType() {
        return securityHeaderType;
    }

    public void setSecurityHeaderType(SecurityHeaderType securityHeaderType) {
        this.securityHeaderType = securityHeaderType;
    }
}
