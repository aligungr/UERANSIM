cmake_minimum_required(VERSION 3.17)

file(GLOB_RECURSE EXT_C_HDR_FILES *.h)
file(GLOB_RECURSE EXT_CXX_HDR_FILES *.hpp)
file(GLOB_RECURSE EXT_C_SRC_FILES *.c)
file(GLOB_RECURSE EXT_CXX_SRC_FILES *.cpp)
file(GLOB HDR_FILES *.hpp)
file(GLOB SRC_FILES *.cpp)

add_library(crypt ${EXT_C_HDR_FILES} ${EXT_C_SRC_FILES} ${EXT_CXX_HDR_FILES} ${EXT_CXX_SRC_FILES} ${HDR_FILES} ${SRC_FILES})
target_include_directories(crypt PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})

target_compile_options(crypt PUBLIC -Wno-format-security)

target_link_libraries(crypt utils)
