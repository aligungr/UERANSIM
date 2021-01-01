cmake_minimum_required(VERSION 3.17)
project(ueransim)

set(CMAKE_CXX_STANDARD 17)

add_subdirectory(asn/asn1c)
add_subdirectory(asn/rrc)
add_subdirectory(asn/ngap)
add_subdirectory(utils)

add_executable(ueransim
        rlc/rlc_utils.cpp rlc/rlc_utils.hpp
        rlc/rlc.cpp rlc/rlc.hpp
        rlc/rlc_entity_tm.cpp rlc/rlc_entity_tm.hpp
        rlc/rlc_test.cpp rlc/rlc_test.hpp
        rlc/rlc_entity_um.cpp rlc/rlc_entity_um.hpp
        rlc/rlc_func.cpp rlc/rlc_func.hpp
        rlc/rlc_encoder.cpp rlc/rlc_encoder.hpp
        rlc/rlc_entity_am.cpp rlc/rlc_entity_am.hpp
        rlc/rlc_test2.cpp rlc/rlc_test2.hpp)

target_link_libraries(ueransim pthread)

target_link_libraries(ueransim asn-rrc)
target_link_libraries(ueransim asn-ngap)
target_link_libraries(ueransim utils)

target_compile_options(ueransim PRIVATE -Wall -Wextra -pedantic -Wno-unused-parameter)
