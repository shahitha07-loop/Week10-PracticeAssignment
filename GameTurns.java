class Node {
    String name;
    Node next;

    public Node(String name) {
        this.name = name;
        this.next = null;
    }
}

public class GameTurns {
    private Node tail;

    // Add a player to the end of the circular list - O(1) time
    public void addLast(String name) {
        Node newNode = new Node(name);
        if (tail == null) {
            tail = newNode;
            tail.next = tail; // Points to itself to form a circle
        } else {
            newNode.next = tail.next; // New node points to head
            tail.next = newNode;      // Old tail points to new node
            tail = newNode;           // Update tail reference
        }
    }

    // Print the player for each turn in order - O(turns) time
    public void printTurns(int turns) {
        if (tail == null || turns <= 0) {
            return;
        }

        Node current = tail.next; // Start from head
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < turns; i++) {
            sb.append(current.name);
            if (i < turns - 1) {
                sb.append(" ");
            }
            current = current.next; // Move to the next player in the circle
        }

        System.out.println(sb.toString());
    }

    // Example Test Case
    public static void main(String[] args) {
        GameTurns game = new GameTurns();
        String[] players = {"Asha", "Ravi", "Neha"};
        int turns = 7;

        for (String player : players) {
            game.addLast(player);
        }

        System.out.print("Expected Output: ");
        game.printTurns(turns);
    }
}