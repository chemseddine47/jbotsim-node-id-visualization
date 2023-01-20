This project provides two practical solutions for displaying Node IDs within the [JBotSim](https://jbotsim.github.io/) simulation environment. By default, JBotSim provides a clean visualization but lacks direct on-screen labeling for node identities, which can make debugging distributed algorithms or identifying specific nodes difficult.

## 🧐 What is JBotSim?
JBotSim is a lightweight Java library designed for the simulation of distributed algorithms in dynamic networks. It is primarily used for:
* **Algorithm Prototyping:** Implementing ideas in minutes to observe their behavior.
* **Distributed Computing Research:** Testing consensus, broadcasting, and routing protocols.
* **Dynamic Networks:** Modeling mobile robots, UAV swarms, and wireless sensor networks where nodes move or links break frequently.

## 🚩 The Problem
In standard JBotSim usage, nodes are visualized as icons (often a default `circle.png`). While each node has a unique integer ID accessible via the API (`node.getID()`), this ID is not rendered in the GUI. Users often have to hover over nodes to see labels or print logs to the console to track them.

## 🛠️ Solutions Implemented
This project provides two ways to solve this by overriding the default rendering logic in the `JNode.java` class (or similar UI components) using `Graphics2D`.

### Solution 1: Drawing Over the Node Icon
This method keeps the original aesthetic of JBotSim while adding information.
* **Mechanism:** Using `g2d.drawString()`, the node's ID is drawn directly on top of the `circle.png` icon.
* **Logic:** The ID is converted to a string and positioned using relative coordinates to center it within the existing circle.
* **Best for:** Users who want to keep the default "look and feel" but need at-a-glance identification.

### Solution 2: Custom Geometric Rendering
This method replaces the static icon with dynamic Java2D shapes.
* **Mechanism:** Instead of loading an image file, the `paint()` method draws a geometric circle using the node's original coordinates to maintain dynamic movement.
* **Logic:** The ID is rendered in the center, and because the shape is drawn programmatically, you have full control over the node's color, size, and border weight.
* **Best for:** Simulations where nodes need to change color or size dynamically based on their algorithmic state (e.g., leader election).

