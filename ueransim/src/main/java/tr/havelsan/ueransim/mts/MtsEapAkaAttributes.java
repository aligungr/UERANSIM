package tr.havelsan.ueransim.mts;

import tr.havelsan.ueransim.nas.eap.EAttributeType;
import tr.havelsan.ueransim.nas.eap.EapAttributes;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.List;
import java.util.Map;

class MtsEapAkaAttributes implements TypeRegistry.ICustomTypeRegistry<EapAttributes> {

    @Override
    public EapAttributes construct(Class<EapAttributes> type, Map<String, Object> args) {
        var attr = new EapAttributes();
        for (var entry : args.entrySet()) {
            var key = EAttributeType.fromIdentifier(EAttributeType.class, entry.getKey());
            if (key == null) {
                throw new MtsException("key not found for EapAttributes: %s", entry.getKey());
            }
            var value = entry.getValue();

            if (value instanceof OctetString) {
                attr.putAttribute(key, (OctetString) value);
            } else if (value instanceof String) {
                attr.putAttribute(key, new OctetString((String) value));
            } else if (value instanceof ImplicitTypedObject) {
                var constructed = MtsConstruct.construct(OctetString.class, (ImplicitTypedObject) value, true);
                attr.putAttribute(key, constructed);
            } else {
                var conversions = MtsConvert.convert(value, OctetString.class, true);
                if (conversions.size() == 0)
                    throw new MtsException("no conversion found for types %s and %s", value.getClass().getSimpleName(), OctetString.class.getSimpleName());
                if (conversions.size() > 1)
                    throw new MtsException("multiple conversion found for types %s and %s", value.getClass().getSimpleName(), OctetString.class.getSimpleName());
                attr.putAttribute(key, (OctetString) conversions.get(0).value);
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
    public Class<EapAttributes> getRegisteringClass() {
        return EapAttributes.class;
    }
}