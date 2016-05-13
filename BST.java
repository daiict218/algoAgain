import java.util.Scanner;
class node{
	private node left, right;
	private int data;

	node(int data){
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public void setData(int data){
		this.data = data;
	}

	public int getData(){
		return this.data;
	}

	public void setLeft(node left){
		this.left = left;
	}

	public node getLeft(){
		return this.left;
	}

	public void setRight(node right){
		this.right = right;
	}

	public node getRight(){
		return this.right;
	}
}

class BST{
	public node root;

	BST(){
		this.root = null;
	}

	public void insert(int data){
		node nodeToInsert = new node(data);
		insert(root, nodeToInsert);
	}

	public void insert(node a, node nodeToInsert){
		if(root == null){
			root = nodeToInsert;
		}
		else {
			if(nodeToInsert.getData() < a.getData()){
				if(a.getLeft() == null){
					a.setLeft(nodeToInsert);
				}
				else{
					insert(a.getLeft() , nodeToInsert);
				}
			}
			else{
				if(a.getRight() == null){
					a.setRight(nodeToInsert);
				}
				else{
					insert(a.getRight() , nodeToInsert);
				}
			}
		}
	}

	public node search(int data){
		return search(root, data);
	}

	public node search(node root, int data){
		if(root == null){
			return null;
		}
		if(root.getData() == data){
			return root;
		}
		else if(root.getData() > data){
			return search(root.getLeft() , data);
		}
		else{
			return search(root.getRight() , data);
		}
	}

	public int minimum(){
		node r = root;
		if(r == null){
			System.out.println("No minimum value");
			return -1;
		}
		while(r.getLeft() != null){
			r = r.getLeft();
		}
		return r.getData();
	}

	public int maximum(){
		node r = root;
		if(r == null){
			System.out.println("No minimum value");
			return -1;
		}
		while(r.getRight() != null){
			r = r.getRight();
		}
		return r.getData();
	}

	public void inOrder(node root){
		if(root!=null)
		{
			inOrder(root.getLeft());
			System.out.print(root.getData()+" ");
			inOrder(root.getRight());
		}
	}

	public node delete(int data){
		
	}
}

class main{
	public static void main(String argsp[]){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter total number of nodes in binary search tree");
		int n = input.nextInt();
		BST b = new BST();
		System.out.println("Enter data one by one");
		for(int i=0;i<n;i++)
		{
			b.insert(input.nextInt());
		}
		System.out.println("Inorder traversal");
		b.inOrder(b.root);
		System.out.println();
		System.out.println("Enter the data you want to search");
		int data = input.nextInt();
		node x = b.search(data);
		if(x == null){
			System.out.println("No Data Found");
		} else {
			System.out.println(x.getData());
		}
		System.out.println(b.minimum());
		System.out.println(b.maximum());
	}
}
