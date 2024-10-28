import java.util.ArrayList;
import java.util.List;

public class ReverseTree<T extends Number> {

    private volatile List<Node<T>> root;

    public void add(T value) {
        if (value == null) {
            throw new IllegalStateException("Parameter must not be null");
        }

        if (root == null) {
            synchronized (ReverseTree.class) {
                if (root == null) {
                    root = new ArrayList<>();
                }
            }
        }

        root.add(new Node<>(value));
    }

    public T findGeneralElem(T value1, T value2) {
        if (value1.equals(value2)) {
            throw new IllegalArgumentException("Parameters must not be equal");
        }

        boolean isFind1 = false;
        boolean isFind2 = false;
        int index1 = -1;
        int index2 = -1;
        for (int i = 0; i < root.size(); i++) {
            if (root.get(i).getValue().equals(value1)) {
                index1 = i;
                isFind1 = true;
            }
            if (root.get(i).getValue().equals(value2)) {
                index2 = i;
                isFind2 = true;
            }

            if (isFind1 && isFind2) {
                break;
            }
        }

        boolean isFinish = false;
        Node<T> node1 = root.get(index1);
        Node<T> node2 = root.get(index2);
        List<T> list1 = findAllParents(node1);
        List<T> list2 = findAllParents(node2);

        list1.retainAll(list2);
        return list1.stream().findFirst().get();
    }

    List<T> findAllParents(Node<T> node) {
        List<T> list = new ArrayList<>();
        return iterateByParents(node, list);
    }

    List<T> iterateByParents(Node<T> node, List<T> result) {
        if (node != null) {
            result.add(node.getValue());
            iterateByParents(node.getParent(), result);
        }
        return result;
    }

    List<Node<T>> getRoot() {
        return root;
    }
}

class Node<T extends Number> {
    private final T value;
    private Node<T> parent;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }
}