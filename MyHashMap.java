public class MyHashMap {
    class Node{
        public int key;
        public int val;
        public Node next;

        public Node(int key,int val) {
            this.key = key;
            this.val = val;
        }
    }
    public Node[] arr;
    public int size;


    /** Initialize your data structure here. */
    public MyHashMap() {
        this.arr = new Node[10];
    }

    /** value will always be non-negative. */
    public void put(int key, int val) {
        Node node = new Node(key,val);
        int index = key % arr.length;
        Node cur = arr[index];
        while(cur != null) {
            if(cur.key == key) {
                cur.val = val;
                return;
            }
            cur = cur.next;
        }
        node.next = arr[index];
        arr[index] = node;
        this.size++;
        if(loadFactor() > 0.75) {
            resize();
        }
    }
    public double loadFactor() {
        return this.size*1.0 / this.arr.length;
    }
    public void resize() {
        Node[] newArr = new Node[arr.length * 2];
        for(int i = 0; i < arr.length; i++) {
            Node cur = arr[i];
            while(cur != null) {
                int index = cur.key % newArr.length;
                Node curNext = cur.next;
                cur.next = newArr[index];
                newArr[index] = cur;
                cur = curNext;
            }
        }
        arr = newArr;
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = key % arr.length;
        Node cur = arr[index];
        while(cur != null) {
            if(cur.key == key) {
                return cur.val;
            }
            cur = cur.next;
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = key % arr.length;
        Node cur = arr[index];
        Node pre = null;
        while(cur != null) {
            if(key == cur.key) {
                if(pre == null){
                    arr[index] = cur.next;
                    this.size--;
                    return;
                } else {
                    pre.next = cur.next;
                    this.size--;
                    return;
                }
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
    }

    public void print() {
        for (int i = 0; i < arr.length; i++) {
            Node cur = arr[i];
            while(cur != null) {
                System.out.print(cur.val + " ");
                cur = cur.next;
            }
        }
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put(1,2);
        map.put(1,4);
        map.put(3,6);
        map.remove(1);
        map.print();
    }
}
