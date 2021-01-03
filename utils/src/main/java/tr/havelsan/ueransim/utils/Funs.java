/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.utils;

import kotlin.Pair;

import java.util.ArrayList;
import java.util.function.Consumer;

public final class Funs {
    private ArrayList<Pair<Fun, Consumer<Exception>>> funs;

    private Funs(Pair<Fun, Consumer<Exception>> fun) {
        this.funs = new ArrayList<>();
        this.funs.add(fun);
    }

    public static Funs run(Fun fun) {
        return run(fun, null);
    }

    public static Funs run(Fun fun, Consumer<Exception> onError) {
        return new Funs(new Pair<>(fun, onError));
    }

    public Funs then(Fun fun) {
        funs.add(new Pair<>(fun, null));
        return this;
    }

    public Funs then(Fun fun, Consumer<Exception> onError) {
        funs.add(new Pair<>(fun, onError));
        return this;
    }

    public void invoke() {
        for (var pair : funs) {
            if (pair.getSecond() == null) {
                pair.getFirst().run();
            } else {
                try {
                    pair.getFirst().run();
                } catch (Exception e) {
                    pair.getSecond().accept(e);
                    return;
                }
            }
        }
    }
}
