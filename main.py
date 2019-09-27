import ngap
import json
from pycrate_asn1rt.dictobj import *
from pycrate_asn1rt.asnobj_construct import *
from pycrate_asn1rt.asnobj_basic import *
from pycrate_asn1rt.asnobj_ext import *
from pycrate_asn1rt.asnobj_class import *

# not: 'constraint belirten' asn1set'in içinde hep tek eleman var kabul edip ona göre yazdık.
# ilerde bir yerde sorun çıkarsa buna da bak

def compile_element(element, parent):
    if type(element) is SEQ:
        return compile_sequence(element, parent)
    if type(element) is SEQ_OF:
        return compile_sequence_of(element, parent)
    if type(element) is INT:
        return compile_int(element, parent)
    if type(element) is ENUM:
        return compile_enum(element, parent)
    if type(element) is OPEN:
        return compile_open(element, parent)
    if type(element) is OCT_STR:
        return compile_octet_string(element, parent)
    if type(element) is CHOICE:
        return compile_choice(element, parent)
    if type(element) is BIT_STR:
        return compile_bit_string(element, parent)
    if type(element) is STR_PRINT:
        return compile_printible_string(element, parent)
    if type(element) is OID:
        return compile_object_identifier(element, parent)
    if type(element) is CLASS:
        return compile_class(element, parent)
    assert False, "type not implemented"


def compile_sequence(element, parent):
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
    for item in element._root_mand:
        assert type(item) is str
        obj["mandatory"].append(item)
    for item in element._root_opt:
        assert type(item) is str
        obj["optional"].append(item)
    for item in element._cont._dict:
        assert type(item) is str
        value = compile_element(element._cont._dict[item], obj)
        obj["properties"][item] = value
    return obj


def compile_sequence_of(element, parent):
    assert not element._const_tab
    assert not element._const_val
    assert not element._root
    assert not element._ext
    obj = {
        "type": "sequence-of"
    }
    if element._const_sz:  # size constraint is only defined for some types
        assert type(element._const_sz.ra) is int
        obj["max-length"] = element._const_sz.ra

    obj["items"] = compile_element(element._cont, obj)
    return obj


def compile_int(element, parent):
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


def compile_enum(element, parent):
    assert not element._const_val
    obj = {
        "type": "enum",
        "values": {}
    }
    for item in element._cont_rev:
        key = element._cont_rev[item]
        obj["values"][key] = item
    if element._const_tab and element._const_tab._lut:
        obj["possible-values"] = set()
        lut = element._const_tab._lut
        assert type(lut) is dict
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


# not: possible values'la alkalı olarka genel bi veri kaybı sözkonusu olabilir
# içindeki enumlar intler için mesela constrianing valueslar var ama beraberkenki
# possible valueslar yok
# mesela (1, 'a'), (2, 'b') olanlar int için 1,2, char için 'a','b' olarak alınıyor
def compile_open(element, parent):
    assert not element._const_val
    obj = {
        "type": "open-type"
    }
    if element._const_tab:
        obj["possible-values"] = []
        const_tab = element._const_tab
        tab_id = element._const_tab_id
        assert const_tab and type(const_tab) is CLASS
        assert tab_id and type(tab_id) == str
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
            obj["possible-values"].append(compile_element(i, obj))
        if len(obj["possible-values"]) == 0:
            del obj["possible-values"]
    return obj


def compile_octet_string(element, parent):
    assert not element._const_tab
    assert not element._const_val
    obj = {
        "type": "octet-string"
    }
    if element._const_sz:
        assert type(element._const_sz.ra) is int
        obj["max-length"] = element._const_sz.ra
    if element._const_cont:
        assert not element._const_cont_enc
        obj["content"] = compile_element(element._const_cont, obj)
    return obj


def compile_bit_string(element, parent):
    assert not element._const_cont
    assert not element._const_tab
    assert not element._const_val
    obj = {
        "type": "bit-string"
    }
    if element._const_sz:
        assert type(element._const_sz.ra) is int
        obj["max-length"] = element._const_sz.ra
    return obj


def compile_choice(element, parent):
    # assert not element._const_tab --bazılarında varmış ama ignore edildi
    assert not element._const_val
    obj = {
        "type": "choice",
        "values": {}
    }
    assert type(element._cont) is ASN1Dict
    for item in element._cont._dict:
        assert type(item) is str
        obj["values"][item] = compile_element(element._cont._dict[item], obj)
    return obj


def compile_printible_string(element, parent):
    assert not element._const_tab
    assert not element._const_val
    obj = {
        "type": "bit-string"
    }
    if element._const_sz:
        assert type(element._const_sz.ra) is int
        obj["max-length"] = element._const_sz.ra
    return obj


def compile_object_identifier(element, parent):
    assert not element._const_tab
    assert not element._const_val
    obj = {
        "type": "object-identifier"
    }
    return obj


def compile_class(element, parent):
    assert not element._const_tab
    assert not element._const_val
    assert type(element._cont) is ASN1Dict
    obj = {
        "type": "class",
        "properties": {}
    }
    for item in element._cont._dict:
        assert type(item) is str
        obj["properties"][item] = compile_element(element._cont._dict[item], obj)
    return obj


modules = [
    ngap.NGAP_Constants,
    ngap.NGAP_CommonDataTypes,
    ngap.NGAP_Containers,
    ngap.NGAP_IEs,
    ngap.NGAP_PDU_Contents,
    ngap.NGAP_PDU_Descriptions
]


def test():
    for module in modules:
        items = getattr(module, "_all_")
        for item in items:
            print(item)


def get_type_by_name(name):
    for module in modules:
        items = getattr(module, "_obj_")
        for item in items:
            if item == name:
                return getattr(module, name)
    assert False, "type not found"


#print(json.dumps(compile_element(get_type_by_name(input("Enter type: ")))))
print(json.dumps(compile_element(get_type_by_name("InitialUEMessage"), None)))
