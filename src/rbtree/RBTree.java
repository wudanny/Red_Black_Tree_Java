
package rbtree;

/**
 * Red Black Tree that implements insert, delete, traversal, minimum, maximum, search, predecessor,
 * and successor.
 * @author Danny
 * @param <K> the data type of the key for the nodes in the red black tree
 * @param <V> the data type of the data stored in each node in the red black tree
 */
public class RBTree<K extends Comparable<K>,V>{
    
    private RBNode<K,V>  root;
    private RBNode<K,V> nil = new RBNode(null,null, Color.Black);
    
    /**
     * Constructor of RBTree with one perimeter
     * @param tree the root of a pre-existing red black tree
     */
    public RBTree(RBNode<K,V> tree) {
        root = tree;
    }
    
    /**
     * A new empty red black tree 
     */
    public RBTree() {
        root = nil;
    }
    
    /**
     * Searches the red black tree for node with the key <K> key. 
     * @param key The key of the node you wish to search for.
     * @return The Node with key key, or if there is no node with identical key, 
     * null is returned
     */
    public RBNode<K,V> search(K key) {
        RBNode<K,V> traverse = root;
        
        while (traverse.getKey() != null) {
            //if the traverse has identical key, return the node
            if (key.compareTo(traverse.getKey()) == 0) {
                return traverse;
            } else {
                //else go to either the left child, if < key, or right child, if > key.
                if (key.compareTo(traverse.getKey()) < 0) {
                    traverse = traverse.getLeftChild();
                } else {
                    traverse = traverse.getRightChild();
                }
            }
        }
        //no node with key matches
        return null;
        
    }
    
    /**
     * Find the node with the minimum key at sub-tree with root of tree
     * @param tree The root node of the tree you wish to find the minimum of
     * @return the Node with the minimum key in the sub-tree
     */
    public RBNode<K,V> minimum(RBNode<K,V> tree) {
        RBNode traverse = tree;
        while (traverse.getLeftChild().getKey() != null) {
            traverse = traverse.getLeftChild();
        }
        return traverse;
    }
    
    /**
     * Find the node with the maximum key at sub-tree with root of tree
     * @param tree The root node of the tree you wish to find the maximum of
     * @return the Node with the maximum key in the sub-tree
     */
    public RBNode<K,V> maximum(RBNode<K,V> tree) {
        RBNode traverse = tree;
        while (traverse.getRightChild().getKey() != null) {
            traverse = traverse.getRightChild();
        }
        return traverse;
    }
    
    /**
     * Find the Node with the key that has the smallest key larger than the key of nodeToFind
     * @param nodeToFind The node you wish to find the successor of
     * @return The node with the key that has the smallest key larger than the key of nodeToFind,
     * or null if a successor does not exist
     */
    public RBNode<K,V> successor(RBNode<K,V> nodeToFind) {
        RBNode traverse = nodeToFind;
        if (traverse.getRightChild().getKey() != null) {
            //the node with the smallest key in the right sub-tree
            return minimum(traverse.getRightChild());
        } else {
            //traverse up the tree to find the successor
            RBNode parent = traverse.getParent();
            while (parent != null && !traverse.equals(parent.getLeftChild())) {
                traverse = parent;
                parent = traverse.getParent();
            }
            
            return parent;
            
        }
    }
    
    /**
     * Find the Node with the key that has the largest key smaller than the key of nodeToFind
     * @param nodeToFind The node you wish to find the predecessor of
     * @return The node with the key that has the largest key smaller than the key of nodeToFind,
     * or null if a predecessor does not exist
     */
    public RBNode<K,V> predecessor(RBNode<K,V> nodeToFind) {
        RBNode traverse = nodeToFind;
        if (traverse.getLeftChild().getKey() != null) {
            //the node with the largest key in the left sub-tree
            return maximum(traverse.getLeftChild());
        } else {
            //traverse up the tree to find the predecessor
            RBNode parent = traverse.getParent();
            while (parent != null && !traverse.equals(parent.getRightChild())) {
                traverse = parent;
                parent = traverse.getParent();
            }
            
            return parent;
            
        }
    }
    
