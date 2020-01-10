package com.example.demo.face.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Depth First Search 深度优先遍历
 * 
 * @author zhanghao105
 *
 */
public class DFSTest {
	

	public static void main(String[] args) {
		Node node = new Node(0);
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		ArrayList<Node> l34 = new ArrayList<Node>();
		l34.add(node3);l34.add(node4);
		node2.setNexts(l34);
		ArrayList<Node> l12 = new ArrayList<Node>();
		l12.add(node1);l12.add(node2);
		node.setNexts(l12);
		
		bfsSelf(node);
//		dfsSelf(node);
//		dfs(node);
//		bfs(node);
	}
	
	public static void dfsSelf(Node node) {
		if(node == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		HashSet<Node> set = new HashSet<>();
		stack.push(node);
		set.add(node);
		System.out.println(node.value);
		while(!stack.isEmpty()) {
			Node no = stack.pop(); //拿到节点
			for(Node next : no.nexts) { //遍历子节点
				if(!set.contains(next)) {
					stack.push(no);
					stack.push(next);
					set.add(next);
					System.out.println(next.value);
					//break是因为，a节点发现有子节点，立马返回继续遍历a的子节点
					//然后break 回溯 重新遍历该节点 看是否有子节点，没有就出栈，遍历父节点的另一个节点
					break; 
				}
			}
		}
		
	}
	
	
	
	/* 1.准备一个栈结构和一个Set结构的集合，栈用来记录还有孩子没有遍历到的节点，Set用来记录遍历的历史记录
	 * 2.将首节点加入到栈和Set中
	 * 3.弹栈拿到首节点
	 * 4.从首节点开始深度遍历
	 * 
	 * @param args
	 */
	public static void dfs(Node node) {
		if(node == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		Set<Node> set = new HashSet<Node>();
		
		stack.add(node);
		set.add(node);
		System.out.println(node.value);
		
		while(!stack.isEmpty()) {
			//弹栈获得一个节点
			Node cur = stack.pop();
			//查看这个节点的所有孩子
			for(Node next : cur.nexts) {
				//如果有孩子是之前没有遍历到的，说明这个节点没有深度遍历完
				if(!set.contains(next)) {
					//将此节点与其孩子加入栈中与set中
					stack.push(cur);
					stack.push(next);
					set.add(next);
					System.out.println(next.value);
					break;
				}
			}
		}
	}
	
	public static void bfsSelf(Node node) {
		Queue<Node> queue = new LinkedList<Node>();
		HashSet<Node> set = new HashSet<Node>();
		queue.add(node);
		set.add(node);
//		System.out.println(node.value);
		while(!queue.isEmpty()) {
			Node no = queue.poll();
			System.out.println(no.value);
			for(Node nt : no.nexts) {
				if(!set.contains(nt)) {
					queue.add(nt);
					set.add(nt);
				}
			}
		}
	}
	
	/*
	 * 利用队列来将下一层的所有节点记录下来，然后顺序遍历就可以了
	 * 用set存储已添加的队列节点，防止重复添加
	 */
	public static void bfs(Node node) {
		if(node == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<Node>();
		//用来注册已加入队列的节点-->防止重复添加节点
		HashSet<Node> set = new HashSet<Node>();
		queue.add(node);
		set.add(node);
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.println(cur.value);
			for(Node next : cur.nexts) {
				if(!set.contains(next)) {
					queue.add(next);
					set.add(next);
				}
			}
		}
	}
	
	
}
