package util.listtest.binaryTree;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/29 19:53
 * @File : Node
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/4/29 19:53
 */
public class Node {
    //左子节点
    public Node leftNode;
    //又子节点
    public Node rightNode;
    //值
    public Object value;


    public static void main(String[] args){
        int randoms[] = new int[]{67, 7, 30, 73, 11, 0, 78, 81, 10, 74};
        Node roots = new Node();
        for(int number : randoms){
            roots.add(number);
        }
//        System.out.println("二叉树结果");
//        System.out.println(roots.toString());
        System.out.println("二叉树中序遍历结果：");
        System.out.println(roots.inorderTravelsal(roots));
        System.out.println("==============================分割线============================================");
        System.out.println("二叉树先序遍历结果：");
        System.out.println(roots.preorderTravelsal(roots));
        System.out.println("==============================分割线============================================");
        System.out.println("二叉树后序遍历结果：");
        System.out.println(roots.postorderTravelsal(roots));

    }


    /**
    * @Author maozp3
    * @Description: 往二叉树中增加值
    * @Date: 19:56 2019/4/29
    * @Param [value]
    * @return void
    **/
    public void add(Object v){
        //如果当前节点没有值，就把数据放在当前节点上
        if(value == null){
            value = v;
        }else{ //如果当前节点有值，就进行判断，新增的值与当前值的大小关系
            //新增的值，如果比当前存在的值小或者二者相等，就放入其左子树。否则就放入右子树
            if( (Integer)v-((Integer)value) <= 0){
                //左子树若不存在，就创建一个，然后添加。如果存在就直接添加。
                if(null == leftNode){
                    leftNode = new Node();
                }
                leftNode.add(v);
            }else{
                //又子树若不存在，就创建一个，然后添加。如果存在就直接添加。
                if(null == rightNode){
                    rightNode = new Node();
                }
                rightNode.add(v);
            }
        }

    }

    /**
    * @Author maozp3
    * @Description: 二叉树中序遍历
    * @Date: 20:41 2019/4/29
    * @Param [node]
    * @return java.util.List<java.lang.Object>
    **/
    public List<Object> inorderTravelsal(Node node){
        List<Object> values = new ArrayList<>();
        //左节点的遍历结果
        if(null != node.leftNode){
            values.addAll(leftNode.inorderTravelsal(node.leftNode));
        }
        //当前节点（中序遍历）
        values.add(node.value);
        //右节点的遍历结果
        if(null != rightNode){
            values.addAll(rightNode.inorderTravelsal(node.rightNode));
        }
        return values;
    }

    /**
    * @Author maozp3
    * @Description: 二叉树先序遍历
    * @Date: 20:45 2019/4/29
    * @Param [node]
    * @return java.util.List<java.lang.Object>
    **/
    public List<Object> preorderTravelsal(Node node){
        List<Object> values = new ArrayList<>();
        //当前节点（先序遍历）
        values.add(node.value);
        //左节点的遍历结果
        if(null != node.leftNode){
            values.addAll(leftNode.preorderTravelsal(node.leftNode));
        }
        //右节点的遍历结果
        if(null != rightNode){
            values.addAll(rightNode.preorderTravelsal(node.rightNode));
        }
        return values;
    }

    /**
    * @Author maozp3
    * @Description: 二叉树后序遍历
    * @Date: 20:47 2019/4/29
    * @Param [node]
    * @return java.util.List<java.lang.Object>
    **/
    public List<Object> postorderTravelsal(Node node){
        List<Object> values = new ArrayList<>();
        //左节点的遍历结果
        if(null != node.leftNode){
            values.addAll(leftNode.postorderTravelsal(node.leftNode));
        }
        //右节点的遍历结果
        if(null != rightNode){
            values.addAll(rightNode.postorderTravelsal(node.rightNode));
        }
        //当前节点（后序遍历）
        values.add(node.value);

        return values;
    }



    /*@Override
    public String toString() {
        return "Node{" +
                "leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                ", value=" + value +
                '}';
    }*/
}
