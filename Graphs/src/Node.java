import java.util.HashSet;
import java.util.Set;

public class Node<T>{
    private T value;
    private Set<NodeLink> nodeLinks;

    public Node(T value){
        this.value = value;
    }

    public Set<NodeLink> getLinks(){
        if (nodeLinks == null){
            nodeLinks = new HashSet<>();
        }
        return nodeLinks;
    }

    public void linkTo(Node<T> n){
        getLinks().add(new NodeLink(this, n));
    }

    public void doubleLink(Node<T> n){
        this.linkTo(n);
        n.getLinks().add(new NodeLink(n, this));
    }

    public Set<Node<T>> getNeighbors(){
        Set<Node<T>> nodes = new HashSet<>();
        for (NodeLink nl : getLinks()){
            nodes.add(nl.getTo());
        }
        return nodes;
    }
}
