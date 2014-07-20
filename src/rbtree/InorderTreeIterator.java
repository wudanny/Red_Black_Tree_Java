/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbtree;

import java.util.Stack;

/**
 * An iterator that traverses a tree in order
 *
 * @author Danny
 */
public class InorderTreeIterator implements TreeIterator {

    private Stack<Node> traversalStack;

    /**
     * Constructor with one perimeter
     *
     * @param root the root node of the tree
     */
    public InorderTreeIterator(Node root) {
        traversalStack = new Stack<Node>();
        leftMostLeafPath(root);
    }

    /**
     * Pushes the nodes along the path to the left most leaf starting at root
     *
     * @param root the root of the tree
     */
    private void leftMostLeafPath(Node root) {
        while (root.getKey() != null) {
            traversalStack.push(root);
            root = root.getLeftChild();
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
     * Retrieves the next node in the in order traversal
     *
     * @return The next node in the in order traversal, or if there are no more
     * nodes to traverse to, it returns null
     */
    @Override
    public Node next() {
        if (hasMore()) {
            Node returnNode = traversalStack.pop();
            leftMostLeafPath(returnNode.getRightChild());
            return returnNode;
        }
        return null;
    }
}
