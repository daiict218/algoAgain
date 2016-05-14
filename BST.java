import java.util.Scanner;
import java.util.Stack;
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

	public int minimum(node r){
		if(r == null){
			System.out.println("No minimum value");
			return -1;
		}
		while(r.getLeft() != null){
			r = r.getLeft();
		}
		return r.getData();
	}

	public int maximum(node r){
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


	//This is a wrapper method over the delete function
	public void delete(int data){
		root =  delete(root, data);
	}


	//The delete function can change the root of the tree. So, it will return the node of the tree from which it is deleting the data
	public node delete(node root, int data){
		// If the root is null, then it will simply return null
		if(root == null){
			return null;
		}
		else if(root.getData() > data){
			//if the data which is to be deleted is on the left side of BST then we will recursively call delete and set the left of the root returned by that recursive delete function
			root.setLeft(delete(root.getLeft(), data));
		}
		else if(root.getData() < data){
			//if the data which is to be deleted is on the right side of BST then we will recursively call delete and set the right of the root returned by that recursive delete function
			root.setRight(delete(root.getRight(), data));
		}
		else{
			// if the data is to be deleted is current data and it has only left child then the new root for the current subtree would be it's left child
			if(root.getRight() == null){
				return root.getLeft();
			}
			// similarly if the data is to be deleted is current data and it has only right child then the new root for the current subtree would be it's right child
			if(root.getLeft() == null){
				return root.getRight();
			}
			//if the current node which is to be deleted has both the children then we will find minimum from the right subtree and set that data to current node
			root.setData(minimum(root.getRight()));
			//and then call delete recursively on the right side of the subtree to delete the data (minimum) which is copied.
			root.setRight(delete(root.getRight(), root.getData()));
		}
		return root;
		// ?? what if we can't access the data and just has to delete the node with the pointers???
	} 

	public void inOrderStack(node root){
		node r = root;
		boolean done = false;
		Stack<node> s = new Stack<node>();
		while(!done){
			if(r != null){
				s.push(r);
				r = r.getLeft();
			}
			else{
				if(s.isEmpty()){
					done = true;
				}
				else{
					node x = s.pop();
					System.out.print(x.getData()+" ");
					r = x.getRight();
				}
			}
		}
		System.out.println();
	}

	public void preOrderStack(node root){
		node r = root;
		boolean done = false;
		Stack<node> s = new Stack<node>();
		while(!done){
			if(r != null){
				System.out.print(r.getData()+" ");
				s.push(r);
				r = r.getLeft();
			}
			else{
				if(s.isEmpty()){
					done = true;
				}
				else{
					node x = s.pop();
					r = x.getRight();
				}
			}
		}
		System.out.println();
	}

	public int height(node r){
		if(r == null){
			return -1;
		} else {
			return Math.max(height(r.getLeft()) , height(r.getRight())) + 1;
		}
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
		// System.out.println();
		// System.out.println("Enter the data you want to search");
		// int data = input.nextInt();
		// node x = b.search(data);
		// if(x == null){
		// 	System.out.println("No Data Found");
		// } else {
		// 	System.out.println(x.getData());
		// }
		// System.out.println(b.minimum(b.root));
		// System.out.println(b.maximum(b.root));
		// System.out.println("Enter the data you want to delete");
		// data = input.nextInt();
		// b.delete(data);
		// System.out.println("Inorder traversal after delete");
		// b.inOrderStack(b.root);
		// System.out.println("preOrder traversal after delete");
		// b.preOrderStack(b.root);
		System.out.println("height of the tree is : ");
		System.out.println(b.height(b.root));
	}
}
