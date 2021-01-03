//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <cassert>
#include <functional>
#include <memory>

template <typename T>
class LinkedItem
{
    LinkedItem *prev;
    LinkedItem *next;
    bool isDummy;

  public:
    T *value;

    ~LinkedItem()
    {
        value = nullptr;
        prev = nullptr;
        next = nullptr;
    }

  private:
    template <typename U>
    friend class LinkedList;

    explicit LinkedItem(T *value) : prev(nullptr), next(nullptr), isDummy(false), value(value)
    {
    }

    LinkedItem(T *value, LinkedItem *prev, LinkedItem *next) : prev(prev), next(next), isDummy(false), value(value)
    {
    }

    LinkedItem(LinkedItem *prev, LinkedItem *next, bool isDummy) : prev{prev}, next{next}, isDummy{isDummy}, value{}
    {
    }

  private:
    inline LinkedItem *asItem(LinkedItem *item)
    {
        return item->isDummy ? nullptr : item;
    }

  public:
    inline LinkedItem *getPrev()
    {
        return asItem(prev);
    }

    inline LinkedItem *getNext()
    {
        return asItem(next);
    }
};

template <typename T>
class LinkedList
{
    LinkedItem<T> *head;
    LinkedItem<T> *tail;
    int count;

  public:
    LinkedList()
    {
        head = new LinkedItem<T>(nullptr, nullptr, true);
        tail = new LinkedItem<T>(nullptr, nullptr, true);

        head->next = tail;
        tail->prev = head;

        count = 0;
    }

    ~LinkedList()
    {
        clearAndDelete();
        delete head;
        delete tail;
    }

  private:
    T *removeBetween(LinkedItem<T> *prev, LinkedItem<T> *next)
    {
        auto middle = prev->next;
        middle->prev = nullptr;
        middle->next = nullptr;

        prev->next = next;
        next->prev = prev;

        count--;

        auto ret = middle->value;
        delete middle;
        return ret;
    }

    void addBetween(T *element, LinkedItem<T> *prev, LinkedItem<T> *next)
    {
        auto newest = new LinkedItem<T>(element, prev, next);
        prev->next = newest;
        next->prev = newest;

        count++;
    }

  public:
    inline LinkedItem<T> *getFirst()
    {
        return head->getNext();
    }

    inline T *getFirstElement()
    {
        auto item = getFirst();
        return item ? item->value : nullptr;
    }

    inline LinkedItem<T> *getLast()
    {
        return tail->getPrev();
    }

    inline T *getLastElement()
    {
        auto item = getLast();
        return item ? item->value : nullptr;
    }

    T *remove(LinkedItem<T> *item)
    {
        if (item == nullptr)
            return nullptr;

        auto next = item->next;
        auto prev = item->prev;
        return removeBetween(prev, next);
    }

    inline T *removeFirst()
    {
        return remove(getFirst());
    }

    inline T *removeLast()
    {
        return remove(getLast());
    }

    [[nodiscard]] inline int getCount() const
    {
        return count;
    }

    [[nodiscard]] inline bool isEmpty() const
    {
        return getCount() == 0;
    }

    void clearAndDelete()
    {
        auto cursor = getFirst();
        while (cursor != nullptr)
        {
            T *value = remove(cursor);
            delete value;
            cursor = cursor->getNext();
        }
    }

    // Same with remove, but increments the cursor (as if not deleted)
    T *removeAndIncrement(LinkedItem<T> *&cursor)
    {
        assert(cursor != nullptr);

        auto nextAsItem = cursor->getNext();
        T *value = removeBetween(cursor->prev, cursor->next);
        cursor = nextAsItem;
        return value;
    }

    inline void addFirst(T *item)
    {
        addBetween(item, head, head->next);
    }

    inline void addLast(T *item)
    {
        addBetween(item, tail->prev, tail);
    }

    inline void addAfter(LinkedItem<T> *item, T *element)
    {
        addBetween(element, item, item->next);
    }

    inline void addBefore(LinkedItem<T> *item, T *element)
    {
        addBetween(element, item->prev, item);
    }

    template <class Predicate>
    bool any(Predicate p)
    {
        auto cursor = getFirst();
        while (cursor != nullptr)
        {
            if (p(cursor->value))
                return true;
            cursor = cursor->getNext();
        }
        return false;
    }
};