file(GLOB HDR_FILES *.h)
file(GLOB SRC_FILES *.c)

add_library(asn-asn1c ${HDR_FILES} ${SRC_FILES})
target_include_directories(asn-asn1c PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})

target_compile_options(asn-asn1c PUBLIC -DASN_DISABLE_OER_SUPPORT)
