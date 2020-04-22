package tr.havelsan.ueransim.mts;

import tr.havelsan.ueransim.nas.EapDecoder;
import tr.havelsan.ueransim.nas.impl.ies.IEEapMessage;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.Utils;

import java.util.Base64;
import java.util.List;
import java.util.Map;

class MtsIEEapMessage implements TypeRegistry.ICustomTypeRegistry<IEEapMessage> {

    @Override
    public IEEapMessage construct(Class<IEEapMessage> type, Map<String, Object> args) {
        if (args.size() == 1 && args.containsKey("payload")) {
            var payload = args.get("payload");
            if (payload == null)
                throw new MtsException("payload cannot be null");
            if (!(payload instanceof String))
                throw new MtsException("payload must be a string");

            var payloadString = (String) payload;
            if (payloadString.length() == 0)
                throw new MtsException("payload cannot be empty");

            String hex;
            try {
                hex = new String(Base64.getDecoder().decode((payloadString)));
            } catch (Exception e) {
                throw new MtsException("payload is not a valid base64 string");
            }

            byte[] bytes;
            try {
                bytes = Utils.hexStringToByteArray(hex);
            } catch (Exception e) {
                throw new MtsException("decoded payload base64 is not a valid hex string");
            }

            return new IEEapMessage(EapDecoder.eapPdu(new OctetInputStream(bytes)));
        }

        return MtsConstruct.construct(type, args, false);
    }

    @Override
    public boolean isConvertable(Class<?> from, Class<?> to) {
        return false;
    }

    @Override
    public void convert(Object from, Class<?> to, List<Conversion<?>> list, int depth) {
    }

    @Override
    public Class<IEEapMessage> getRegisteringClass() {
        return IEEapMessage.class;
    }
}