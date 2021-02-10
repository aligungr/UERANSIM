cmake_minimum_required(VERSION 3.17)

file(GLOB_RECURSE HDR_FILES *.hpp)
file(GLOB_RECURSE SRC_FILES *.cpp)

add_library(gnb ${HDR_FILES} ${SRC_FILES})

target_compile_options(gnb PRIVATE -Wall -Wextra -pedantic -Wno-unused-parameter)

target_link_libraries(gnb utils)
target_link_libraries(gnb sctp-cxx)
target_link_libraries(gnb asn-ngap)
target_link_libraries(gnb asn-rrc)
target_link_libraries(gnb asn-utils)
target_link_libraries(gnb rrc)
target_link_libraries(gnb udp)
target_link_libraries(gnb gtp)
target_link_libraries(gnb app)
target_link_libraries(gnb urs)


