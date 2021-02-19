import java.util.HashSet;

public class MyHashSet {
    static class Node {
        public int key;
        public Node next;

        public Node(int key) {
            this.key = key;
        }
    }
    public Node[] arr;
    public int size;

    /** Initialize your data structure here. */
    public MyHashSet() {
        this.arr = new Node[10];
    }

    public void add(int key) {
        Node node = new Node(key);
        int index = key % arr.length;
        Node cur = arr[index];
        while(cur != null) {
            if(cur.key == key) {
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
            while (cur != null) {
                int index = cur.key % newArr.length;
                Node curNext = cur.next;
                cur.next = newArr[index];
                newArr[index] = cur;
                cur = curNext;
            }
        }
        arr = newArr;
    }

    public void remove(int key) {
        int index = key % arr.length;
        Node pre = null;
        Node cur = arr[index];
        while(cur != null) {
            if(key == cur.key) {
                if(pre == null) {
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

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int index = key % arr.length;
        Node cur = arr[index];
        while(cur != null) {
            if(key == cur.key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }
    public void print() {
        for (int i = 0; i < arr.length; i++) {
            Node cur = arr[i];
            while(cur != null) {
                System.out.print(cur.key + " ");
                cur = cur.next;
            }
        }
    }


    public static void main(String[] args) {

        MyHashSet set = new MyHashSet();
        set.add(1);
        set.add(1);
        set.add(2);
        set.remove(1);
        set.print();
//        System.out.println(set.contains(1));
//        System.out.println(set);
    }
}
