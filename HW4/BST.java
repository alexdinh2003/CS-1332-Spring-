import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Your implementation of a BST.
 *
 * @author HOANG GIA NGHI, DINH [FIRST, LAST]
 * @version 1.0
 * @userid ndinh31 (i.e. gburdell3)
 * @GTID 903733512 (i.e. 900000000)
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The data passed in Collections is null");
        }

        for (T curr : data) {
            if (curr == null) {
                throw new IllegalArgumentException("Any element in data is null. Please pass data in!");
            }
            add(curr);
        }
    }

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null. Please pass the data in");
        }

        root = rAdd(data, root);
    }

    /**
     * Private recursive helper function that adds a node onto the BST in O(log n) time.
     *
     * @param data The data to add to the tree.
     * @param currentNode The current node in the BST being compared.
     * @return The current node that was recursively passed in or pointer reinforcement
     */

    private BSTNode<T> rAdd(T data, BSTNode<T> currentNode) {
        //a) If you reach a null node → add the data here
        //b) If you reach a match → do nothing (no duplicates)
        if (currentNode == null) {
            this.size++;
            return new BSTNode<>(data);
        } else if (data.compareTo(currentNode.getData()) < 0) {
            currentNode.setLeft(rAdd(data, currentNode.getLeft()));
        } else if (data.compareTo(currentNode.getData()) > 0) {
            currentNode.setRight(rAdd(data, currentNode.getRight()));
        }
        return currentNode;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The passing data is null");
        }
        BSTNode<T> dummy = new BSTNode<>(null);
        root = rRemove(this.root, data, dummy);
        return dummy.getData();
    }

    /**
     * A private recursive help method to remove and return the data
     *
     * @param node current node of subtree
     * @param data The data of type T was being removed
     * @param dummy the placeholder node
     * @throw java.util.NoSuchElementException if the data is not in the tree
     * @return pointer reinforcement
     */

    private BSTNode<T> rRemove(BSTNode<T> node, T data, BSTNode<T> dummy) {
        if (node == null) {
            throw new NoSuchElementException(data + " is not located in this tree.");
        } else if (node.getData().compareTo(data) > 0) {
            // check if it is less or greater than 0
            node.setLeft(rRemove(node.getLeft(), data, dummy));
        } else if (node.getData().compareTo(data) < 0) {
            node.setRight(rRemove(node.getRight(), data, dummy));
        } else {
            dummy.setData(node.getData());
            size--;

            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            } else if (node.getLeft() == null) {
                //One child remove case
                //Set the parent’s pointer to the node to the node’s child
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                //dummy2 = successor
                BSTNode<T> dummy2 = new BSTNode<>(null);
                node.setRight(removeSuccessor(node.getRight(), dummy2));
                node.setData(dummy2.getData());
            }
        }
        return node;
    }

    /**
     * Helper method to search for nearest successor of subtree
     * Get successor and remove the node
     *
     * @param currentNode current root of subtree
     * @param dummy placeholder remove node/ hold successor data
     * @return nearest successor of subtree / subtree on the right of removed node
     */

    private BSTNode<T> removeSuccessor(BSTNode<T> currentNode, BSTNode<T> dummy) {
        //Instead of removing the node itself, we’ll replace the node’s data
        //with the predecessor or successor and remove THAT node:
        //  1) Predecessor - largest value still smaller than the current
        //  value; go one to the left, then as far right as possible
        //  (2) Successor - smallest value still larger that the current
        //  value; go one to the right, then as far left as possible
        //  We’ll delegate the removal of an actual node to the node
        //  containing the predecessor or successor since these will always
        //  be 0 or 1 child remove cases

        if (currentNode.getLeft() == null) {
            dummy.setData(currentNode.getData());
            return currentNode.getRight();
        } else {
            currentNode.setLeft(removeSuccessor(currentNode.getLeft(), dummy));
        }
        return currentNode;
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data accessing is null");
        }

        BSTNode<T> searchNode = rSearch(this.root, data);
        if (searchNode == null) {
            throw new NoSuchElementException("The data is not in the BST!");
        } else {
            return searchNode.getData();
        }
    }

    /**
     * private recursive helper method to search for an element in BST
     * @param currentNode the current node in BST
     * @param targetData The target data to find
     * @return the data store in BST
     */
    private BSTNode<T> rSearch(BSTNode<T> currentNode, T targetData) {
        if (currentNode == null || currentNode.getData().equals(targetData)) {
            return currentNode;
        } else if (currentNode.getData().compareTo(targetData) < 0) {
            return rSearch(currentNode.getRight(), targetData);
        }
        return rSearch(currentNode.getLeft(), targetData);
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data is null.");
        }
        return rSearch(this.root, data) != null;
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> list = new ArrayList<>();
        rPreorder(list, root);
        return list;

    }

    /**
     * A private recursive helper method to traverse the BST in preorder format
     *
     * @param currentNode current node in BST
     * @param listNode a complete list of nodes in the subtree in preorder
     */

    private void rPreorder(List<T> listNode, BSTNode<T> currentNode) {
        // if current node is null → return
        if (currentNode == null) {
            return;
        }

        // (a) look at data (record, print it)
        // (b) recurse left
        // (c) recurse right
        listNode.add(currentNode.getData());
        rPreorder(listNode, currentNode.getLeft());
        rPreorder(listNode, currentNode.getRight());
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> list = new ArrayList<>();
        rInorder(list, root);
        return list;
    }

    /**
     * A private recursive helper method to traverse the BST in inorder format
     *
     * @param currentNode current node in BST
     * @param listNode a complete list of nodes in the subtree in inorder
     */

    private void rInorder(List<T> listNode, BSTNode<T> currentNode) {
        // if current node is null → return
        if (currentNode == null) {
            return;
        }

        // (a) recurse left
        // (b) look at data (record, print it)
        // (c) recurse right
        rInorder(listNode, currentNode.getLeft());
        listNode.add(currentNode.getData());
        rInorder(listNode, currentNode.getRight());
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> list = new ArrayList<>();
        rPostorder(list, root);
        return list;
    }

    /**
     * A private recursive helper method to traverse the BST in inorder format
     *
     * @param currentNode current node in BST
     * @param listNode a complete list of nodes in the subtree in inorder
     */

    private void rPostorder(List<T> listNode, BSTNode<T> currentNode) {
        // if current node is null → return
        if (currentNode == null) {
            return;
        }

        // (a) recurse left
        // (b) recurse right
        // (c) look at data (record, print it)
        rPostorder(listNode, currentNode.getLeft());
        rPostorder(listNode, currentNode.getRight());
        listNode.add(currentNode.getData());
    }
    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        //(1) Add the root to the queue
        //(2) While the queue is not empty →
        //(a) Remove one node from the queue
        //(b) Enqueue its left and right children (in that order)

        List<T> list = new ArrayList<>();
        Queue<BSTNode<T>> queue = new LinkedList<>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            BSTNode<T> currentNode = queue.remove();
            if (currentNode != null) {
                list.add(currentNode.getData());
                queue.add(currentNode.getLeft());
                queue.add(currentNode.getRight());
            }
        }

        return list;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return height at current node - 1
     */
    public int height() {
        if (root == null) {
            return -1;
        }
        return rFindHeight(root);
    }

    /**
     * Private recursive method to find the height of the tree
     *
     * @param currentNode current node in the BST
     * @return the height of the root of the tree
     */

    private int rFindHeight(BSTNode<T> currentNode) {
        if (currentNode == null) {
            return -1;
        }

        return Math.max(rFindHeight(currentNode.getLeft()), rFindHeight(currentNode.getRight())) + 1;
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Finds and retrieves the k-largest elements from the BST in sorted order,
     * least to greatest.
     *
     * This must be done recursively.
     *
     * In most cases, this method will not need to traverse the entire tree to
     * function properly, so you should only traverse the branches of the tree
     * necessary to get the data and only do so once. Failure to do so will
     * result in an efficiency penalty.
     *
     * EXAMPLE: Given the BST below composed of Integers:
     *
     *                50
     *              /    \
     *            25      75
     *           /  \
     *          12   37
     *         /  \    \
     *        10  15    40
     *           /
     *          13
     *
     * kLargest(5) should return the list [25, 37, 40, 50, 75].
     * kLargest(3) should return the list [40, 50, 75].
     *
     * Should have a running time of O(log(n) + k) for a balanced tree and a
     * worst case of O(n + k), with n being the number of data in the BST
     *
     * @param k the number of largest elements to return
     * @return sorted list consisting of the k largest elements
     * @throws java.lang.IllegalArgumentException if k < 0 or k > size
     */
    public List<T> kLargest(int k) {
        if (k < 0 || k > size) {
            throw new IllegalArgumentException(String.format("You must access [0, %d] elements.", size));
        }
        List<T> kElements = new ArrayList<>();
        rInOrder(root, k, kElements);

        return kElements;
    }

    /**
     * Private recursive helper method to traverse in reverse inorder
     *
     * @param currentNode the current node in the tree
     * @param k the number of elements that add to the list
     * @param list List of maximum k elements
     */

    private void rInOrder(BSTNode<T> currentNode, int k, List<T> list) {
        if (currentNode == null) {
            return;
        }
        if (list.size() < k) {
            rInOrder(currentNode.getRight(), k, list);
            if (list.size() < k) {
                list.add(0, currentNode.getData());
                rInOrder(currentNode.getLeft(), k, list);
            }
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
    public BSTNode<T> getRoot() {
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
