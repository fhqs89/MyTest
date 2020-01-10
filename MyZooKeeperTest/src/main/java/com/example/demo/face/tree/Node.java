package com.example.demo.face.tree;

import java.util.ArrayList;

public class Node {
	public int value;//值
	public ArrayList<Node> nexts;//所有子节点
	
	public Node(int value) {
		this.value = value;
		this.nexts = new ArrayList<Node>();
	}
	
	public void setNexts(ArrayList<Node> nexts) {
		this.nexts = nexts;
	}
}
