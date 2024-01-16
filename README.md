# This project contains implementations of consistent hashing algorithm in java

# Consistent hashing is an algorithm to select nodes or virtual nodes for storing the keys without taking total no of nodes in calculation.

# Consistent Hashing: A Detailed Overview

Consistent hashing is a distributed hashing scheme that provides a way to evenly distribute keys across a changing set of nodes in a distributed system. This technique minimizes the amount of data that needs to be rehashed when nodes are added or removed, making it particularly useful in scenarios where the number of nodes can vary dynamically.

## Key Concepts:

### Hash Ring: 
The hash space is typically visualized as a ring, where each node is assigned a position based on its hash value.

### Node Positioning: 
Each node in the system is assigned one or more positions on the ring based on the hash of its name or identifier.
Virtual nodes (replicas) are often used to increase the number of points per node for better load balancing.

### Key Hashing: 
When a key needs to be stored or retrieved, its hash is calculated. The key is then mapped onto the hash ring at the position closest to its hash value.

### Node Lookup: 
To find the node responsible for a key, you move clockwise around the ring until you encounter the first node.

### Node Additions or Removals: 
When a node is added or removed, only a fraction of the keys need to be remapped. The keys that were assigned to the departing node are reassigned to its successor on the ring.

## Workflow:

### Determining Node Positions: 
Each node's identifier is hashed to determine its position on the hash ring. Virtual nodes may be used to increase the number of positions per node.

### Key Assignment: 
When a key needs to be stored, its hash determines its position on the ring. The key is assigned to the first node encountered in the clockwise direction.

### Node Lookup: 
When a key needs to be retrieved, its hash determines its position on the ring. Moving clockwise on the ring, the first encountered node is responsible for the key.

### Node Additions: 
When a node is added, keys that were assigned to the next node are reassigned to the new node. This minimizes the impact on other nodes and maintains a balanced distribution.

### Node Removals: 
When a node is removed, keys assigned to that node are reassigned to its successor. This ensures a smooth transition and avoids redistributing all keys.


## Advantages:

### Load Balancing: 
Keys are distributed evenly across nodes, leading to a balanced load in the system.

### Minimized Rehashing: 
Only a fraction of keys need to be remapped when nodes are added or removed, reducing the impact on the system.

### Scalability: 
The system can easily scale by adding or removing nodes without significant rehashing.

### Fault Tolerance: 
If a node fails, its keys are often reassigned to the next node on the ring, ensuring continued operation.

## Use Cases:

### Distributed Databases:
Consistent hashing is widely used in distributed databases to evenly distribute data.

### Caching Systems:
In distributed caching systems, consistent hashing ensures a balanced distribution of cache entries.

### Load Balancing:
Consistent hashing is employed in load balancers to distribute incoming requests across servers.

### Distributed File Systems:
Some distributed file systems use consistent hashing for efficient data distribution.

### Content Delivery Networks (CDNs):
CDNs use consistent hashing to determine the edge server that should serve a particular piece of content.

## Challenges:

### Hotspots:
In some scenarios, keys may not be evenly distributed, leading to hotspots.

### Node Departure Handling:
Handling the departure of a node with a large number of keys requires careful planning.

### Deterministic Nature:
The deterministic nature of consistent hashing may not be suitable for certain applications.

### In summary, consistent hashing is a powerful technique for distributing keys in a scalable and fault-tolerant manner. Its ability to minimize rehashing during node changes makes it particularly valuable in dynamic and large-scale distributed systems.