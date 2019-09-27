import ngap
import json
from pycrate_asn1rt.dictobj import *
from pycrate_asn1rt.asnobj_construct import *
from pycrate_asn1rt.asnobj_basic import *
from pycrate_asn1rt.asnobj_ext import *
from pycrate_asn1rt.asnobj_class import *


########## # todo: map'e eklerken zaten var mı diye bak eğer zaten varsa direk dön
########## # ama var olan yenisinden farklıysa hata var demektrir.
########## MAP = {}
##########
##########
########## def make_ref_id_int(element):
##########     assert element._tr._typeref.called[0]
##########     assert element._tr._typeref.called[1]
##########     res = "$" + element._tr._typeref.called[0] + "." + element._tr._typeref.called[1]
##########     if len(element._tr._typeref.ced_path) > 0:
##########         res += element._tr._typeref.ced_path[0]
##########     return res
##########
##########
########## def make_ref_id_enum(element):
##########     assert element._tr._typeref.called[0]
##########     assert element._tr._typeref.called[1]
##########     res = "$" + element._tr._typeref.called[0] + "." + element._tr._typeref.called[1]
##########     if len(element._tr._typeref.ced_path) > 0:
##########         res += element._tr._typeref.ced_path[0]
##########     return res
##########
##########
########## def make_ref_id_sequence(element):
##########     if not element._tr:
##########         return "$" + element._mod + "." + element._name
##########     else:
##########         assert element._typeref.called[0]
##########         assert element._typeref.called[1]
##########         res = "$" + element._typeref.called[0] + "." + element._typeref.called[1]
##########         if len(element._typeref.ced_path) > 0:
##########             res += element._typeref.ced_path[0]
##########         return res
##########
##########
########## def make_ref_id_sequence_of(element):
##########     if not element._tr:
##########         return "$" + element._mod + "." + element._name
##########     else:
##########         assert element._typeref.called[0]
##########         assert element._typeref.called[1]
##########         res = "$" + element._typeref.called[0] + "." + element._typeref.called[1]
##########         if len(element._typeref.ced_path) > 0:
##########             res += element._typeref.ced_path[0]
##########         return res
##########
##########
########## def make_ref_id_open(element):
##########     assert element._tr
##########     assert element._typeref
##########     assert not element._tr._typeref
##########     assert element._typeref.called[0]
##########     assert element._typeref.called[1]
##########     res = "$" + element._typeref.called[0] + "." + element._typeref.called[1]
##########     if len(element._typeref.ced_path) > 0:
##########         res += "&" + element._typeref.ced_path[0]
##########     return res
##########
##########
########## def make_ref_id_class(element):
##########     assert element._tr
##########     assert element._typeref
##########     assert not element._tr._typeref
##########     assert element._typeref.called[0]
##########     assert element._typeref.called[1]
##########     res = "$" + element._typeref.called[0] + "." + element._typeref.called[1]
##########     if len(element._typeref.ced_path) > 0:
##########         res += "&" + element._typeref.ced_path[0]
##########     return res
##########
##########
########## def make_constraints(constraint_list):
##########     res = []
##########     for item in constraint_list:
##########         if type(item) is ASN1RangeInt:
##########             res.append({
##########                 "constraint": "range-int",
##########                 "lower-bound": item.lb,
##########                 "upper-bound": item.ub
##########             })
##########         else:
##########             raise RuntimeError("not implemented yet")
##########     return res
##########
##########
########## def translate_element(element):
##########     if type(element) is SEQ:
##########         return translate_sequence(element)
##########     elif type(element) is SEQ_OF:
##########         return translate_sequence_of(element)
##########     elif type(element) is INT:
##########         return translate_int(element)
##########     elif type(element) is ENUM:
##########         return translate_enum(element)
##########     elif type(element) is OPEN:
##########         return translate_open(element)
##########     else:
##########         raise RuntimeError("not implemented yet")
##########
##########
########## def translate_sequence(element):
##########     assert not element._const_tab
##########     assert not element._const_val
##########     ref_id = make_ref_id_sequence(element)
##########     if type(element._cont) is ASN1Dict:
##########         obj = {
##########             "type": "sequence",
##########             "properties": {}
##########         }
##########         for item in element._cont:
##########             if type(item) is str:
##########                 obj["properties"][item] = translate_element(element._cont[item])
##########             else:
##########                 raise RuntimeError("not implemented yet")
##########         MAP[ref_id] = obj
##########         return ref_id
##########     else:
##########         raise RuntimeError("not implemented yet")
##########
##########
########## def translate_sequence_of(element):
##########     assert not element._const_tab
##########     assert not element._const_val
##########     ref_id = make_ref_id_sequence_of(element)
##########     obj = {
##########         "type": "sequence_of",
##########         "items": translate_element(element._cont)
##########     }
##########     MAP[ref_id] = obj
##########     return ref_id
##########
##########
########## def translate_int(element):
##########     ref_id = make_ref_id_int(element)
##########     obj = {
##########         "type": "integer",
##########         "constraints": make_constraints(element._tr._const_val.root)
##########     }
##########     MAP[ref_id] = obj
##########     return ref_id
##########
##########
########## def translate_enum(element):
##########     ref_id = make_ref_id_enum(element)
##########     obj = {
##########         "type": "enum",
##########         "values": {}
##########     }
##########     for item in element._cont:
##########         obj["values"][str(item)] = element._cont[item]
##########     MAP[ref_id] = obj
##########     return ref_id
##########
##########
########## def translate_open(element):
##########     ref_id = make_ref_id_open(element)
##########     assert element._const_tab
##########     assert type(element._const_tab) is CLASS
##########     obj = {
##########         "type": "open-type",
##########         "class": translate_class(element._const_tab)
##########     }
##########     MAP[ref_id] = obj
##########     return ref_id
##########
##########
########## def translate_class(element):
##########     ref_id = make_ref_id_class(element)
##########     return 4
##########
##########
########## print(ngap.NGAP_PDU_Contents.InitialUEMessage.get_proto(w_open=True))
########## print("----------")
##########
########## translate_element(ngap.NGAP_PDU_Contents.InitialUEMessage)
##########
########## for key in MAP:
##########     print(key)
##########     print(json.dumps(MAP[key]))
##########


