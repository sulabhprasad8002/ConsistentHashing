public class ConsistentHashingTestDrive {
    public static void main(String[] args) {
        SimpleConsistentHashing simpleConsistentHashing = new SimpleConsistentHashing();

        simpleConsistentHashing.addNode("Node 1");
        simpleConsistentHashing.addNode("Node 2");
        simpleConsistentHashing.addNode("Node 3");

        simpleConsistentHashing.removeNode("Node 1");
    }
}
