#pragma once

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
        //delete value;
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
        clear();
        delete head;
        delete tail;
    }

  private:
    void removeBetween(LinkedItem<T> *prev, LinkedItem<T> *next)
    {
        auto middle = prev->next;
        middle->prev = nullptr;
        middle->next = nullptr;

        prev->next = next;
        next->prev = prev;

        count--;

        delete middle;
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

    void remove(LinkedItem<T> *item)
    {
        if (item == nullptr)
            return;

        auto next = item->next;
        auto prev = item->prev;
        removeBetween(prev, next);
    }

    inline void removeFirst()
    {
        remove(getFirst());
    }

    inline void removeLast()
    {
        remove(getLast());
    }

    inline int getCount() const
    {
        return count;
    }

    inline bool isEmpty() const
    {
        return getCount() == 0;
    }

    void clear()
    {
        while (!isEmpty())
            removeFirst();
    }

    // Same with remove, but returns 'next' of deleted item (as if not deleted)
    LinkedItem<T> *removeAndNext(LinkedItem<T> *item)
    {
        if (item == nullptr)
            return nullptr;

        auto nextAsItem = item->getNext();
        auto next = item->next;
        auto prev = item->prev;
        removeBetween(prev, next);

        return nextAsItem;
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
        }
        return false;
    }
};