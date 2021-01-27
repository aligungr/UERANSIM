cmake_minimum_required(VERSION 3.17)

file(GLOB HDR_FILES *.h)
file(GLOB SRC_FILES *.c)

add_library(asn-rrc ${HDR_FILES} ${SRC_FILES})
target_link_libraries(asn-rrc asn-asn1c)
target_include_directories(asn-rrc PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
