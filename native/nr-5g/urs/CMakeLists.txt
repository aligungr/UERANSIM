cmake_minimum_required(VERSION 3.17)

file(GLOB HDR_FILES *.hpp)
file(GLOB SRC_FILES *.cpp)

add_library(urs ${HDR_FILES} ${SRC_FILES})

target_compile_options(urs PRIVATE -Wall -Wextra -pedantic -Wno-unused-parameter)

target_link_libraries(urs utils)
target_link_libraries(urs udp)
target_link_libraries(urs logger)

target_include_directories(urs PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
