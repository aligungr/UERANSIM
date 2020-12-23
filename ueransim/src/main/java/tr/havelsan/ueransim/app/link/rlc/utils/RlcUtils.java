/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.utils;

import tr.havelsan.ueransim.utils.LinkedList;

import java.util.Comparator;

public class RlcUtils {

    public static <T> void insertSortedLinkedList(LinkedList<T> list, T item, Comparator<T> comparator) {
        if (list.isEmpty()) {
            list.addFirst(item);
            return;
        }

        var cursor = list.getFirst();
        while (cursor != null) {
            if (comparator.compare(cursor.value, item) > 0)
                break;
            cursor = cursor.getNext();
        }

        if (cursor != null) {
            list.addAfter(cursor, item);
        } else {
            list.addLast(item);
        }
    }
}
