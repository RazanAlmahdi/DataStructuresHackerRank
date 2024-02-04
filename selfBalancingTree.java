	/* Class node is defined as :
    class Node 
    	int val;	//Value
    	int ht;		//Height
    	Node left;	//Left child
    	Node right;	//Right child

	*/
    public int height(Node n)
    {
        if (n == null)
            return -1;
        else
            return n.ht;
    }
    public void updateHeight(Node n)
    {
        n.ht = 1+Math.max(height(n.right), height(n.left));
    }
    public int getBalance(Node n)
    {
        if (n==null)
            return -1;
        else
            return height(n.right) - height(n.left);
    }
    public Node rotateRight(Node y)
    {
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }
    public Node rotateLeft(Node y)
    {
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }
    public Node rebalance(Node z)
    {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1)
        {
            if (height(z.right.right) > height(z.right.left))
                z = rotateLeft(z);
            else
            {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        }
            else if (balance < -1)
            {
                if(height(z.left.left) > height(z.left.right))
                    z = rotateRight(z);
                else
                {
                    z.left = rotateLeft(z.left);
                    z = rotateRight(z);
                }
            }
        return z;
    }
    public Node insert(Node root,int val)
    {
        if (root==null)
        {
            Node n = new Node();
            n.val = val;
            return n;
        }
        else if(root.val < val)
            root.right = insert(root.right, val);
        else if(root.val > val)
            root.left = insert(root.left, val);
        return rebalance(root);
    }
