cmake_minimum_required(VERSION 3.17)
project(ueransim)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_C_STANDARD 11)

include_directories(src)

#################### SUB DIRECTORIES ####################

add_subdirectory(src/ext)
add_subdirectory(src/utils)
add_subdirectory(src/asn/asn1c)
add_subdirectory(src/asn/rrc)
add_subdirectory(src/asn/ngap)
add_subdirectory(src/lib)
add_subdirectory(src/gnb)
add_subdirectory(src/ue)

#################### GNB EXECUTABLE ####################

add_executable(nr-gnb src/gnb.cpp)

target_link_libraries(nr-gnb pthread)

target_compile_options(nr-gnb PRIVATE -Wall -Wextra -pedantic)

target_link_libraries(nr-gnb asn-rrc)
target_link_libraries(nr-gnb asn-ngap)
target_link_libraries(nr-gnb common-lib)
target_link_libraries(nr-gnb gnb)

#################### UE EXECUTABLE ####################

add_executable(nr-ue src/ue.cpp)
target_link_libraries(nr-ue pthread)
target_compile_options(nr-ue PRIVATE -Wall -Wextra -pedantic)

target_link_libraries(nr-ue common-lib)
target_link_libraries(nr-ue ue)

###################### IF BINDER ######################
add_library(devbnd SHARED src/binder.cpp)
target_compile_options(devbnd PRIVATE -D_GNU_SOURCE -Wall -Wextra)
target_link_options(devbnd PRIVATE -nostartfiles)
target_link_libraries(devbnd dl)

#################### CLI EXECUTABLE ####################
add_executable(nr-cli src/cli.cpp)
target_link_libraries(nr-cli pthread)
target_compile_options(nr-cli PRIVATE -Wall -Wextra -pedantic)

target_link_libraries(nr-cli common-lib)

#################### TEST EXECUTABLE ####################
# Built by default so the tests cannot rot, but it is not part of what `make build` installs.
option(BUILD_TESTS "Build the nr-tests executable" ON)

if (BUILD_TESTS)
    # CONFIGURE_DEPENDS so that adding a test file re-runs cmake instead of silently skipping it.
    file(GLOB_RECURSE TEST_SRC_FILES CONFIGURE_DEPENDS test/*.cpp)
    add_executable(nr-tests ${TEST_SRC_FILES})
    target_link_libraries(nr-tests pthread)
    target_compile_options(nr-tests PRIVATE -Wall -Wextra -pedantic)
    target_link_libraries(nr-tests common-lib)
    target_link_libraries(nr-tests ue)
    target_link_libraries(nr-tests ext)

    enable_testing()
    # Registers each doctest test case as its own ctest test, as listed by the built binary, so a failure or a crash
    # is reported against the test case that caused it instead of the whole suite.
    include(src/ext/doctest/doctest.cmake)
    doctest_discover_tests(nr-tests)
endif ()
