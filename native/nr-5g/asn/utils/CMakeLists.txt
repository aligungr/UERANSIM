cmake_minimum_required(VERSION 3.17)

file(GLOB HDR_FILES *.hpp)
file(GLOB SRC_FILES *.cpp)

add_library(asn-utils ${HDR_FILES} ${SRC_FILES})

target_compile_options(asn-utils PRIVATE -Wall -Wextra -pedantic -Wno-unused-parameter)
target_include_directories(asn-utils PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})

target_link_libraries(asn-utils utils)
target_link_libraries(asn-utils sctp-cxx)
target_link_libraries(asn-utils logger)
target_link_libraries(asn-utils asn-ngap)
target_link_libraries(asn-utils asn-rrc)
