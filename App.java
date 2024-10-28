public class App {

    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        init(tree);
        Integer res = tree.findGeneralElem(2, 13);

        // ---------------

        ReverseTree<Integer> reverseTree = new ReverseTree<>();
        initReverseTree(reverseTree);
        Integer generalElem = reverseTree.findGeneralElem(2, 7);
        System.out.println();
    }

    static void init(Tree<Integer> tree) {
        tree.add(10);
        tree.add(5);
        tree.add(3);
        tree.add(4);
        tree.add(2);
        tree.add(6);
        tree.add(7);
        tree.add(12);
        tree.add(11);
        tree.add(13);
    }

    static void initReverseTree(ReverseTree<Integer> reverseTree) {
        reverseTree.add(2);
        reverseTree.add(4);
        reverseTree.add(7);
        reverseTree.add(11);
        reverseTree.add(13);

        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node6 = new Node<>(6);
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node10 = new Node<>(10);
        Node<Integer> node12 = new Node<>(12);

        reverseTree.getRoot().get(0).setParent(node3);
        reverseTree.getRoot().get(1).setParent(node3);
        reverseTree.getRoot().get(2).setParent(node6);
        node3.setParent(node5);
        node6.setParent(node5);
        node5.setParent(node10);

        reverseTree.getRoot().get(3).setParent(node12);
        reverseTree.getRoot().get(4).setParent(node12);
        node12.setParent(node10);
    }
}