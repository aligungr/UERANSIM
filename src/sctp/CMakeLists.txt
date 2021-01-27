cmake_minimum_required(VERSION 3.17)

file(GLOB HDR_FILES *.hpp)
file(GLOB SRC_FILES *.cpp)

add_library(sctp-cxx ${HDR_FILES} ${SRC_FILES})

target_compile_options(sctp-cxx PRIVATE -Wall -Wextra -pedantic -Wno-unused-parameter)

target_link_libraries(sctp-cxx utils)
target_link_libraries(sctp-cxx sctp)

target_include_directories(sctp-cxx PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
