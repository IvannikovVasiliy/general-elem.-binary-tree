import java.util.ArrayList;
import java.util.List;

public class Tree<T extends Number> {
    private static final String LEFT_PREFIX = "l";
    private static final String RIGHT_PREFIX = "r";

    private volatile Node<T> root;

    public void add(T value) {
        if (value == null) {
            throw new IllegalStateException("Parameter must not be null");
        }

        if (root == null) {
            synchronized (Tree.class) {
                if (root == null) {
                    root = new Node<>(value);
                }
            }
        } else {
            root.add(value);
        }
    }

    public T findGeneralElem(T value1, T value2) {
        String res1 = root.find(value1, "");
        String res2 = root.find(value2, "");
        int minLength = Math.min(res1.length(), res2.length());

        StringBuilder resString = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            if (res1.charAt(i) != res2.charAt(i)) {
                break;
            }
            resString.append(res1.charAt(i));
        }

        return generalELem(resString.toString(), 0, root);
    }

    private T generalELem(String str, int index, Node<T> node) {
        if (str.length() == index) {
            return node.value;
        }
        Node<T> nextNode = LEFT_PREFIX.equals(String.valueOf(str.charAt(index++))) ? node.left : node.right;
        return generalELem(str, index, nextNode);
    }

    static class Node<T extends Number> {
        Comparable<? super T> comparable;

        private final T value;
        private Node<T> left;
        private Node<T> right;

        public Node(T value) {
            this.value = value;
            comparable = (Comparable<? super T>) this.value;
        }

        public void add(T value) {
            if (comparable.compareTo(value) < 0) {
                if (right == null) {
                    right = new Node<>(value);
                } else {
                    right.add(value);
                }
            } else {
                if (left == null) {
                    left = new Node<>(value);
                } else {
                    left.add(value);
                }
            }
        }

        public String find(T value, String res) {
            if (comparable.compareTo(value) == 0) {
                return res;
            }
            if (comparable.compareTo(value) < 0) {
                if (right == null) {
                    throw new ElementNotFoundException("Element " + value + " not found");
                }
                return right.find(value, res + RIGHT_PREFIX);
            } else {
                return left.find(value, res + LEFT_PREFIX);
            }
        }
    }
}