package com.runsim.backend.otn;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.Arrays;

public class OtnArray extends OtnElement {
    private final ArrayList<OtnElement> elements;

    public OtnArray() {
        this.elements = new ArrayList<>();
    }

    public OtnArray(OtnElement... elements) {
        this.elements = new ArrayList<>();
        this.elements.addAll(Arrays.asList(elements));
    }

    public OtnArray add(OtnElement element) {
        elements.add(element);
        return this;
    }

    public OtnArray add(int element) {
        elements.add(new OtnNumber(element));
        return this;
    }

    public OtnArray add(float element) {
        elements.add(new OtnNumber(element));
        return this;
    }

    public OtnArray add(String element) {
        elements.add(new OtnString(element));
        return this;
    }

    public int size() {
        return elements.size();
    }

    public OtnElement get(int index) {
        return elements.get(index);
    }

    public void set(int index, OtnElement element) {
        elements.set(index, element);
    }

    @Override
    public JsonElement toJson() {
        JsonArray value = new JsonArray();
        for (OtnElement element : elements)
            value.add(element.toJson());

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive("array"));
        jsonObject.add("value", value);

        return jsonObject;
    }
}
