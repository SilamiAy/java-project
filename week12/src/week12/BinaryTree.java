package week12;

public class BinaryTree {
    Node root;
    int count = 0;
    public BinaryTree(){
        Node root;
    }
    boolean isEmpty(){
        return root == null;
    }
    void add(int data){
        if (isEmpty()) {//tree is empty
            root = new Node(data);
        }
        else{
            Node current = root;
            while(true){
                if (data<current.data) {
                    if (current.left!=null) {
                        current = current.left;
                    }
                    else{
                        current.left = new Node(data);
                        break;
                    }
                }
                else if(data>current.data){
                    if (current.right!=null) {
                        current = current.right;
                    }
                    else{
                        current.right = new Node(data);
                        break;
                    }
                }
                else{//data is already exist
                    break;
                }
            }
        }
    }
    boolean find(int data){
        boolean hasil = false;
        Node current = root;
        while(current!=null){
            if (current.data==data) {
                hasil = true;
                break;
            }
            else if(data<current.data){
                current = current.left;
            }
            else{
                current = current.right;
            }
        }
        return hasil;
    }
    void traversePreOrder(Node node){
        if (node != null) {
            System.out.println(" "+node.data);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }
    void traversePostOrder(Node node){
        if(node!= null){
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.println(" "+node.data);
        }
    }
    void traverseInOrder(Node node){
        if (node!=null) {
            traverseInOrder(node.left);
            System.out.println(" "+node.data);
            traverseInOrder(node.right);
        }
    }
    Node getSuccessor(Node del){
        Node successor = del.right;
        Node successorParent = del;
        while(successor.left!=null){
            successorParent = successor;
            successor = successor.left;
        }
        if(successor!=del.right){
            successorParent.left = successor.right;
            successor.right = del.right;
        }
        return successor;
    }
    void delete(int data){
        if (isEmpty()) {
            System.out.println("Tree is empty!");
            return;
        }
        //find node (current) that will be deleted
        Node parent = root;
        Node current = root;
        boolean isLeftChild = false;
        while(current != null){
            if (current.data ==data) {
                break;
            }else if(data<current.data){
                parent = current;
                current = current.left;
                isLeftChild = true;
            }else if(data> current.data){
                parent = current;
                current = current.right;
                isLeftChild = false;
            }
        }
        //deletion
        if(current == null){
            System.out.println("couldn't find data!");
            return;
        }else{
            //if there is no child, simply delete it
            if(current.left==null&& current.right==null){
                if (current == root) {
                    root=null;
                }else{
                    if (isLeftChild) {
                        parent.left=null;
                    }else{
                        parent.right = null;
                    }
                }
            }
            else if(current.left==null){//if there is 1 child (right)
                if(current == root){
                    root = current.right;
                }else{
                    if(isLeftChild){
                        parent.left = current.right;
                    }else{
                        parent.right=current.right;
                    }
                }
            }
            else if(current.right==null){//if there is 1 child (left)
                if (current==root) {
                    root=current.left;
                }
                else{
                    if (isLeftChild) {
                        parent.left = current.left;
                    }
                    else{
                        parent.right = current.left;
                    }
                }
            }
            else{//if there are 2 childs
                Node successor = getSuccessor(current);
                if (current==root) {
                    root = successor;
                }
                else{
                    if(isLeftChild){
                        parent.left = successor;
                    }
                    else{
                        parent.right = successor;
                    }
                }
                successor.left = current.left;
            }
        }
    }
    void printLeaf(Node node){
        if (node != null) {
            if (node.left == null&&node.right==null) 
                System.out.print(" " + node.data);
            printLeaf(node.left);
            printLeaf(node.right);
        }
    }
    void addrec(int data, Node rec){
        if (isEmpty()) {//tree is empty
            root = new Node(data);
            addrec(data, root);
        }
        else{
            if (data<rec.data) {
                if (rec.left!=null) {
                    rec = rec.left;
                    addrec(data, rec);
                }
                else{
                    rec.left = new Node(data);
                    addrec(data,rec.left);
                }
            }
            else if(data>rec.data){
                if (rec.right!=null) {
                    rec = rec.right;
                    addrec(data,rec);
                }
                else{
                    rec.right = new Node(data);
                    addrec(data,rec.right);
                }
            }
            else{
                
            }
        }
    }
    void small(Node begin){
        if (begin.left!=null) {
            begin=begin.left;
            small(begin);
        }else if (begin.right!=null) {
            begin=begin.right;
            small(begin);
        }else{
            System.out.println("smallest: "+begin.data);
        }
    }
    void big(Node begin){
        if(begin.right!=null){
            begin = begin.right;
            big(begin);
        }else if (begin.left!=null) {
            begin = begin.left;
            big(begin);
        }else{
            System.out.println("biggest: "+begin.data);
        }
    }
    void countLeaf(Node node){
        if (node != null) {
            if (node!=null&&node.left == null&&node.right==null) {
                count++;
            }
            if (node.left == null&&node.right==null){ 
                System.out.print(" " + node.data);
            }
            countLeaf(node.left);
            countLeaf(node.right);
        }
    }        
    void countPrint(){
        System.out.println("count: "+count);
    }
}

