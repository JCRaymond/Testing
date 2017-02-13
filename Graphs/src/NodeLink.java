public class NodeLink {
    private Node from;
    private Node to;
    private double len;

    public NodeLink(Node from, Node to, int len){
        this.from = from;
        this.to = to;
        this.len = len;
    }

    public NodeLink(Node from, Node to){
        this(from, to, 1);
    }

    public Node getStart(){
        return from;
    }

    public Node getTo(){
        return to;
    }

    public double getLength(){
        return len;
    }
}
