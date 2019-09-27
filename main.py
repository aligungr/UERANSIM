import asn2json
import json

asn2json.test()

type_name = input("Enter type: ")
asn_type = asn2json.get_type_by_name(type_name)
compiled = asn2json.compile_element(asn_type, None)

print(json.dumps(compiled))
