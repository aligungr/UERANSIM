import asn2json
import json

asn2json.test()

user_input = input("Enter type: ").split(".")
asn_type = asn2json.get_type_by_name(user_input[0], user_input[1])
compiled = asn2json.compile_element(asn_type, None)

print(json.dumps(compiled))


##### TODO: Bazı yerlerde type ve type_ref'in beraber bulunması uygun olmadı.
#### Mesela jsonda 71. satırda RAN-UE-NGAP-ID var. bunu type_Ref'e eklediğimzide mesela,
#### bunun integer olduğu ve min max değerlerinin ne olduğu zaten belli olması lazım
#### --tabi bu durmunun bilgi kaybına yol açıp açmadığına mutalak bakıklsın. eğer bilgi kaubı yoksa,
####   user defined olanlar için bu type yerne kullanılablir
