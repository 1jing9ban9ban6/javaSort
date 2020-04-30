package Toolee;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author jing
 * @create 2020-04-06-20:45
 */
public class treeDemo {
    class TreeNode//节点结构
    {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }
    }

    public static void preOrderRe(TreeNode biTree) {//递归实现
        if (biTree == null)
            return;
        else {
            System.out.println(biTree.value);
            preOrderRe(biTree.left);
            preOrderRe(biTree.right);
        }
    }

    public static void preOrder(TreeNode biTree) {//非递归实现
        Stack<TreeNode> stack = new Stack<>();
        while (biTree != null || !stack.isEmpty()) {
            while (biTree != null) {
                System.out.println(biTree.value);
                stack.push(biTree);
                biTree = biTree.left;
            }
            if (!stack.isEmpty()) {
                biTree = stack.pop();
                biTree = biTree.right;
            }
        }
    }

    public static void midOrderRe(TreeNode biTree) {//中序遍历递归实现
        if (biTree == null)
            return;
        else {
            midOrderRe(biTree.left);
            System.out.println(biTree.value);
            midOrderRe(biTree.right);
        }
    }


    public static void midOrder(TreeNode biTree) {//中序遍历费递归实现
        Stack<TreeNode> stack = new Stack<>();
        while (biTree != null || !stack.isEmpty()) {
            while (biTree != null) {
                stack.push(biTree);
                biTree = biTree.left;
            }
            if (!stack.isEmpty()) {
                biTree = stack.pop();
                System.out.println(biTree.value);
                biTree = biTree.right;
            }
        }
    }

    public static void postOrderRe(TreeNode biTree) {//后序遍历递归实现
        if (biTree == null)
            return;
        else {
            postOrderRe(biTree.left);
            postOrderRe(biTree.right);
            System.out.println(biTree.value);
        }
    }

    public static void postOrder(TreeNode biTree) {//后序遍历非递归实现
        int left = 1;//在辅助栈里表示左节点
        int right = 2;//在辅助栈里表示右节点
        Stack<TreeNode> stack = new Stack<>();
        //辅助栈，用来判断子节点返回父节点时处于左节点还是右节点。
        Stack<Integer> stack2 = new Stack<>();

        while (biTree != null || !stack.empty()) {
            while (biTree != null) {//将节点压入栈1，并在栈2将节点标记为左节点
                stack.push(biTree);
                stack2.push(left);
                biTree = biTree.left;
            }

            while (!stack.empty() && stack2.peek() == right) {
                //如果是从右子节点返回父节点，则任务完成，将两个栈的栈顶弹出
                stack2.pop();
                System.out.println(stack.pop().value);
            }

            if (!stack.empty() && stack2.peek() == left) {
                //如果是从左子节点返回父节点，则将标记改为右子节点
                stack2.pop();
                stack2.push(right);
                biTree = stack.peek().right;
            }

        }
    }

    public static void levelOrder(TreeNode biTree) {//层次遍历
        if (biTree == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(biTree);
        TreeNode currentNode;
        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            System.out.println(currentNode.value);
            if (currentNode.left != null)
                queue.add(currentNode.left);
            if (currentNode.right != null)
                queue.add(currentNode.right);
        }
    }
}

