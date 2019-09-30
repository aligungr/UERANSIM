import json


def json_to_py(json_string):
    json_obj = json.loads(json_string)
    return _json_to_py(json_obj)


def _json_to_py(json_obj):
    assert type(json_obj) is dict, "invalid json"
    py_type = json_obj["type"]
    py_value = json_obj["value"]
    assert py_type is not None, "type required"
    assert py_value is not None, "value required"

    if py_type == "object":
        res = {}
        assert type(py_value) is dict, "value must be an object"
        for key in py_value:
            res[key] = _json_to_py(py_value[key])
    elif py_type == "array":
        res = []
        assert type(py_value) is list, "value must be an array"
        for item in py_value:
            res.append(_json_to_py(item))
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
            arr.append(_json_to_py(item))
        res = tuple(arr)
    elif py_type == "null":
        res = None
    else:
        assert False, "invalid type: " + py_type
    return res


def py_to_json(obj):
    return json.dumps(_py_to_json(obj))


def _py_to_json(obj):
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
            val.append(_py_to_json(item))
        return {"type": "array", "value": val}
    if type(obj) is tuple:
        val = []
        for item in obj:
            val.append(_py_to_json(item))
        return {"type": "tuple", "value": val}
    if type(obj) is dict:
        val = {}
        for key in obj:
            val[key] = _py_to_json(obj[key])
        return {"type": "object", "value": val}
