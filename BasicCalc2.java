
// Time Complexity : O(n) - n is the length of the string
// Space Complexity : O(1) 
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Solution {
    public int calculate(String s) {
        // Base case
        if (s == null || s.length() == 0) {
            return 0;
        }
       
        int calc = 0;
        int num = 0;
        int tail = 0;
        char lastSign = '+';
        // Iterate over the string and take one char at a time
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // If the char is digit, store it in num
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            // If it is operator or we are at the last char of string, evaluate the
            // expression based on the values present in num, calc and lastSign
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                // While evaluating, if the lastsign is plus, then add the value of num with
                // calc and store it in calc
                if (lastSign == '+') {
                    calc = calc + num;
                    // tail stores whatever operation we did, we added num so +num
                    tail = num;
                }
                // While evaluating, if the lastsign is minus, then substract the value of num
                // with calc and store it in calc
                else if (lastSign == '-') {
                    calc = calc - num;
                    // tail stores whatever operation we did, we substracted num so -num
                    tail = -num;
                }
                // While evaluating, if the lastsign is multiply, then first undo the last
                // operation, so substract tail from calc, then add to it mulitplication of tail
                // and num
                else if (lastSign == '*') {
                    calc = calc - tail + (tail * num);
                    // We added tail * num
                    tail = tail * num;
                }
                // While evaluating, if the lastsign is division, then first undo the last
                // operation, so substract tail from calc, then add to it division of tail
                // and num
                else if (lastSign == '/') {
                    calc = calc - tail + (tail / num);
                    // We added tail / num
                    tail = tail / num;
                }
                // Reset num to zero
                num = 0;
                // Change the lastsign to the current operator
                lastSign = c;
            }
        }
        // Return calc
        return calc;
    }
}

// In this problem, we are using stack to store the result of intermediate
// evaluation. We start iterating over the string, if we
// find a digit we store it in num, then when we find a operator, we store the
// num in stack based on type of operator, at last we
// pop all from stack and add them together and store in calc and return calc.

// Time Complexity : O(n) - n is the length of the string
// Space Complexity : O(n) stack space
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int calc = 0;
        int num = 0;
        // int tail=0;
        char lastSign = '+';
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                // Instead of storing in calc, we pushing to the stack intermediate evaluation
                // results
                if (lastSign == '+') {
                    st.push(num);
                } else if (lastSign == '-') {
                    st.push(-num);
                } else if (lastSign == '*') {
                    st.push(st.pop() * num);
                } else if (lastSign == '/') {
                    st.push(st.pop() / num);
                }
                num = 0;
                lastSign = c;
            }
        }
        // At last, adding the contents of stack
        while (!st.isEmpty()) {
            calc = calc + st.pop();
        }
        return calc;
    }
}