package rbtree;

/**
 * A node that contains a pointer to its parent, left child, and right child. 
 * Each node contain a key and a value or data. This node is for a red-black tree,
 * so each node has a color of Color.Black or Color.Red
 * @param <K> The data type of the key. K must extend from the Comparable 
 * @param <V> The data type of the value or data.
 */
public class RBNode<K extends Comparable<K>, V> implements Node{
    private RBNode<K, V> parent,left,right;
    private K key;
    private V value;
    private Color color; 
    
    /**
     * Constructor for the Node class with three arguments.
     * @param key The key with data type of <K> of the node. Used to compare with keys of other nodes.
     * @param value The data with data type of <V>. 
     * @param color The color of the node. Can be either Color.Black or Color.Red
     */
    public RBNode(K key,V value, Color color){
        this.key=key;
        this.color=color;
        parent=null;
        left=null;
        right=null;
        this.value=value;
    }
    
    /**
     * Constructor for the Node class with two arguments.
     * @param key The key with data type of <K> of the node. Used to compare with keys of other nodes.
     * @param value The data with data type of <V>. 
     */
    public RBNode(K key,V value){
        this.key=key;
        this.color=null;
        parent=null;
        left=null;
        right=null;
        this.value=value;
    }
    
    /**
     * Setter for the parent node.
     * @param newParent The parent node for this node.
     */
    public void setParent(RBNode<K, V> newParent){
        this.parent=newParent;
    }
    
    /**
     * Setter for the left child.
     * @param newChild The left child node for this node.
     */
    public void setLeftChild(RBNode<K, V> newChild){
        this.left=newChild;
    }
    
    /**
     * Setter for the right child.
     * @param newChild The right child node for this node.
     */
    public void setRightChild(RBNode<K, V> newChild){
        this.right=newChild;
    }
    
    /**
     * Setter for the color
     * @param newColor One of the Color enum. The color of this node.
     */
    public void setColor(Color newColor){
        this.color=newColor;
    }
    
    /**
     * Setter for the key.
     * @param newKey The new key of type <K>.
     */
    public void setKey(K newKey){
        this.key=newKey;
    }
    
    /**
     * Setter for the value or data.
     * @param newValue The new value of type <V>
     */
    public void setValue(V newValue){
        this.value=newValue;
    }
    
    /**
     * Getter for the parent node
     * @return The node of the parent to this node.
     */
    public RBNode<K, V> getParent(){
        return this.parent;
    }
    
    /**
     * Getter for the left child node.
     * @return The node to the left child for this node.
     */
    public RBNode<K, V> getLeftChild(){
        return this.left;
    }
    
    /**
     * Getter for the right child node.
     * @return The node to the right child of this node.
     */
    public RBNode<K, V> getRightChild(){
        return this.right;
    }
    
    /**
     * Getter for the color.
     * @return The color of type Color.
     */
    public Color getColor(){
        return this.color;
    }
    
    /**
     * Getter for the key.
     * @return The key value of data type <K>
     */
    public K getKey(){
        return this.key;
    }
    
    /**
     * Getter for the value
     * @return The data value of data type <V>
     */
    public V getValue(){
        return this.value;
    }
}
