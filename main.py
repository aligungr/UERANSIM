import asn2json
import json

asn2json.test()

user_input = input("Enter type: ").split(".")
asn_type = asn2json.get_type_by_name(user_input[0], user_input[1])
compiled = asn2json.compile_element(asn_type, None)

print(json.dumps(compiled))
