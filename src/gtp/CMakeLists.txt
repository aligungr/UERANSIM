cmake_minimum_required(VERSION 3.17)

file(GLOB HDR_FILES *.hpp)
file(GLOB SRC_FILES *.cpp)

add_library(gtp ${HDR_FILES} ${SRC_FILES})

target_compile_options(gtp PRIVATE -Wall -Wextra -pedantic -Wno-unused-parameter)

target_link_libraries(gtp utils)

target_include_directories(gtp PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
