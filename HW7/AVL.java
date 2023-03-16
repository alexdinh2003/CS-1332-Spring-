import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL.
 *
 * @author HOANG GIA NGHI DINH
 * @version 1.0
 * @userid ndinh31 (i.e. gburdell3)
 * @GTID 903733512 (i.e. 900000000)
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class AVL<T extends Comparable<? super T>> {

    // Do not add new instance variables or modify existing ones.
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The data is null");
        }

        for (T elements : data) {
            if (elements == null) {
                throw new IllegalArgumentException("The data in the collection is null.");
            }
            add(elements);
        }
    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        // Time complexity O(log n)
        if (data == null) {
            throw new IllegalArgumentException("The data is null");
        }

        if (root == null) {
            size = 0;
        }
        root = rAdd(data, root);
    }

    /**
     * Private recursive method to add data
     * @param data The data to add to the tree
     * @param currNode The current node in the AVL being compared
     *
     * @return The current node that recursively passed in or pointer reinforcement
     */
    private AVLNode<T> rAdd(T data, AVLNode<T> currNode) {
        //a) If you reach a null node → add the data here
        //b) If you reach a match → do nothing (no duplicates)
        // or this → AVLNode<T> temp = new AVLNode<T>(data);
        //            return rotate(temp);
        if (currNode == null) {
            this.size++;
            return rotate(new AVLNode<>(data));
        } else if (currNode.getData().compareTo(data) < 0) {
            currNode.setRight(rAdd(data, currNode.getRight()));


        } else if (currNode.getData().compareTo(data) > 0) {
            currNode.setLeft(rAdd(data, currNode.getLeft()));
        }

        update(currNode);
        return getBFNode(currNode);
    }

    /**
     *  Method to handle the rotation
     *
     * @param currNode node root of problematic subtree
     * @return root of fixed subtree
     */
    private AVLNode<T> rotate(AVLNode<T> currNode) {
        //Right rotation BF is 2 at node and its left child is 0 or 1
        //Left rotation BF is -2 at node and its left child is 0 or 1
        if (currNode.getBalanceFactor() > 1) {
            if (currNode.getLeft() != null && currNode.getLeft().getBalanceFactor() < 0) {
                currNode.setLeft(leftRotation(currNode.getLeft()));
            }
            return rightRotation(currNode);
        } else if (currNode.getBalanceFactor() < -1) {
            if (currNode.getRight() != null && currNode.getRight().getBalanceFactor() > 0) {
                currNode.setRight(rightRotation(currNode.getRight()));
            }
            return leftRotation(currNode);
        }

        return currNode;
    }

    /**
     * Method to update node data (height and BF)
     * @param node node to be fixed
     */
    private void update(AVLNode<T> node) {
        if (node == null) {
            node.setHeight(-1);
        } else if (node.getLeft() == null && node.getRight() == null) {
            node.setHeight(0);
            node.setBalanceFactor(0);
        } else {
            node.setHeight(Math.max(getHeightNode(node.getLeft()), getHeightNode(node.getRight())) + 1);
            node.setBalanceFactor(getHeightNode(node.getLeft()) - getHeightNode(node.getRight()));
        }
    }

    /**
     * Left case implementation
     *
     * @param node1 root of problematic subtree
     * @return root of the fixed substree
     */
    private AVLNode<T> leftRotation(AVLNode<T> node1) {
        //AVLNode rotateLeft(AVLNode a) {
        //AVLNode b = a.getRight();
        //a.setRight(b.getLeft());
        //b.setLeft(a);
        //update(a); // a is now a child of b,
        //update(b); // updating b relies on updating a
        //return b; } for pointer reinforcement!
        // middleNode.getLeft() = left node

        AVLNode<T> middleNode = node1.getRight();

        node1.setRight(middleNode.getLeft());
        middleNode.setLeft(node1);

        update(node1);
        update(middleNode);
        return middleNode;
    }

    /**
     * Right case implementation
     *
     * @param node1 root of problematic subtree
     * @return root of the fixed substree
     */
    private AVLNode<T> rightRotation(AVLNode<T> node1) {
        //AVLNode rotateRight(AVLNode a){
        //AVLNode b = a.getLeft();
        //a.setLeft(b.getRight());
        //b.setRight(a);
        //update(a); // update H & BF of node
        //update(b);
        //return b; // for pointer reinforcement! }
        // middleNode.getRight() right node

        AVLNode<T> middleNode = node1.getLeft();

        node1.setLeft(middleNode.getRight());
        middleNode.setRight(node1);

        update(node1);
        update(middleNode);
        return middleNode;
    }

    /**
     * Get height for specific node
     *
     * @param node node with height in question
     * @return node's height
     */
    private int getHeightNode(AVLNode<T> node) {
        if (node == null) {
            return -1;
        } else if (node.getLeft() == null && node.getRight() == null) {
            return 0;
        } else {
            return node.getHeight();
        }
    }

    /**
     * Get balance factor for specific node
     *
     * @param node node with BF in question
     * @return node's BF
     */
    private AVLNode<T> getBFNode(AVLNode<T> node) {
        //leftChildHeight - rightChildHeight
        if (node.getBalanceFactor() > 1) {
            if (node.getLeft().getBalanceFactor() < 0) {
                node.setLeft(leftRotation(node.getLeft()));
            }
            node = rightRotation(node);
        } else if (node.getBalanceFactor() < -1) {
            if (node.getRight().getBalanceFactor() > 0) {
                node.setRight(rightRotation(node.getRight()));
            }
            node = leftRotation(node);
        }
        update(node);
        return node;
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data, NOT successor. As a reminder, rotations can occur
     * after removing the predecessor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("AVL cannot have the null data.");
        } else if (root == null) {
            throw new NoSuchElementException("AVL doesn't contain this data.");
        } else {
            // temp = dummyNode
            AVLNode<T> temp = new AVLNode<>(data);
            root = removeHelper(root, data, temp);
            return temp.getData();
        }
    }

    /**
     * Helper method for the remove method
     *
     * @param currNode the current node of the method is on
     * @param data the data we want to remove
     * @param temp temp node to capture the desired node (the return node)
     * @return the root (recently accessed node = pointer reinforcement)
     */
    private AVLNode<T> removeHelper(AVLNode<T> currNode, T data, AVLNode<T> temp) {
        //getBFNOde = balance the rotation
        if (currNode == null) {
            throw new NoSuchElementException("AVL doesn't contain this data.");
        }
        if (data.compareTo(currNode.getData()) < 0) {
            currNode.setLeft(removeHelper(currNode.getLeft(), data, temp));
        } else if (data.compareTo(currNode.getData()) > 0) {
            currNode.setRight(removeHelper(currNode.getRight(), data, temp));
        } else {
            temp.setData(currNode.getData());

            size--;
            if (currNode.getLeft() == null && currNode.getRight() == null) {
                return null;
            } else if (currNode.getLeft() != null && currNode.getRight() == null) {
                return currNode.getLeft();
            } else if (currNode.getLeft() == null && currNode.getRight() != null) {
                return currNode.getRight();
            } else {
                AVLNode<T> predecessor = new AVLNode<>(null);
                currNode.setLeft(predecessorHelper(currNode.getLeft(), predecessor));
                currNode.setData(predecessor.getData());
            }
        }
        update(currNode);
        return getBFNode(currNode);
    }

    /**
     * Method to find predecessor for edge case removal
     *
     * @param currNode the data we want to find, or we are on right now
     * @param temp temporary nide for capturing the predecessor node's data
     * @return predecessor node (the most recently accessed node)
     */
    private AVLNode<T> predecessorHelper(AVLNode<T> currNode, AVLNode<T> temp) {
        if (currNode.getRight() == null) {
            temp.setData(currNode.getData());
            return currNode.getLeft();
        } else {
            currNode.setRight(predecessorHelper(currNode.getRight(), temp));
        }
        update(currNode);
        return getBFNode(currNode);
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data is null.");
        }

        return getH(root, data);
    }

    /**
     * Helper recursive method for get() to recursive throught tree
     *
     * @param currNode currNode for recursive step
     * @param data data tried to be retrieved
     * @return data in tree that matches parameter
     */
    private T getH(AVLNode<T> currNode, T data) {
        //Use value equality for two values
        if (currNode == null) {
            throw new NoSuchElementException("Data does not exist in the tree.");
        } else if (currNode.getData().compareTo(data) > 0) {
            return getH(currNode.getLeft(), data);
        } else if (currNode.getData().compareTo(data) < 0) {
            return getH(currNode.getRight(), data);
        }
        return currNode.getData();
    }
    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree.
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data is null");
        }

        return containsH(root, data);
    }

    /**
     * Helper method for contains() to recurse through tree
     *
     * @param currNode current node for recusive step
     * @param data data being search for
     * @return whether tree contains data
     */
    private boolean containsH(AVLNode<T> currNode, T data) {
        if (currNode == null) {
            return false;
        } else if (currNode.getData().compareTo(data) > 0) {
            return containsH(currNode.getLeft(), data);
        } else if (currNode.getData().compareTo(data) < 0) {
            return containsH(currNode.getRight(), data);
        } else {
            return true;
        }
    }

    /**
     * Returns the height of the root of the tree.
     *
     * Should be O(1).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        // Since this is an AVL, this method does not need to traverse the tree
        // return root == null ? -1 : root.getHeight();
        if (size == 0) {
            return -1;
        } else {
            return root.getHeight();
        }
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the data on branches of the tree with the maximum depth. If you
     * encounter multiple branches of maximum depth while traversing, then you
     * should list the remaining data from the left branch first, then the
     * remaining data in the right branch. This is essentially a preorder
     * traversal of the tree, but only of the branches of maximum depth.
     *
     * This must be done recursively.
     *
     * Your list should not have duplicate data, and the data of a branch should be
     * listed in order going from the root to the leaf of that branch.
     *
     * Should run in worst case O(n), but you should not explore branches that
     * do not have maximum depth. You should also not need to traverse branches
     * more than once.
     *
     * Hint: How can you take advantage of the balancing information stored in
     * AVL nodes to discern deep branches?
     *
     * Example Tree:
     *                           10
     *                       /        \
     *                      5          15
     *                    /   \      /    \
     *                   2     7    13    20
     *                  / \   / \     \  / \
     *                 1   4 6   8   14 17  25
     *                /           \          \
     *               0             9         30
     *
     * Returns: [10, 5, 2, 1, 0, 7, 8, 9, 15, 20, 25, 30]
     *
     * @return the list of data in branches of maximum depth in preorder
     * traversal order
     */
    public List<T> deepestBranches() {
        List<T> list = new ArrayList<>();
        deepestBranchesHelper(this.root, list);
        return list;
    }

    /**
     * Helper method for deepestBranches()
     * @param currNode AVLNode that holds curr node
     * @param list the list that the data we are keeping track of
     *
     */
    private void deepestBranchesHelper(AVLNode<T> currNode, List<T> list) {
        //Copy and rehash of preOrder traversal for finding the deepest branches.
        //     * We choose which branch to go of the child whose height is
        //     * non-null/the most high.
        if (currNode == null) {
            return;
        } else {
            list.add(currNode.getData());
            if (currNode.getLeft() != null && currNode.getBalanceFactor() >= 0) {
                deepestBranchesHelper(currNode.getLeft(), list);
            }
            if (currNode.getRight() != null && currNode.getBalanceFactor() <= 0) {
                deepestBranchesHelper(currNode.getRight(), list);
            }
        }
    }

    /**
     * Returns a sorted list of data that are within the threshold bounds of
     * data1 and data2. That is, the data should be > data1 and < data2.
     *
     * This must be done recursively.
     *
     * Should run in worst case O(n), but this is heavily dependent on the
     * threshold data. You should not explore branches of the tree that do not
     * satisfy the threshold.
     *
     * Example Tree:
     *                           10
     *                       /        \
     *                      5          15
     *                    /   \      /    \
     *                   2     7    13    20
     *                  / \   / \     \  / \
     *                 1   4 6   8   14 17  25
     *                /           \          \
     *               0             9         30
     *
     * sortedInBetween(7, 14) returns [8, 9, 10, 13]
     * sortedInBetween(3, 8) returns [4, 5, 6, 7]
     * sortedInBetween(8, 8) returns []
     *
     * @param data1 the smaller data in the threshold
     * @param data2 the larger data in the threshold
     * @return a sorted list of data that is > data1 and < data2
     * @throws IllegalArgumentException if data1 or data2 are null
     * or if data1 > data2
     */
    public List<T> sortedInBetween(T data1, T data2) {
        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException("Cannot search for null data.");
        }
        if (data1.compareTo(data2) > 0) {
            throw new IllegalArgumentException("The parameter data is in the wrong order");
        } else {
            List<T> list = new ArrayList<>();
            if (data1.equals(data2)) {
                return list;
            }
            sortHelper(root, list, data1, data2);
            return list;
        }
    }

    /**
     * Private recursive method helper method for sort in between method
     *
     * @param currNode current node method is on
     * @param list the list of data that stores all desired values
     * @param data1 the lower bound of the interval (= data threshold)
     * @param data2 the upper bound of the interval (= data threshold)
     */
    private void sortHelper(AVLNode<T> currNode, List<T> list, T data1, T data2) {
        if (currNode == null) {
            return;
        }

        if (currNode.getData().compareTo(data1) <= 0) {
            sortHelper(currNode.getRight(), list, data1, data2);
        } else if (currNode.getData().compareTo(data2) >= 0) {
            sortHelper(currNode.getLeft(), list, data1, data2);
        } else {
            sortHelper(currNode.getLeft(), list, data1, data2);
            list.add(currNode.getData());
            sortHelper(currNode.getRight(), list, data1, data2);
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