    /**
     * Performs a left rotation on a sub-tree
     * @param rotatePoint The node you wish to do a left rotation on
     */
    private void rotateLeft(RBNode<K,V> rotatePoint) {
        RBNode rightChild = rotatePoint.getRightChild();
        RBNode grandParent = rotatePoint.getParent();
        //rightChild has parents of grandparent
        rightChild.setParent(grandParent);
        //set either left of right child of grandParent to rightChild or if grandParent is null, right child is root
        if (grandParent == null) {
            root = rightChild;
        } else {
            if (grandParent.getLeftChild().equals(rotatePoint)) {
                grandParent.setLeftChild(rightChild);
            } else {
                grandParent.setRightChild(rightChild);
            }
        }
        //fix the left child of rightChild to the right child of rotatePoint
        rotatePoint.setRightChild(rightChild.getLeftChild());
        rightChild.getLeftChild().setParent(rotatePoint);
        //fix parent of rotatePoint and left child of rightChild
        rightChild.setLeftChild(rotatePoint);
        rotatePoint.setParent(rightChild);
        
    }
    
    /**
     * Performs a right rotation on a sub-tree
     * @param rotatePoint The node you wish to do a right rotation on
     */
    private void rotateRight(RBNode<K,V> rotatePoint) {
        RBNode leftChild = rotatePoint.getLeftChild();
        RBNode grandParent = rotatePoint.getParent();
        //leftChild has parents of grandparent
        leftChild.setParent(grandParent);
        //set either left of right child of grandParent to leftChild or if grandParent is null, leftChild is root
        if (grandParent == null) {
            root = leftChild;
        } else {
            if (grandParent.getLeftChild().equals(rotatePoint)) {
                grandParent.setLeftChild(leftChild);
            } else {
                grandParent.setRightChild(leftChild);
            }
        }
        //fix the left child of leftChild to the right child of rotatePoint
        rotatePoint.setLeftChild(leftChild.getRightChild());
        leftChild.getRightChild().setParent(rotatePoint);
        //fix parent of rotatePoint and left child of leftChild
        leftChild.setRightChild(rotatePoint);
        rotatePoint.setParent(leftChild);
    }
    
    /**
     * Insert a node with a key of value key and data with the value value.
     * @param key The key of the new node
     * @param value The data the new node contains
     * @return If the insertion was successful, it returns true. If the insertion was unsuccessful because 
     * a node with key identical to key already exist in the tree, it returns false.
     */
    public boolean insertNode(K key, V value){
        if(this.search(key) == null){
            this.insert(new RBNode<K,V>(key,value));
            return true;
        }
        return false;
    }
    
    /**
     * Insert new node into the tree, find location to insert the new node into and 
     * re-balance the tree
     * @param newNode the new node to be inserted into the tree
     */
    private void insert(RBNode<K,V> newNode) {
        RBNode<K,V> x = root;
        RBNode<K,V> y = nil;
        //find the locations to insert the new node
        while (x.getKey() != null) {
            y = x;
            if (newNode.getKey().compareTo(x.getKey()) < 0) {
                x = x.getLeftChild();
            } else {
                x = x.getRightChild();
            }
        }
        //if the tree is empty, set root to newNode
        if (y.getKey() == null) {
            root = newNode;
        } else {
            //else set the parents of the newNode and either newNode is the left or right child
            newNode.setParent(y);
            if (newNode.getKey().compareTo(y.getKey())<0) {
                y.setLeftChild(newNode);
            } else {
                y.setRightChild(newNode);
            }
        }
        
        //set newNode color to red and its children to nil
        newNode.setColor(Color.Red);
        newNode.setLeftChild(nil);
        newNode.setRightChild(nil);
        
        //re-balance the tree
        this.insertFixUp(newNode);
    }
    
