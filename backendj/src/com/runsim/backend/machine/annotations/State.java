package com.runsim.backend.machine.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface State {
    int timeout() default 0;
}
