/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbtree;

import java.util.Stack;

/**
 * An iterator that traverses a tree post order
 *
 * @author Danny
 */
public class PostorderTreeIterator implements TreeIterator {

    private Stack<Node> traversalStack;

    /**
     * Constructor with one perimeter
     *
     * @param root the root node of the tree
     */
    public PostorderTreeIterator(Node root) {
        traversalStack = new Stack<Node>();
        leafPath(root);
    }

    /**
     * Pushes nodes along the path to the left most leaf into the stack
     *
     * @param root the root node of the tree
     */
    private void leafPath(Node root) {
        while (root.getKey() != null) {
            traversalStack.push(root);
            if (root.getLeftChild().getKey() != null) {
                root = root.getLeftChild();
            } else {
                root = root.getRightChild();
            }

        }
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
     * Retrieves the next node in the post order traversal
     *
     * @return The next node in the post order traversal, or if there are no
     * more nodes to traverse to, it returns null
     */
    @Override
    public Node next() {
        if (hasMore()) {
            Node returnNode = traversalStack.pop();
            if (hasMore()) {
                Node parentOfReturnNode = traversalStack.peek();
                if (returnNode.equals(parentOfReturnNode.getLeftChild())) {
                    leafPath(parentOfReturnNode.getRightChild());
                }
            }
            return returnNode;
        }
        return null;
    }
}
