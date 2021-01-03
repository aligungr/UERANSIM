/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.utils;

import java.util.ArrayList;

public class LinkedList<T> {
    private final Item head;
    private final Item tail;
    private int size;

    public LinkedList() {
        this.head = new Item(null);
        this.tail = new Item(null);

        head.next = tail;
        tail.prev = head;

        this.size = 0;
    }

    public Item getFirst() {
        return head.getNext();
    }

    public T getFirstElement() {
        var item = getFirst();
        return item == null ? null : item.value;
    }

    public Item getLast() {
        return tail.getPrev();
    }

    public T getLastElement() {
        var item = getLast();
        return item == null ? null : item.value;
    }

    public void removeFirst() {
        remove(getFirst());
    }

    public void removeLast() {
        remove(getLast());
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        while (!isEmpty()) {
            removeFirst();
        }
    }

    private void removeBetween(Item prev, Item next) {
        var middle = prev.next;
        middle.prev = null;
        middle.next = null;

        prev.next = next;
        next.prev = prev;

        size--;
    }

    public void remove(Item item) {
        if (item == null)
            return;

        var next = item.next;
        var prev = item.prev;
        removeBetween(prev, next);
    }

    // Use with caution, do not modify while iterating.
    public Iterable<T> elements() {
        var list = new ArrayList<T>();
        var cursor = getFirst();
        while (cursor != null) {
            list.add(cursor.value);
            cursor = cursor.getNext();
        }
        return list;
    }

    // Same with remove, but returns 'next' of deleted item (as if not deleted)
    public Item removeAndNext(Item item) {
        if (item == null)
            return null;

        var nextAsItem = item.getNext();
        var next = item.next;
        var prev = item.prev;
        removeBetween(prev, next);

        return nextAsItem;
    }

    private void addBetween(T element, Item prev, Item next) {
        var newest = new Item(element, prev, next);
        prev.next = newest;
        next.prev = newest;

        size++;
    }

    public void addFirst(T item) {
        addBetween(item, head, head.next);
    }

    public void addLast(T item) {
        addBetween(item, tail.prev, tail);
    }

    public void addAfter(Item item, T element) {
        addBetween(element, item, item.next);
    }

    public void addBefore(Item item, T element) {
        addBetween(element, item.prev, item);
    }

    public class Item {
        public T value;
        private Item prev;
        private Item next;

        public Item(T value) {
            this.value = value;
        }

        public Item(T value, Item prev, Item next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        public Item getPrev() {
            return asItem(prev);
        }

        public Item getNext() {
            return asItem(next);
        }

        private Item asItem(Item item) {
            if (item == head || item == tail)
                return null;
            return item;
        }
    }
}
