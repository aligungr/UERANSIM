cmake_minimum_required(VERSION 3.17)

file(GLOB_RECURSE HDR_FILES *.hpp)
file(GLOB_RECURSE SRC_FILES *.cpp)

add_library(common-lib ${HDR_FILES} ${SRC_FILES})

target_compile_options(common-lib PRIVATE -Wall -Wextra -pedantic -Wno-unused-parameter)

target_link_libraries(common-lib utils)
target_link_libraries(common-lib asn-ngap)
target_link_libraries(common-lib asn-rrc)

target_link_libraries(common-lib sctp) # Linux SCTP
