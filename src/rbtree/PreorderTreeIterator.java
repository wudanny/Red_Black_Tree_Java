/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbtree;

import java.util.Stack;

/**
 * An iterator that traverses a tree pre order
 *
 * @author Danny
 */
public class PreorderTreeIterator implements TreeIterator {

    private Stack<Node> traversalStack;

    /**
     * Constructor with one perimeter
     *
     * @param root the root node of the tree
     */
    public PreorderTreeIterator(Node root) {
        traversalStack = new Stack<Node>();
        traversalStack.push(root);
    }

    /**
     * Checks if there are any more nodes to traverse to
     *
     * @return Returns true if there are more nodes to traverse to. Returns
     * false if there are no more nodes to traverse to
     */
    @Override
    public boolean hasMore() {
        return !traversalStack.empty();
    }

    /**
     * Retrieves the next node in the pre order traversal
     *
     * @return The next node in the pre order traversal, or if there are no more
     * nodes to traverse to, it returns null
     */
    @Override
    public Node next() {
        if (hasMore()) {
            Node returnNode = traversalStack.pop();
            if (returnNode.getRightChild().getKey() != null) {
                traversalStack.push(returnNode.getRightChild());
            }
            if (returnNode.getLeftChild().getKey() != null) {
                traversalStack.push(returnNode.getLeftChild());
            }
            return returnNode;
        }
        return null;
    }
}
