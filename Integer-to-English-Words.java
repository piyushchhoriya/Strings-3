// In this problem, dividing the given number in 3-digit pairs, keeping english translation arrays, and building the string 
// accordingly

// Time Complexity : O(1) Because we are traversing based on the number of digits which is constant
// Space Complexity : O(1) 
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Solution {
    // Declare translation arrays
    String[] thousands = { " ", "Thousand", "Million", "Billion" };
    String[] below_20 = { " ", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
            "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
    String[] tens = { " ", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

    public String numberToWords(int num) {
        // If the num is 0, simply return zero
        if (num == 0) {
            return "Zero";
        }
        // Declare i for iterating over thousands array
        int i = 0;
        // Result string
        String result = "";
        // Iterate through all digits
        while (num > 0) {
            // Num mod 1000 will give us the last 3 digits ... Eg. 4,123,567,898 % 1000=898
            if (num % 1000 != 0) {
                // If it is first 3 digits, we dont attach anything to it, that is why
                // thousands[0] is "" empty string.
                result = magic(num % 1000) + thousands[i] + " " + result;
            }
            // 4,123,567,898 / 1000=4,123,567
            num = num / 1000;
            // Then for next pair of 3 digits that is 567, we want five hundred sixty seven
            // thousand, thousand at the end so increment i, which will give us thousand
            // from array of thousands
            i++;
        }
        return result.trim();
    }

    // Method that will return word conversion
    private String magic(int num) {
        // Check if the number is 0
        if (num == 0) {
            return "";
        }
        // num is < 20, we have array of below_20
        else if (num < 20) {
            return below_20[num] + " ";
        }
        // num <100, we will use tens array for first digit and second digit we get from
        // recursive call
        else if (num < 100) {
            return tens[num / 10] + " " + magic(num % 10);
        }
        // For 3 digit num, below_20 for first digit Eg. 467 so for 4 four from
        // below_20, then append hundred to it, and for remn 2 digits call this function
        // recursively
        else {
            return below_20[num / 100] + " Hundred " + magic(num % 100);
        }

    }
}