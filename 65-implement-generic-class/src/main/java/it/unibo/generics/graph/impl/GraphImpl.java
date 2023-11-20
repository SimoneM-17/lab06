package it.unibo.generics.graph.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {

    private Map<N, Set<N>> graph = new HashMap<>();

    public void addNode(N node) {
        this.graph.putIfAbsent(node, new HashSet<N>());
    }

    public void addEdge(N source, N target) {
        if (source != null && target != null && this.graph.containsKey(source)) {
            this.graph.get(source).add(target);
        }
    }

    public Set<N> nodeSet() {
        return new HashSet<>(graph.keySet());
    }

    public Set<N> linkedNodes(N node) {
        return new HashSet<>(this.graph.get(node));
    }

    public List<N> getPath(N source, N target) {
        List<N> path = new ArrayList<>();
        Queue<N> enqueuedNodes = new LinkedList<>();
        Set<N> visitedNodes = new HashSet<>();
        N currentNode;

        enqueuedNodes.add(source);
        while (!enqueuedNodes.isEmpty()) {
            currentNode = enqueuedNodes.poll();
            visitedNodes.add(currentNode);
            path.add(currentNode);
            if (currentNode.equals(target)) {
                return path;
            }
            for (N adjacentNode : this.graph.get(currentNode)) {
                if (!visitedNodes.contains(adjacentNode)) {
                    enqueuedNodes.add(adjacentNode);
                }
            }
        }
        return null;
    }
    
}
