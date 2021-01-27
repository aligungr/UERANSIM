cmake_minimum_required(VERSION 3.17)

file(GLOB HDR_FILES *.hpp)
file(GLOB SRC_FILES *.cpp)

add_library(rlc ${HDR_FILES} ${SRC_FILES})

target_compile_options(rlc PRIVATE -Wall -Wextra -pedantic -Wno-unused-parameter)

target_link_libraries(rlc utils)
target_link_libraries(rlc sctp)

target_include_directories(rlc PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
