package com.runsim.backend.otn;

import java.util.ArrayList;
import java.util.Arrays;

public class OtnTuple extends OtnElement {
    private final ArrayList<OtnElement> elements;

    public OtnTuple() {
        this.elements = new ArrayList<>();
    }

    public OtnTuple(OtnElement... elements) {
        this.elements = new ArrayList<>();
        this.elements.addAll(Arrays.asList(elements));
    }

    public OtnTuple add(OtnElement element) {
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
}
