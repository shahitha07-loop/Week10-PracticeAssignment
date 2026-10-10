import java.util.*;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class CancelledOrdersCleanup {

    // Removes all nodes containing the specified code and returns the new head
    public static Node removeAll(Node head, int code) {
        // Handle leading nodes that match the code
        while (head != null && head.data == code) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        // Traverse the rest of the list
        Node current = head;
        while (current.next != null) {
            if (current.next.data == code) {
                current.next = current.next.next; // Bypass the matching node
            } else {
                current = current.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node head = null;
        Node tail = null;
        int codeToRemove = -1;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            if (line.startsWith("orders")) {
                String[] parts = line.split("=");
                if (parts.length > 1) {
                    String valuesPart = parts[1].trim();
                    String[] vals = valuesPart.split("->");
                    for (String v : vals) {
                        v = v.trim();
                        if (v.isEmpty() || v.equalsIgnoreCase("null")) continue;
                        Node newNode = new Node(Integer.parseInt(v));
                        if (head == null) {
                            head = newNode;
                            tail = newNode;
                        } else {
                            tail.next = newNode;
                            tail = newNode;
                        }
                    }
                }
            } else if (line.startsWith("code")) {
                String[] parts = line.split("=");
                if (parts.length > 1) {
                    codeToRemove = Integer.parseInt(parts[1].trim());
                }
            }
        }

        head = removeAll(head, codeToRemove);
        printList(head);
        scanner.close();
    }

    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}