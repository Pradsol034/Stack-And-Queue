package local;
//907. Sum of Subarray Minimums

import java.util.Arrays;
import java.util.Stack;

public class SumofSubarrayMinimums {
    public static void main(String[] args) {
        // int []arr={1,4,6,7,3,7,8,1};
        int[]arr={3,1,2,4};
        Solution obj=new Solution();
        int ans=obj.sumSubarrayMins(arr);
        System.out.println(ans);
    }
}

//Having Only little bit problem
/* 
class Solution {
    public int sumSubarrayMins(int[] arr) 
    {
        //Finding the next smallest element
        //for that we traverse from back 
        int n=arr.length;
        int[]NextSmallest=new int[n];
        Stack<Integer> stack=new Stack<>();
        NextSmallest[n-1]=n;
        stack.push(n-1);
        for(int i=n-2 ;i>=0;i--)
        {
            while(!stack.isEmpty() && arr[stack.peek()]>arr[i])
            {
                stack.pop();
            }
            if(stack.isEmpty())
            {
                NextSmallest[i]=n;
            }else{
                NextSmallest[i]=stack.peek();
            }
            stack.push(i);
        }
        System.out.println(Arrays.toString(NextSmallest));

        //Finding the previous Smallest Element
        //for that we traverse from starting
        int[] PreviousSmallest=new int[n];
        stack.clear();
        PreviousSmallest[0]=-1;
        stack.push(0);
        for(int i=1;i<n;i++)
        {
            while (!stack.isEmpty() && arr[stack.peek()]>arr[i]) {
                stack.pop();
            }
            if(stack.isEmpty())
            {
                PreviousSmallest[i]=-1;
            }else{
                PreviousSmallest[i]=stack.peek();
            }
            stack.push(i);
        }
        System.out.println(Arrays.toString(PreviousSmallest));
        
        //Writing Main Code

        int result=0;
        int mod=(int)(1e9+7);
        for (int i = 0; i < arr.length; i++) {
            int right=NextSmallest[i]-i;
            int left=i-PreviousSmallest[i];
            result=(result+(int)(left*right*arr[i]))%mod;
        }
        return result;
    }
}
 */


 class Solution  extends HelperMethod{ // inherit the class 
    public int sumSubarrayMins(int[] arr) {
        
        HelperMethod help = new HelperMethod(); // object of helper fun to access the method.
        
        int[] nextSE = help.nextSmaller(arr);
        int[] prevSE = help.previousSmaller(arr);
        long total = 0;
        int mod = (int)(1e9+7);

        for (int i = 0; i < arr.length; i++) {
            int left = i - prevSE[i];
            int right = nextSE[i] - i;

            total = (total + (long)left * right * arr[i]) % mod;
        }
        return (int) total;
    }

}

//purpose of creating another class just coz of organized the code..

class HelperMethod{
    // Find the next smaller element
    public int[] nextSmaller(int[] arr) {
        int n = arr.length;
        int[] nse = new int[n];
        Stack<Integer> stk = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stk.isEmpty() && arr[stk.peek()] > arr[i]) {
                stk.pop();
            }
            nse[i] = (stk.isEmpty()) ? n : stk.peek();
            stk.push(i);
        }
        return nse;
    }

    // Find the previous smaller element
    public int[] previousSmaller(int[] arr) {
        int n = arr.length;
        int[] pse = new int[n];
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stk.isEmpty() && arr[stk.peek()] >= arr[i]) {
                stk.pop();
            }
            pse[i] = (stk.isEmpty()) ? -1 : stk.peek();
            stk.push(i);
        }
        return pse;
    }
}