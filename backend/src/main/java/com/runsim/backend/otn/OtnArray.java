package com.runsim.backend.otn;

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
    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"type\": \"array\",\"value\":[");
        for (int i = 0; i < size(); i++) {
            sb.append(get(i).toJson());
            if (i != size() - 1)
                sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
