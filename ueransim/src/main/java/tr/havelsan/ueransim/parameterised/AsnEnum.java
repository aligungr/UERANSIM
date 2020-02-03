package tr.havelsan.ueransim.parameterised;

import fr.marben.asnsdk.japi.spe.ExtEnumeratedValue;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AsnEnum {
    Class<? extends ExtEnumeratedValue> value();
}
