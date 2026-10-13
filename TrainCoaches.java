class Node {
    String name;
    Node prev;
    Node next;

    public Node(String name) {
        this.name = name;
        this.prev = null;
        this.next = null;
    }
}

public class TrainCoaches {
    private Node head;
    private Node tail;

    // Add a coach to the end of the train
    public void addLast(String name) {
        Node newNode = new Node(name);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Remove a coach by name
    public void remove(String name) {
        Node current = head;
        while (current != null) {
            if (current.name.equals(name)) {
                // If it's the only node in the list
                if (current == head && current == tail) {
                    head = null;
                    tail = null;
                } 
                // If it's the head node
                else if (current == head) {
                    head = head.next;
                    head.prev = null;
                } 
                // If it's the tail node
                else if (current == tail) {
                    tail = tail.prev;
                    tail.next = null;
                } 
                // If it's a middle node
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                return; // Exit after removing the coach
            }
            current = current.next;
        }
    }

    // Print train from engine end (head to tail)
    public void printForward() {
        Node current = head;
        StringBuilder sb = new StringBuilder("Forward: ");
        while (current != null) {
            sb.append(current.name);
            if (current.next != null) {
                sb.append(" <-> ");
            }
            current = current.next;
        }
        System.out.println(sb.toString());
    }

    // Print train from guard end (tail to head)
    public void printBackward() {
        Node current = tail;
        StringBuilder sb = new StringBuilder("Backward: ");
        while (current != null) {
            sb.append(current.name);
            if (current.prev != null) {
                sb.append(" <-> ");
            }
            current = current.prev;
        }
        System.out.println(sb.toString());
    }

    // Example Test Case
    public static void main(String[] args) {
        TrainCoaches train = new TrainCoaches();
        String[] coaches = {"Engine", "A1", "B1", "B2", "Guard"};
        
        for (String coach : coaches) {
            train.addLast(coach);
        }

        // Remove B1
        train.remove("B1");

        // Print results
        train.printForward();
        train.printBackward();
    }
}