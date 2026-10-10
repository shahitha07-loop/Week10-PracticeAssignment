import java.util.*;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList {
    Node head;
    int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Time Complexity: O(1)
    public void addFirst(int token) {
        Node newNode = new Node(token);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // Time Complexity: O(N) where N is the current size of the list
    public void addLast(int token) {
        Node newNode = new Node(token);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Time Complexity: O(N) to traverse to the specified index
    public void insertAt(int index, int token) {
        if (index < 0 || index > size) return;
        if (index == 0) {
            addFirst(token);
            return;
        }
        Node newNode = new Node(token);
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

public class PriorityTokenInsertion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SinglyLinkedList list = new SinglyLinkedList();

        if (scanner.hasNextLine()) {
            String startLine = scanner.nextLine().trim();
            if (startLine.contains("=")) {
                String valuesPart = startLine.split("=")[1].trim();
                if (!valuesPart.isEmpty()) {
                    String[] vals = valuesPart.split("->");
                    for (String v : vals) {
                        v = v.trim();
                        if (!v.isEmpty() && !v.equalsIgnoreCase("null")) {
                            list.addLast(Integer.parseInt(v));
                        }
                    }
                }
            }
        }

        while (scanner.hasNextLine()) {
            String opLine = scanner.nextLine().trim();
            if (opLine.isEmpty()) continue;
            String[] ops = opLine.split(",");
            for (String op : ops) {
                op = op.trim();
                if (op.startsWith("addFirst")) {
                    list.addFirst(extractParam(op));
                } else if (op.startsWith("addLast")) {
                    list.addLast(extractParam(op));
                } else if (op.startsWith("insertAt")) {
                    int[] params = extractTwoParams(op);
                    list.insertAt(params[0], params[1]);
                }
            }
        }

        list.printList();
        scanner.close();
    }

    private static int extractParam(String op) {
        int startIdx = op.indexOf('(');
        int endIdx = op.indexOf(')');
        return Integer.parseInt(op.substring(startIdx + 1, endIdx).trim());
    }

    private static int[] extractTwoParams(String op) {
        int startIdx = op.indexOf('(');
        int endIdx = op.indexOf(')');
        String[] parts = op.substring(startIdx + 1, endIdx).split(",");
        return new int[] { Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()) };
    }
}