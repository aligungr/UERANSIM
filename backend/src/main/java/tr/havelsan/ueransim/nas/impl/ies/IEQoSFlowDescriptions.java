package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement6;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IEQoSFlowDescriptions extends InformationElement6 {
    public OctetString rawData;

    public IEQoSFlowDescriptions() {
    }

    public IEQoSFlowDescriptions(OctetString rawData) {
        this.rawData = rawData;
    }

    @Override
    protected IEQoSFlowDescriptions decodeIE6(OctetInputStream stream, int length) {
        var res = new IEQoSFlowDescriptions();
        res.rawData = stream.readOctetString(length);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctetString(rawData);
    }
}
