package ch1_3;

import java.util.Iterator;
import java.util.Scanner;

public class Stack<Item> implements Iterable<Item>{
    public static void main(String[] args){
        Stack<Integer> stack = new Stack<Integer>();
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
                    stack.push(Integer.parseInt(s));
            }
        }catch (NumberFormatException e){
            System.out.println("输入错误");
            System.out.println(input);
            return;
        }

        for(int i : stack)
            System.out.println(i);
    }
    private Item[] a = (Item[]) new Object[1];
    private int N;
    Stack(){
        //创建空栈
    }

    void push(Item item){
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

    Item pop(){
        return a[--N];
    }

    boolean isEmpty(){
        return N == 0;
    }

    int size(){
        return N;
    }

    public Iterator<Item> iterator(){
        return new stackIterator();
    }

    public class stackIterator implements Iterator<Item>{
        private int i = N;
        public boolean hasNext() { return i > 0; }
        public Item next() { return a[--i]; }
        public void remove() {}
    }
}