def compile_element(element):
    if type(element) is SEQ:
        return compile_sequence(element)
    if type(element) is SEQ_OF:
        return compile_sequence_of(element)
    if type(element) is INT:
        return compile_int(element)
    if type(element) is ENUM:
        return compile_enum(element)
    if type(element) is OPEN:
        return compile_open(element)
    if type(element) is OCT_STR:
        return compile_octet_string(element)
    if type(element) is CHOICE:
        return compile_choice(element)
    if type(element) is BIT_STR:
        return compile_bit_string(element)
    if type(element) is STR_PRINT:
        return compile_printible_string(element)
    assert False, "type not implemented"


def compile_sequence(element):
    assert not element._const_tab
    assert not element._const_val
    assert type(element._cont) is ASN1Dict
    assert not element._ext or len(element._ext) == 0
    obj = {
        "type": "sequence",
        "properties": {},
        "mandatory": [],
        "optional": []
    }
    for item in element._cont._dict:
        assert type(item) is str
        value = compile_element(element._cont._dict[item])
        obj["properties"][item] = value
    for item in element._root_mand:
        assert type(item) is str
        obj["mandatory"].append(item)
    for item in element._root_opt:
        assert type(item) is str
        obj["optional"].append(item)
    return obj


def compile_sequence_of(element):
    assert not element._const_tab
    assert not element._const_val
    assert not element._root
    assert not element._ext
    obj = {
        "type": "sequence-of",
        "items": compile_element(element._cont)
    }
    if element._const_sz:  # size constraint is only defined for some types
        obj["max-length"] = element._const_sz.ra
    return obj


