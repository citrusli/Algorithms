package ch1_3;

import java.util.Iterator;
import java.util.Scanner;

public class Bag<Item> implements Iterable<Item>{  //背包
    public static void main(String[] args){
        Bag<Double> numbers = new Bag<Double>();
        Scanner scanner = new Scanner(System.in);
        String input;
        try {
            while (scanner.hasNext()) {
                input = scanner.nextLine();
                if(input.trim().equals("exit")){
                    break;
                }
                String[] ss = input.split(" ");
                for(String s : ss)
                    numbers.add(Double.parseDouble(s));
            }
        }catch (NumberFormatException e){
            System.out.println("输入错误");
            return;
        }
        scanner.close();
        int N = numbers.size();

        double sum = 0.0;
        for (double x : numbers)
                sum += x;
        double mean = sum/N;

        sum = 0.0;
        for(double x : numbers)
            sum += (x-mean)*(x-mean);
        double std = Math.sqrt(sum/(N-1));

        System.out.println("平均值："+mean);
        System.out.println(""+std);
    }
    private Item[] a = (Item[]) new Object[1];   //背包元素
    private int N = 0;
    Bag(){
        //创建空背包
    }

    void add(Item item){
        //添加元素
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

    boolean isEmpty(){
        //背包是否为空
        return N == 0;
    }

    int size(){
        //背包大小
        return N;
    }

    public Iterator<Item> iterator(){
        return new BagIterator();
    }

    public class BagIterator implements Iterator<Item>{
        private int i = N;
        public boolean hasNext() { return i > 0; }
        public Item next() { return a[--i]; }
        public void remove() {}
    }
}
