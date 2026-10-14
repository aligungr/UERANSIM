//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <cstdint>
#include <memory>
#include <mutex>

#include <utils/nts.hpp>

// Runs an NtsTask on the calling thread instead of its own, so that a test decides when it handles what. A task that
// this drives is never start()ed or quit().
class NtsTaskDriver
{
  public:
    // What start() would do before it starts the thread.
    static void start(NtsTask &task)
    {
        task.onStart();
    }

    // What quit() would do once the thread has stopped.
    static void quit(NtsTask &task)
    {
        task.onQuit();
    }

    // Handles the next message waiting for the task, or the next timer of the task that is due. Returns false if there
    // was neither.
    static bool runOnce(NtsTask &task)
    {
        auto msg = task.poll();
        if (!msg)
            return false;
        // onLoop() takes the message itself, and finds this one first.
        task.pushFront(std::move(msg));
        task.onLoop();
        return true;
    }

    static void runUntilIdle(NtsTask &task)
    {
        while (runOnce(task))
        {
        }
    }

    // Takes the next message waiting for a task that only collects them, such as one standing in for a peer.
    static std::unique_ptr<NtsMessage> take(NtsTask &task)
    {
        return task.poll();
    }

    // How long until the next timer of the task falls due, 0 if one has already. 500 if the task has no timer.
    static int64_t nextTimerIn(NtsTask &task)
    {
        std::lock_guard<std::mutex> lock{task.mutex};
        return task.timerBase.getNextWaitTime();
    }
};
