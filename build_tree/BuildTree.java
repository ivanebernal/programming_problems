package build_tree;

class BuildTree {
    Node buildTree(int[] inOrder, int[] preOrder) {
        if(inOrder.length == 0) return null;
        int rootData = preOrder[0];
        Node root = new Node(rootData);
        int[][] subInOrder = divideInOrder(inOrder, rootData); //O(N)
        int[][] subPreOrder = dividePreOrder(preOrder, subInOrder); //O(N)
        root.left = buildTree(subInOrder[0], subPreOrder[0]);
        root.right = buildTree(subInOrder[1], subPreOrder[1]);
        return root;
    }

    //O(N)
    int[][] divideInOrder(int[] inOrder, int head) {
        int index = -1;
        for(int i = 0; i < inOrder.length; i++) {
            if(inOrder[i] == head) {
                index = i;
                break;
            }
        }
        return new int[][] { subArr(inOrder, 0, index), subArr(inOrder, index + 1, inOrder.length) };
    }

    int[][] dividePreOrder(int[] preOrder, int[][] newInOrder) {
        int lSize = newInOrder[0].length;
        int rSize = newInOrder[1].length;
        return new int[][] { subArr(preOrder, 1, lSize + 1), subArr(preOrder, lSize + 1, lSize + rSize + 1) };
    }

    int[] subArr(int[] arr, int start, int end) {
        if(start > end) return new int[0];
        int[] result = new int[end - start];
        for(int i = start; i < end; i ++) {
            result[i - start] = arr[i];
        }
        return result;
    }
}