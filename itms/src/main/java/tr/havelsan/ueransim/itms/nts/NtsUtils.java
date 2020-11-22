/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.itms.nts;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

// NOTE: This class is the reason why I hate Java.
class NtsUtils {

    static Object take(BlockingDeque<Object> msgQueue) {
        try {
            return msgQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
            throw new RuntimeException(e);
        }
    }

    static Object poll(BlockingDeque<Object> msgQueue, int timeout) {
        try {
            return msgQueue.poll(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
            throw new RuntimeException(e);
        }
    }
}
