package rbtree;

/**
 * A node that contains a pointer to its parent, left child, and right child. 
 * Each node contain also contains a key.
 * @param <K> The data type of the key. K must extend from the Comparable 
 */
public interface Node<K extends Comparable<K>> {
 
    /**
     * Getter for the parent node
     * @return The node of the parent to this node.
     */
    public Node getParent();
    
    /**
     * Getter for the left child node.
     * @return The node to the left child for this node.
     */
    public Node getLeftChild();
    
    /**
     * Getter for the right child node.
     * @return The node to the right child of this node.
     */
    public Node getRightChild();

    
    /**
     * Getter for the key.
     * @return The key value of data type <K>
     */
    public K getKey();
    
}
