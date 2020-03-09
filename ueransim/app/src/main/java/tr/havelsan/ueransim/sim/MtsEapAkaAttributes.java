package tr.havelsan.ueransim.sim;

import tr.havelsan.ueransim.mts.MtsException;
import tr.havelsan.ueransim.mts.*;
import tr.havelsan.ueransim.nas.eap.EapAkaPrime;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.List;
import java.util.Map;

public class MtsEapAkaAttributes implements TypeRegistry.ICustomTypeRegistry<EapAkaPrime.Attributes> {

    @Override
    public EapAkaPrime.Attributes construct(Class<EapAkaPrime.Attributes> type, Map<String, Object> args) {
        var attr = new EapAkaPrime.Attributes();
        for (var entry : args.entrySet()) {
            var key = EapAkaPrime.EAttributeType.fromIdentifier(EapAkaPrime.EAttributeType.class, entry.getKey());
            if (key == null) {
                throw new MtsException("key not found for EapAkaPrime.Attributes: %s", entry.getKey());
            }
            var value = entry.getValue();

            if (value instanceof OctetString) {
                attr.getAttributes().put(key, (OctetString) value);
            } else if (value instanceof String) {
                attr.getAttributes().put(key, new OctetString((String) value));
            } else if (value instanceof ImplicitTypedObject) {
                var constructed = MtsConstruct.construct(OctetString.class, ((ImplicitTypedObject) value).getParameters(), true);
                attr.getAttributes().put(key, constructed);
            } else {
                var conversions = MtsConvert.convert(value, OctetString.class, true);
                if (conversions.size() == 0)
                    throw new MtsException("no conversion found for types %s and %s", value.getClass().getSimpleName(), OctetString.class.getSimpleName());
                if (conversions.size() > 1)
                    throw new MtsException("multiple conversion found for types %s and %s", value.getClass().getSimpleName(), OctetString.class.getSimpleName());
                attr.getAttributes().put(key, (OctetString) conversions.get(0).value);
            }
        }
        return attr;
    }

    @Override
    public boolean isConvertable(Class<?> from, Class<?> to) {
        return false;
    }

    @Override
    public void convert(Object from, Class<?> to, List<Conversion<?>> list, int depth) {

    }

    @Override
    public Class<EapAkaPrime.Attributes> getRegisteringClass() {
        return EapAkaPrime.Attributes.class;
    }
}