    /**
     * Balance the tree after a new insertion
     * @param insertedNode The node that was inserted
     */
    private void insertFixUp(RBNode<K,V> insertedNode) {
        RBNode uncle, grand, parent = insertedNode.getParent();
        while (parent != null && parent.getColor() == Color.Red) {
            grand = parent.getParent();
            //if the parent of the insertedNode is the left child
            if (parent.equals(grand.getLeftChild())) {
                uncle = grand.getRightChild();
                if (uncle.getColor() == Color.Red) {
                    //if the uncle of insertedNode is red
                    parent.setColor(Color.Black);
                    uncle.setColor(Color.Black);
                    grand.setColor(Color.Red);
                    insertedNode = grand;
                    parent = insertedNode.getParent();
                } else {
                    //if the uncle is black
                    if (insertedNode.equals(parent.getRightChild())) {
                        //if insertedNode is the right child, make it a left child
                        insertedNode = parent;
                        rotateLeft(insertedNode);
                        parent = insertedNode.getParent();
                    }
                    //if insertedNode is the left child
                    parent.setColor(Color.Black);
                    grand.setColor(Color.Red);
                    rotateRight(grand);
                }
            } else {
                //if the parent of insertedNode is the right child
                uncle = grand.getLeftChild();
                if (uncle.getColor() == Color.Red) {
                    //if the uncle of insertedNode is red
                    parent.setColor(Color.Black);
                    uncle.setColor(Color.Black);
                    grand.setColor(Color.Red);
                    insertedNode = grand;
                    parent = insertedNode.getParent();
                } else {
                     //if the uncle is black
                    if (insertedNode.equals(parent.getLeftChild())) {
                         //if insertedNode is the left child, make it a right child
                        insertedNode = parent;
                        rotateRight(insertedNode);
                        parent = insertedNode.getParent();
                    }
                     //if insertedNode is the right child
                    parent.setColor(Color.Black);
                    grand.setColor(Color.Red);
                    rotateLeft(grand);
                }
            }
        }
        
        root.setColor(Color.Black);
    }
    
    /**
     * Delete a node from the red black tree with the key key
     * @param key the key that is contained in the node you wish to delete from in the tree
     * @return if the deletion is successful, it returns true. If the deletion is unsuccessful because
     * no node exist with the key key, it returns false.
     */
    public boolean deleteNode(K key){
        RBNode<K,V> returnNode=this.search(key);
        if(returnNode == null){
            return false;
        }
         this.delete(returnNode);
         return true;
    }
    
    /**
     * Deletes a node from the tree and re-balances the tree
     * @param remove the node you wish to remove from the tree
     */
    private void delete(RBNode<K,V> remove) {
        RBNode<K,V> x, y;
        //find the successor for the removed node
        if (remove.getLeftChild().getKey() == null || remove.getRightChild().getKey() == null) {
            y = remove;
        } else {
            y = this.successor(remove);
        }
        
        //if node y is removed, we need to fix the child of y. Retreieve the only child of y
        if (y.getLeftChild().getKey() != null) {
            x = y.getLeftChild();
        } else {
            x = y.getRightChild();
        }
        
        //set the parents of x even if its a nil
        x.setParent(y.getParent());
        
        if (y.equals(root)) {
            //if y is the root, set x to be the new root
            root = x;
        } else {
            //set x as the left or right child of y's parent
            if (y.equals(y.getParent().getLeftChild())) {
                y.getParent().setLeftChild(x);
            } else {
                y.getParent().setRightChild(x);
            }
        }
        
        //remove the remove node if y, the successor of remove node, is not the remove node itself
        if (!y.equals(remove)) {
            remove.setKey(y.getKey());
        }
        //if y is black, re-balance the tree
        if (y.getColor() == Color.Black) {
            deleteFixUp(x);
        }
        
    }
    
