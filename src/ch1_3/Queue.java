package ch1_3;

import java.util.Iterator;
import java.util.Scanner;

public class Queue<Item> implements Iterable<Item> {     //队列
    public static void main(String[] args){
        Queue<Integer> q = new Queue<Integer>();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        try {
            while (scanner.hasNext()) {
                input = scanner.nextLine();
                if (input.trim().equals("exit")) {
                    break;
                }
                String[] ss = input.split(" ");
                for (String s : ss)
                    q.enqueue(Integer.parseInt(s));
            }
        }catch (NumberFormatException e){
            System.out.println("输入错误");
            System.out.println(input);
            return;
        }

        int N = q.size();
        int[] a = new int[N];
        for(int i = 0; i < N; i++) {
            a[i] = q.dequeue();
            System.out.println(a[i]);
        }
    }
    private Item[] a = (Item[]) new Object[1];
    private int N;

    Queue(){
        //创建空队列
    }

    void enqueue(Item item){
        if(N < a.length){
            a[N++] = item;
        }
        else{
            Item[] temp = (Item[]) new Object[2*a.length];
            temp[N++] = item;
            System.arraycopy(a,0,temp,0,a.length);
            a = temp;
        }
    }

    Item dequeue(){
        Item item = a[0];
        for(int i = 0; i < N-1; i++)
            a[i] = a[i+1];
        N--;
        return item;
    }

    boolean isEmpty(){
        return N == 0;
    }

    int size(){
        return N;
    }

    public Iterator<Item> iterator(){
        return new queueIterator();
    }

    public class queueIterator implements Iterator<Item>{
        private int i = N;
        public boolean hasNext() { return i > 0; }
        public Item next() { return a[--i]; }
        public void remove() {}
    }
}
