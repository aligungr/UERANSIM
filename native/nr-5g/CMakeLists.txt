cmake_minimum_required(VERSION 3.17)
project(untitled)

set(CMAKE_CXX_STANDARD 17)

add_executable(untitled
        src/main.cpp

        src/utils/octet_string.cpp src/utils/octet_string.hpp
        src/utils/nts.cpp src/utils/nts.hpp
        src/utils/bit_buffer.cpp src/utils/bit_buffer.hpp
        src/utils/linked_list.cpp src/utils/linked_list.hpp
        src/utils/octet_buffer.cpp src/utils/octet_buffer.hpp
        src/utils/bits.cpp src/utils/bits.hpp

        src/rlc/rlc_utils.cpp src/rlc/rlc_utils.hpp
        src/rlc/rlc.cpp src/rlc/rlc.hpp
        src/rlc/rlc_entity_tm.cpp src/rlc/rlc_entity_tm.hpp
        src/rlc/rlc_test.cpp src/rlc/rlc_test.hpp
        src/rlc/rlc_entity_um.cpp src/rlc/rlc_entity_um.hpp
        src/rlc/rlc_func.cpp src/rlc/rlc_func.hpp
        src/rlc/rlc_encoder.cpp src/rlc/rlc_encoder.hpp
        src/rlc/rlc_entity_am.cpp src/rlc/rlc_entity_am.hpp
        src/rlc/rlc_test2.cpp src/rlc/rlc_test2.hpp

        src/asn/core.cpp src/asn/core.hpp
        src/asn/ber_tlv.cpp src/asn/ber_tlv.hpp
        src/asn/ber_decoder.cpp
        src/asn/ber_decoder.hpp src/utils/bit_string.cpp src/utils/bit_string.hpp)

target_link_libraries(untitled pthread)

target_compile_options(untitled PRIVATE -Wall -Wextra -pedantic -Wno-unused-parameter)