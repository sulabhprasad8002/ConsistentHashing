import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class SimpleConsistentHashing {
    SortedMap<Integer, String> hashRing = new TreeMap<>();
    private static final int NUM_OF_VIRTUAL_NODES = 100;

    public void addNode(String node){
        for(int i=0; i<NUM_OF_VIRTUAL_NODES; i++) {
            String virtualNode = node + "-" + i;

            int hash = hash(virtualNode);
            hashRing.put(hash, virtualNode);
        }

        redistributeKeys();
    }

    public void removeNode(String node) {
        for (int i=0; i<NUM_OF_VIRTUAL_NODES; i++) {
            String virtualNode = node + "-" + i;
            int hash = hash(virtualNode);

            hashRing.remove(hash);
        }

        redistributeKeys();
    }

    public void redistributeKeys() {
        System.out.println("Redistributing keys:");
        for(String key : getKeySet()) {
            String newNode = getNodeForKey(key);
            System.out.println("Key: " + key + " -> Node: " + newNode);
        }
        System.out.println("Redistribution complete");
    }

    public String getNodeForKey(String key) {

        if(hashRing.isEmpty()) {
            return null;
        }

        int hash = hash(key);

        if(!hashRing.containsKey(hash)) {
            SortedMap<Integer, String> tailMap = hashRing.tailMap(hash);

            hash = !tailMap.isEmpty() ? tailMap.firstKey() : hashRing.firstKey();
        }

        return hashRing.get(hash);
    }

    private int hash(String key) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(key.getBytes());
            return Math.abs(hashBytes.hashCode());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private Iterable<String> getKeySet() {
        return List.of("Key1", "Key2", "Key3", "Key4", "Key5", "Key6");
    }
}
