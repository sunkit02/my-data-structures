package datastructures.trees;

import java.util.*;

public class MyLinkedBinarySearchTree<E extends Comparable<E>> implements BinarySearchTree<E> {

    private BinaryTreeNode<E> root;
    private int size;

    public MyLinkedBinarySearchTree() {
        this.size = 0;
    }

    public MyLinkedBinarySearchTree(E rootElement) {
        this.root = new BinaryTreeNode<>(rootElement);
        this.size = 1;
    }

    public MyLinkedBinarySearchTree(Collection<E> elements) {
        insertAll(elements);
    }

    @Override

    public E getRootElement() {
        return root.element;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insert(E element) {
        if (isEmpty()) {
            root = new BinaryTreeNode<>(element);
            size++;
        }
        recursiveInsert(element, root);
    }

    public void insertAll(Collection<E> elements) {
        elements.forEach(this::insert);
    }

    private void recursiveInsert(E element, BinaryTreeNode<E> currentNode) {
        if (element.compareTo(currentNode.element) > 0) {
            if (currentNode.hasRightNode()) {
                recursiveInsert(element, currentNode.rightNode);
            } else {
                currentNode.rightNode = new BinaryTreeNode<>(element);
                size++;
            }
        } else if (element.compareTo(currentNode.element) < 0) {
            if (currentNode.hasLeftNode()) {
                recursiveInsert(element, currentNode.leftNode);
            } else {
                currentNode.leftNode = new BinaryTreeNode<>(element);
                size++;
            }
        }
    }

    @Override
    public void delete(E element) {
        if (element.equals(root.element)) {
            if (root.isLeaf()) {
                root = null;
                size--;
            } else {
                replaceWithInorderSuccessor(root);
            }
        } else {
            recursiveDelete(element, root);
        }
    }

    private void recursiveDelete(E targetElement, BinaryTreeNode<E> currRoot) {
        if (currRoot == null || currRoot.isLeaf()) {
            return;
        }
        if (targetElement.compareTo(currRoot.element) > 0) {
            if (!currRoot.hasRightNode()) return;
            if (targetElement.equals(currRoot.rightNode.element)) {
                deleteNode(currRoot.rightNode, currRoot);
            } else {
                recursiveDelete(targetElement, currRoot.rightNode);
            }
        } else {
            if (!currRoot.hasLeftNode()) return;
            if (targetElement.equals(currRoot.leftNode.element)) {
                deleteNode(currRoot.leftNode, currRoot);
            } else {
                recursiveDelete(targetElement, currRoot.leftNode);
            }
        }
    }

    private void deleteNode(BinaryTreeNode<E> targetNode, BinaryTreeNode<E> root) {
        if (targetNode.isLeaf()) {
            if (root.leftNode.equals(targetNode)) {
                root.leftNode = null;
            } else {
                root.rightNode = null;
            }
            size--;
        } else if (root.hasLeftNode() && root.leftNode.equals(targetNode)) {
            replaceWithInorderSuccessor(root.leftNode);
        } else if (root.hasRightNode()){
            replaceWithInorderSuccessor(root.rightNode);
        }
    }

    private void replaceWithInorderSuccessor(BinaryTreeNode<E> root) {
        if (root.isLeaf()) {
            throw new IllegalStateException(
                    "Cannot replace a leaf node with its inorder successor.");
        }
        if (root.hasRightNode()) {
            root.element = root.rightNode.element;
            if (root.rightNode.isLeaf()) {
                root.rightNode = null;
                size--;
            } else {
                replaceWithInorderSuccessor(root.rightNode);
            }
        } else {
            root.element = root.leftNode.element;
            if (root.leftNode.isLeaf()) {
                root.leftNode = null;
                size--;
            } else {
                replaceWithInorderSuccessor(root.leftNode);
            }
        }
    }

    @Override
    public boolean contains(E targetElement) {
        return recursiveSearch(targetElement, root) != null;
    }

    @Override
    public E find(E targetElement) {
        return recursiveSearch(targetElement, root);
    }

    private E recursiveSearch(E targetElement, BinaryTreeNode<E> node) {
        if (node == null) return null;
        if (node.element.equals(targetElement)) return targetElement;

        if (targetElement.compareTo(node.element) > 0) {
            return recursiveSearch(targetElement, node.rightNode);
        } else {
            return recursiveSearch(targetElement, node.leftNode);
        }
    }

    public int getHeight() {
        return traverseForHeight(root);
    }

    private int traverseForHeight(BinaryTreeNode<E> node) {
        if (node.isLeaf()) return 1;
        int rightHeight = 1;
        int leftHeight = 1;
        if (node.hasLeftNode()) {
            leftHeight += traverseForHeight(node.leftNode);
        }
        if (node.hasRightNode()) {
            rightHeight += traverseForHeight(node.rightNode);
        }
        return Math.max(rightHeight, leftHeight);
    }

    public void reverse() {
        reverse(root);
    }

    private void reverse(BinaryTreeNode<E> root) {
        if (root == null) return;

        BinaryTreeNode<E> temp = root.leftNode;
        root.leftNode = root.rightNode;
        root.rightNode = temp;

        reverse(root.leftNode);
        reverse(root.rightNode);
    }

    @Override
    public Iterator<E> iteratorInOrder() {
        List<E> elements = new ArrayList<>();
        traverseInOrder(elements, root);
        return new BinaryTreeIterator(elements);
    }

    @Override
    public Iterator<E> iteratorPreOrder() {
        List<E> elements = new ArrayList<>();
        traversePreOrder(elements, root);
        return new BinaryTreeIterator(elements);
    }

    @Override
    public Iterator<E> iteratorPostOrder() {
        List<E> elements = new ArrayList<>();
        traversePostOrder(elements, root);
        return new BinaryTreeIterator(elements);
    }

    public Iterator<E> iteratorReverseOrder() {
        List<E> elements = new ArrayList<>();
        traverseReverseOrder(elements, root);
        return new BinaryTreeIterator(elements);
    }

    private void traverseInOrder(List<E> elements, BinaryTreeNode<E> root) {
        if (root == null) return;
        if (root.isLeaf()) {
            elements.add(root.element);
            return;
        }
        if (root.hasLeftNode()) {
            traverseInOrder(elements, root.leftNode);
        }
        elements.add(root.element);
        if (root.hasRightNode()) {
            traverseInOrder(elements, root.rightNode);
        }
    }

    private void traversePreOrder(List<E> elements, BinaryTreeNode<E> root) {
        if (root.isLeaf()) {
            elements.add(root.element);
            return;
        }
        elements.add(root.element);
        if (root.hasLeftNode()) {
            traversePreOrder(elements, root.leftNode);
        }
        if (root.hasRightNode()) {
            traversePreOrder(elements, root.rightNode);
        }
    }

    private void traversePostOrder(List<E> elements, BinaryTreeNode<E> root) {
        if (root.isLeaf()) {
            elements.add(root.element);
            return;
        }
        if (root.hasLeftNode()) {
            traversePostOrder(elements, root.leftNode);
        }
        if (root.hasRightNode()) {
            traversePostOrder(elements, root.rightNode);
        }
        elements.add(root.element);
    }

    private void traverseReverseOrder(List<E> elements, BinaryTreeNode<E> root) {
        if (root.isLeaf()) {
            elements.add(root.element);
            return;
        }
        if (root.hasRightNode()) {
            traverseReverseOrder(elements, root.rightNode);
        }
        elements.add(root.element);
        if (root.hasLeftNode()) {
            traverseReverseOrder(elements, root.leftNode);
        }
    }

    @Override
    public String toString() {
        List<E> elements = new ArrayList<>();
        traverseInOrder(elements, root);
        return elements.toString();
    }


    class BinaryTreeIterator implements Iterator<E> {

        private final List<E> elements;
        private int index;

        public BinaryTreeIterator(List<E> elements) {
            this.elements = elements;
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < elements.size();
        }

        @Override
        public E next() {
            return elements.get(index++);
        }

        @Override
        public String toString() {
            return elements.toString();
        }
    }

    private static class BinaryTreeNode<E> {
        E element;
        BinaryTreeNode<E> leftNode;
        BinaryTreeNode<E> rightNode;

        public BinaryTreeNode(E element) {
            this.element = element;
        }

        public BinaryTreeNode(E element,
                              BinaryTreeNode<E> leftNode,
                              BinaryTreeNode<E> rightNode) {
            this.element = element;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public boolean hasLeftNode() {
            return leftNode != null;
        }

        public boolean hasRightNode() {
            return rightNode != null;
        }

        public boolean hasChildren() {
            return hasRightNode() || hasLeftNode();
        }

        public boolean isLeaf() {
            return !hasLeftNode() && !hasRightNode();
        }

        @Override
        public String toString() {
            E rightNodeValue = rightNode != null ? rightNode.element : null;
            E leftNodeValue = leftNode != null ? leftNode.element : null;
            return String.format("Node(left=%s, this=%s, right=%s)",
                    leftNodeValue, this.element, rightNodeValue);
        }
    }
}
