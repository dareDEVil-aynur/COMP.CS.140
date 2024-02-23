package fi.tuni.prog3.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayNode extends Node implements Iterable<Node> {
    private List<Node> elements;

    public ArrayNode() {
        this.elements = new ArrayList<>();
    }

    public void add(Node node) {
        elements.add(node);
    }

    public int size() {
        return elements.size();
    }

    @Override
    public Iterator<Node> iterator() {
        return elements.iterator();
    }
}
