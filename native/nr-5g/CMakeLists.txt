cmake_minimum_required(VERSION 3.17)
project(untitled)

set(CMAKE_CXX_STANDARD 17)

add_executable(untitled
        utils/octet_string.cpp utils/octet_string.hpp
        utils/nts.cpp utils/nts.hpp
        utils/bit_buffer.cpp utils/bit_buffer.hpp
        utils/linked_list.cpp utils/linked_list.hpp

        rlc/rlc_utils.cpp rlc/rlc_utils.hpp
        rlc/rlc.cpp rlc/rlc.hpp
        rlc/rlc_entity_tm.cpp rlc/rlc_entity_tm.hpp
        rlc/rlc_test.cpp rlc/rlc_test.hpp
        rlc/rlc_entity_um.cpp rlc/rlc_entity_um.hpp
        rlc/rlc_func.cpp rlc/rlc_func.hpp
        rlc/rlc_encoder.cpp rlc/rlc_encoder.hpp
        rlc/rlc_entity_am.cpp rlc/rlc_entity_am.hpp
        rlc/rlc_test2.cpp rlc/rlc_test2.hpp
        )

target_link_libraries(untitled pthread)

target_compile_options(untitled PRIVATE -Wall -Wextra -pedantic -Wno-unused-parameter)