    /**
     * Re-balance the tree after a deletion
     * @param childOfRemovedNode The node child of the node removed
     */
    private void deleteFixUp(RBNode<K,V> childOfRemovedNode) {
        RBNode parent, sibling;
        while (!childOfRemovedNode.equals(root) && childOfRemovedNode.getColor() == Color.Black) {
            parent = childOfRemovedNode.getParent();
            if (childOfRemovedNode.equals(parent.getLeftChild())) {
                //if childOfRemovedNode is the left child
                sibling = parent.getRightChild();
                if (sibling.getColor() == Color.Red) {
                    //if the sibling is red
                    parent.setColor(Color.Red);
                    sibling.setColor(Color.Black);
                    rotateLeft(parent);
                    sibling = parent.getRightChild();
                }
                
                //if the sibling is black
                if (sibling.getLeftChild().getColor() == Color.Black && sibling.getRightChild().getColor() == Color.Black) {
                    //if the sibling has 2 black children
                    sibling.setColor(Color.Red);
                    childOfRemovedNode = parent;
                } else {
                    if (sibling.getRightChild().getColor() == Color.Black) {
                        //if the sibling have a red left child and a black right child, make the right child red
                        sibling.setColor(Color.Red);
                        sibling.getLeftChild().setColor(Color.Black);
                        rotateRight(sibling);
                        sibling = sibling.getParent();
                    }
                    
                    //if the sibling have a red right child
                    sibling.setColor(parent.getColor());
                    parent.setColor(Color.Black);
                    sibling.getRightChild().setColor(Color.Black);
                    rotateLeft(parent);
                    childOfRemovedNode = root;
                }
                
            } else {
                //if childOfRemovedNode is the right child
                  sibling = parent.getLeftChild();
                if (sibling.getColor() == Color.Red) {
                    //if the sibling is red
                    parent.setColor(Color.Red);
                    sibling.setColor(Color.Black);
                    rotateRight(parent);
                    sibling = parent.getLeftChild();
                }
                
                //if the sibling is black
                if (sibling.getLeftChild().getColor() == Color.Black && sibling.getRightChild().getColor() == Color.Black) {
                    //if sibling has 2 black children
                    sibling.setColor(Color.Red);
                    childOfRemovedNode = parent;
                } else {
                    if (sibling.getLeftChild().getColor() == Color.Black) {
                        //if sibling has a black left child and red right child, make it so that sibling has a red left child
                        sibling.setColor(Color.Red);
                        sibling.getRightChild().setColor(Color.Black);
                        rotateLeft(sibling);
                        sibling = sibling.getParent();
                    }
                    //if sibling has a red left child
                    sibling.setColor(parent.getColor());
                    parent.setColor(Color.Black);
                    sibling.getLeftChild().setColor(Color.Black);
                    rotateRight(parent);
                    childOfRemovedNode = root;
                }
            }
            
            childOfRemovedNode.setColor(Color.Black);
        }
        
    }
    
    /**
     * Returns a iterator that performs an inorder traversal
     * @return InorderTreeIterator object that will perform an in order traversal
     */
    public InorderTreeIterator inorderTraverse(){
        return new InorderTreeIterator(root);
    }
    
    /**
     * Returns a iterator that performs an post order traversal
     * @return PostorderTreeIterator object that will perform an post order traversal
     */
    public PostorderTreeIterator postorderTraverse(){
        return new PostorderTreeIterator(root);
    }
    
    /**
     * Returns a iterator that performs an pre order traversal
     * @return PreorderTreeIterator object that will perform an pre order traversal
     */
    public PreorderTreeIterator preorderTraverse(){
        return new PreorderTreeIterator(root);
    }
    
    /**
     * Returns a iterator that performs an level order traversal
     * @return LevelorderTreeIterator object that will perform an level order traversal
     */
    public LevelorderTreeIterator levelorderTraverse(){
        return new LevelorderTreeIterator(root);
    }
}
