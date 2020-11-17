// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#pragma once

#include <cstdint>
#include <list>
#include <string>
#include <iomanip>
#include <sstream>

class Accumulator
{
    std::list<uint64_t> data;
    uint64_t total;

public:
    static constexpr int SIZE = 4;

    Accumulator()
    {
        total = 0;
        data = {};
    }

    void notify(uint64_t v)
    {
        if (data.size() >= SIZE)
        {
            total -= data.front();
            data.pop_front();
            total += v;
            data.push_back(v);
        }
        else
        {
            total += v;
            data.push_back(v);
        }
    }

    uint64_t getTotal()
    {
        return total;
    }
};

class PacketMeter
{
    uint64_t thisSecond;
    uint64_t thisTotal;
    uint64_t lastPeek;
    Accumulator accumulator;

public:
    PacketMeter()
    {
        thisSecond = 0;
        thisTotal = 0;
        lastPeek = 0;
        accumulator = {};
    }

    static uint64_t getCurrentSecond()
    {
        return time(nullptr);
    }

    void notify(int packetLength)
    {
        auto currentSecond = getCurrentSecond();

        if (thisSecond == currentSecond)
        {
            thisTotal += packetLength;
        }
        else
        {
            lastPeek = thisTotal + packetLength;
            accumulator.notify(lastPeek);
            thisSecond = currentSecond;
            thisTotal = packetLength;
        }
    }

    std::string speedMbsPerSec()
    {
        float pi = (accumulator.getTotal() / (float)Accumulator::SIZE) * 8.0f / 1024.0f / 1024.0f;
        std::stringstream stream;
        stream << std::fixed << std::setprecision(2) << pi;
        std::string s = stream.str();
        return s;
    }
};
