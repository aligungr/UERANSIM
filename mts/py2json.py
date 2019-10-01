import json
import binascii


def json_string_to_py(json_string):
    json_obj = json.loads(json_string)
    return json_obj_to_py(json_obj)


def json_obj_to_py(json_obj):
    assert type(json_obj) is dict, "invalid json"
    assert "type" in json_obj, "'type' field is required"
    assert "value" in json_obj, "'value' field is required"
    py_type = json_obj["type"]
    py_value = json_obj["value"]
    assert py_type is not None, "'type' field required"
    assert py_value is not None, "'value' field required"

    if py_type == "object":
        res = {}
        assert type(py_value) is dict, "value must be an object"
        for key in py_value:
            res[key] = json_obj_to_py(py_value[key])
    elif py_type == "array":
        res = []
        assert type(py_value) is list, "value must be an array"
        for item in py_value:
            res.append(json_obj_to_py(item))
    elif py_type == "number":
        if type(py_value) is int:
            res = py_value
        elif type(py_value) is float:
            res = py_value
        elif type(py_value) is str:
            try:
                return int(py_value)
            except ValueError:
                try:
                    return float(py_value)
                except ValueError:
                    assert False, "value must be a number"
        else:
            assert False, "value must be a number"
    elif py_type == "string":
        assert type(py_value) is str, "value must be a string"
        res = py_value
    elif py_type == "tuple":
        assert type(py_value) is list, "value must be an array"
        arr = []
        for item in py_value:
            arr.append(json_obj_to_py(item))
        res = tuple(arr)
    elif py_type == "null":
        res = None
    elif py_type == "base16":
        res = binascii.unhexlify(py_value)
    else:
        assert False, "invalid type: " + py_type
    return res


def py_to_json_string(obj):
    return json.dumps(py_to_json_object(obj))


def py_to_json_object(obj):
    if type(obj) is None:
        return {"type": "null"}
    if type(obj) is str:
        return {"type": "string", "value": obj}
    if type(obj) is int:
        return {"type": "number", "value": obj}
    if type(obj) is float:
        return {"type": "number", "value": obj}
    if type(obj) is list:
        val = []
        for item in obj:
            val.append(py_to_json_object(item))
        return {"type": "array", "value": val}
    if type(obj) is tuple:
        val = []
        for item in obj:
            val.append(py_to_json_object(item))
        return {"type": "tuple", "value": val}
    if type(obj) is dict:
        val = {}
        for key in obj:
            val[key] = py_to_json_object(obj[key])
        return {"type": "object", "value": val}
    if type(obj) is bytes:
        return {"type": "base16", "value": binascii.hexlify(obj).hex()}
    assert False, "invalid type: " + str(type(obj))
