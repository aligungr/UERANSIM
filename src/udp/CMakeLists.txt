cmake_minimum_required(VERSION 3.17)

file(GLOB HDR_FILES *.hpp)
file(GLOB SRC_FILES *.cpp)

add_library(udp ${HDR_FILES} ${SRC_FILES})

target_compile_options(udp PRIVATE -Wall -Wextra -pedantic -Wno-unused-parameter)

target_link_libraries(udp utils)
target_link_libraries(udp sctp)

target_include_directories(udp PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
