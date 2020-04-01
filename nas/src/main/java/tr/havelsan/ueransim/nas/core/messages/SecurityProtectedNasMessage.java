package tr.havelsan.ueransim.nas.core.messages;

import tr.havelsan.ueransim.nas.impl.enums.EExtendedProtocolDiscriminator;
import tr.havelsan.ueransim.nas.impl.enums.ESecurityHeaderType;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet4;

public class SecurityProtectedNasMessage extends NasMessage {
    public ESecurityHeaderType securityHeaderType;
    public Octet4 messageAuthenticationCode;
    public Octet sequenceNumber;
    public NasMessage plainNasMessage;

    public SecurityProtectedNasMessage() {
        super.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES;

        this.securityHeaderType = ESecurityHeaderType.NOT_PROTECTED;
        this.messageAuthenticationCode = new Octet4();
        this.sequenceNumber = new Octet();
        this.plainNasMessage = null;
    }

    public final void encodeMessage(OctetOutputStream stream) {
        super.encodeMessage(stream);
        stream.writeOctet(securityHeaderType.intValue());
        stream.writeOctet4(messageAuthenticationCode);
        stream.writeOctet(sequenceNumber);
        plainNasMessage.encodeMessage(stream);
    }
}
