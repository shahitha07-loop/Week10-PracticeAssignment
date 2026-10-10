import java.util.*;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class QueueReversal {

    // Reverses the singly linked list in place and returns the new head
    public static Node reverse(Node head) {
        Node prev = null;
        Node current = head;
        
        while (current != null) {
            Node nextTemp = current.next; // Store the next node
            current.next = prev;          // Reverse the current node's pointer
            prev = current;               // Move prev one step forward
            current = nextTemp;           // Move current one step forward
        }
        
        return prev; // prev will be the new head of the reversed list
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node head = null;
        Node tail = null;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            if (line.startsWith("queue")) {
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
            }
        }

        head = reverse(head);
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