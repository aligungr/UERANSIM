cmake_minimum_required(VERSION 3.17)

file(GLOB HDR_FILES *.h)
file(GLOB SRC_FILES *.c)

add_library(asn-ngap ${HDR_FILES} ${SRC_FILES})
target_link_libraries(asn-ngap asn-asn1c)
target_include_directories(asn-ngap PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
