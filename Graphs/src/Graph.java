import java.util.*;

public class Graph<T> {
    private Set<Node<T>> nodes;

    public Set<Node<T>> getNodes(){
        if (nodes == null){
            nodes = new HashSet<>();
        }
        return nodes;
    }

    public void addNode(T value){
        getNodes().add(new Node<>(value));
    }

    public double getDistance(Node<T> start, Node<T> end){
        class NodeDist implements Comparable<NodeDist>{
            public double dist;
            public Node<T> node;

            public NodeDist(Node<T> node, double dist){
                this.node = node;
                this.dist = dist;
            }

            @Override
            public int compareTo(NodeDist o) {
                if (dist > o.dist){
                    return 1;
                }
                if (dist < o.dist){
                    return -1;
                }
                return 0;
            }
        }

        Queue<NodeDist> dists = new PriorityQueue<>();
        dists.add(new NodeDist(start, 0));
        while (dists.peek().node != end){
            NodeDist nd = dists.remove();
            for (NodeLink nl : nd.node.getLinks()){
                Node<T> n = nl.getTo();
                nodeInDists:
                {
                    NodeDist foundNode = null;
                    for (NodeDist nd2 : dists) {
                        if (nd2.node == n) {
                            foundNode = nd2;
                            break nodeInDists;
                        }
                    }
                    if (foundNode.dist > nl.getLength()+nd.dist){
                        dists.remove(foundNode);
                    }
                    else{
                        break;
                    }
                }
                dists.offer(new NodeDist(n, nl.getLength() + nd.dist));
            }
        }
        return dists.peek().dist;
    }
}