def compile_int(element):
    obj = {
        "type": "integer"
    }
    if element._const_val:
        lb = element._const_val.lb
        ub = element._const_val.ub
        obj["min"] = lb
        obj["max"] = ub
    if element._const_tab:
        obj["possible-values"] = set()
        lut = element._const_tab._lut
        assert lut and type(lut) is dict
        tab_id = element._const_tab_id
        assert type(tab_id) is str
        for item in lut:
            if item == "__key__":
                continue
            value = lut[item]
            assert type(value) is tuple and len(value) == 2
            assert type(value[0]) is str and type(value[1]) is dict
            i = value[1][tab_id]
            assert type(i) == int
            obj["possible-values"].add(i)
        obj["possible-values"] = list(obj["possible-values"])
        if len(obj["possible-values"]) == 0:
            del obj["possible-values"]
    return obj


def compile_enum(element):
    assert not element._const_val
    obj = {
        "type": "enum",
        "values": {}
    }
    for item in element._cont_rev:
        key = element._cont_rev[item]
        obj["values"][key] = item
    if element._const_tab:
        obj["possible-values"] = set()
        lut = element._const_tab._lut
        assert lut and type(lut) is dict
        tab_id = element._const_tab_id
        for item in lut:
            if item == "__key__":
                continue
            value = lut[item]
            assert type(value) is tuple and len(value) == 2
            assert type(value[0]) is str and type(value[1]) is dict
            i = value[1][tab_id]
            assert type(i) == str
            obj["possible-values"].add(i)
        obj["possible-values"] = list(obj["possible-values"])
        if len(obj["possible-values"]) == 0:
            del obj["possible-values"]
    return obj


# possible values'la alkalı olarka genel bi veri kaybı sözkonusu olabilir
# içindeki enumlar intler için mesela constrianing valueslar var ama beraberkenki
# possible valueslar yok
# mesela (1, 'a'), (2, 'b') olanlar int için 1,2, char için 'a','b' olarak alınıyor
def compile_open(element):
    const_tab = element._const_tab
    tab_id = element._const_tab_id
    assert const_tab and type(const_tab) is CLASS
    assert tab_id and type(tab_id) == str
    assert not element._const_val
    obj = {
        "type": "open-type",
        "possible-values": []
    }
    lut = element._const_tab._lut
    assert lut and type(lut) is dict
    tab_id = element._const_tab_id
    for item in lut:
        if item == "__key__":
            continue
        value = lut[item]
        assert type(value) is tuple and len(value) == 2
        assert type(value[0]) is str and type(value[1]) is dict
        i = value[1][tab_id]
        obj["possible-values"].append(compile_element(i))
    if len(obj["possible-values"]) == 0:
        del obj["possible-values"]
    return obj


def compile_octet_string(element):
    assert not element._const_cont
    assert not element._const_tab
    assert not element._const_val
    obj = {
        "type": "octet-string"
    }
    if element._const_sz:
        obj["max-length"] = element._const_sz.ra
    return obj


def compile_bit_string(element):
    assert not element._const_cont
    assert not element._const_tab
    assert not element._const_val
    obj = {
        "type": "bit-string"
    }
    if element._const_sz:
        obj["max-length"] = element._const_sz.ra
    return obj


def compile_choice(element):
    assert not element._const_tab
    assert not element._const_val
    obj = {
        "type": "choice",
        "values": {}
    }
    assert type(element._cont) is ASN1Dict
    for item in element._cont._dict:
        assert type(item) is str
        obj["values"][item] = compile_element(element._cont._dict[item])
    return obj


def compile_printible_string(element):
    assert not element._const_tab
    assert not element._const_val
    obj = {
        "type": "bit-string"
    }
    if element._const_sz:
        obj["max-length"] = element._const_sz.ra
    return obj


print(json.dumps(compile_element(ngap.NGAP_PDU_Contents.NGSetupRequest)))
