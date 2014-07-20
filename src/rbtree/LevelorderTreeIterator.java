/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbtree;

import java.util.ArrayDeque;

/**
 * An iterator that traverses a tree level order
 *
 * @author Danny
 */
public class LevelorderTreeIterator implements TreeIterator {

    private ArrayDeque<Node> traversalStack;

    /**
     * Constructor with one perimeter
     *
     * @param root the root node of the tree
     */
    public LevelorderTreeIterator(Node root) {
        traversalStack = new ArrayDeque<Node>();
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
        return !traversalStack.isEmpty();
    }

    /**
     * Retrieves the next node in the level order traversal
     *
     * @return The next node in the level order traversal, or if there are no
     * more nodes to traverse to, it returns null
     */
    @Override
    public Node next() {
        if (hasMore()) {
            Node returnNode = traversalStack.pollFirst();
            if (returnNode.getLeftChild().getKey() != null) {
                traversalStack.offerLast(returnNode.getLeftChild());
            }
            if (returnNode.getRightChild().getKey() != null) {
                traversalStack.offerLast(returnNode.getRightChild());
            }
            return returnNode;
        }
        return null;
    }
}
