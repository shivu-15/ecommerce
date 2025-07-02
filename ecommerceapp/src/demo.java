import java.util.Scanner;
class Main{

    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        int [] a = new int [5];
        for(int i =0;i<a.length;i++)
        {
            a[i]=sc.nextInt();
        }
        System.out.println(min(a));
      
    }
    public static int min(int [] a)
    {
        int min=0;
        for(int i =0;i<a.length;i++)
        {
            if(a[i]>min)
            {
                a[i]=min;
            }
        }
        return min;
    }
}