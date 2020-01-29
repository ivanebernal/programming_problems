package build_tree;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BuildTreeTest {
  @Test
  public void testKnownInputs() {
    BuildTree buildTree = new BuildTree();
    
    Node simpleTree = new Node(2);
    simpleTree.left = new Node(1);
    simpleTree.right = new Node(3);
    assertEquals("Simple tree incorrect", simpleTree, buildTree.buildTree(new int[]{1, 2, 3}, new int[]{2, 1, 3}));

    Node unbalancedTree = new Node(1);
    unbalancedTree.left = new Node(2);
    unbalancedTree.left.left = new Node(4);
    unbalancedTree.left.left.left = new Node(5);
    unbalancedTree.left.left.right = new Node(6);
    unbalancedTree.right = new Node(3);
    assertEquals("Unbalanced tree incorrect", unbalancedTree, buildTree.buildTree(new int[]{5, 4, 6, 2, 1, 3}, new int[]{1, 2, 4, 5, 6, 3}));
  }
}