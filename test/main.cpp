//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

// The test runner. Test cases register themselves, so a file added under test/ runs without being listed anywhere.
//
// Tests for src/<path>/<unit> live in test/<path>/<unit>.cpp, and their names start with "<path>/<unit>: ". That
// keeps every name unique, which ctest needs since `make test` runs each test case as its own ctest test, and lets a
// subset be run by name, e.g. `nr-tests -tc="lib/nas/*"` or `ctest -R lib/nas/`.
//
// What several tests share lives in test/support/ and is included as <test/support/...>.

#define DOCTEST_CONFIG_IMPLEMENT_WITH_MAIN
#include <ext/doctest/doctest.h>
