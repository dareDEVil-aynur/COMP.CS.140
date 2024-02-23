package fi.tuni.prog3.json;

import java.util.*;

public class ObjectNode extends Node implements Iterable<String> {
    private Map<String, Node> keyValuePairs;

    public ObjectNode() {
        this.keyValuePairs = new TreeMap<>();
    }

    public Node get(String key) {
        return keyValuePairs.get(key);
    }

    public void set(String key, Node node) {
        keyValuePairs.put(key, node);
    }

    public int size() {
        return keyValuePairs.size();
    }

    @Override
    public Iterator<String> iterator() {
        return keyValuePairs.keySet().iterator();
    }
}
