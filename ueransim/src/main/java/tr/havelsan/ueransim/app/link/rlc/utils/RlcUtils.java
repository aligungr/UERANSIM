/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.utils;

import java.util.Comparator;
import java.util.LinkedList;

public class RlcUtils {

    public static <T> void insertSortedLinkedList(LinkedList<T> list, T item, Comparator<T> comparator) {
        var iterator = list.listIterator();
        while (true) {
            if (!iterator.hasNext()) {
                iterator.add(item);
                break;
            }

            var cursor = iterator.next();
            if (comparator.compare(cursor, item) > 0) {
                iterator.previous();
                iterator.add(item);
                break;
            }
        }
    }
}
