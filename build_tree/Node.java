package build_tree;

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Node 
            && ((Node) obj).data == this.data
            && (((Node) obj).left == null && this.left == null) || ((Node) obj).left.equals(this.left) 
            && (((Node) obj).right == null && this.right == null) || ((Node) obj).right.equals(this.right);
    }
}