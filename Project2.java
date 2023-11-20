package project2;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
//Cole Gauerke
public class Project2{
    public static boolean isNumeric(String a) { //Method that checks if values from the array are of numerical value
        try {                                   
            Double.parseDouble(a);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }    
    public static double calculate (double b, double a, String c) { //Method to calculate operations between two numbers from the stack
        double d = 0;
        if (c.equals("+")) {
            d = b + a;
        }
        else if (c.equals("-")) {
            d = a - b;
        }
        else if (c.equals("*")) {
            d = a * b;
        }
        else if (c.equals("/")) {
            d = a / b;
        }
        else if (c.equals("%")) {
            d = a % b;
        }
        else if (c.equals("^")) {
            d = Math.pow(a, b);
        }
        return d;
    }
    public static double factorial (double n) { //Seperate method for calculating factorials
        double m = 1, o;
        for (o = 2; o <= n; o++){
            m *= o;
        }
        return m;
    }
    public static void checkEq (String [] postfixEq) {  //Will send an error of there are not enough operators or numbers for the equation, making it invalid
        int count1 = 0, count2 = 0;
        for(int i = 0; i < postfixEq.length; i++) {
            if(postfixEq[i].equals("1") || postfixEq[i].equals("2") || postfixEq[i].equals("3") || postfixEq[i].equals("4") || postfixEq[i].equals("5") || postfixEq[i].equals("6") || postfixEq[i].equals("7") || postfixEq[i].equals("8") || postfixEq[i].equals("9")) {
                count1++;
            }
        }
        for(int a = 0; a < postfixEq.length; a ++ ){
            if (postfixEq[a].equals("+") || postfixEq[a].equals("-") || postfixEq[a].equals("*") || postfixEq[a].equals("/") || postfixEq[a].equals("^") || postfixEq[a].equals("%") || postfixEq[a].equals("!")){  
                count2++;
            }
        }
        if (count1 >= (count2 + 2)) {
            throw new Error("Invalid Postfix Equation");
        }
    }
    public static String printSoln (String [] postfixEq) {  //Prints the proper solution following order of operations
        Stack<String> num = new Stack<>();
        checkEq(postfixEq);
        String solution = "";
        for(int i = 0; i < postfixEq.length; i++) {
            if(isNumeric(postfixEq[i])) {
                num.push(postfixEq[i]);
            }
            else if (num.size() > 1) {
                if(postfixEq[i].equals("!")) {
                    String num0 = num.pop();
                    solution = "(" + num0 + postfixEq[i] + ")";
                    num.push(solution);
                }
                else {
                    String num1 = num.pop();
                    String num2 = num.pop();
                    solution = "(" + num2 + " " + postfixEq[i] + " " + num1 + ")";
                    num.push(solution);  
                }
            } 
            else if (num.size() == 1) {
                String num0 = num.pop();
                String solution0 = "(" + num0 + postfixEq[i] + ")";
                num.push(solution0);
            }
        
        }
        return num.pop();
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Stack<Double> num = new Stack<>();
        System.out.print("Enter the desired number of equations: ");
        int numLines = in.nextInt();
        in.nextLine();
        System.out.println("Enter the equations below: ");
        String [][] userInput = new String [numLines][];
        for(int i = 0; i < numLines; i++) {
            String input = in.nextLine();                
            userInput[i] = input.split(" ",100);    //Input is entered to an array of an array of Strings
        }
        System.out.println(Arrays.toString(userInput[0]));
        for (int i = 0; i < numLines; i++) {
            String [] inputArr = userInput[i];
            for (int a = 0; a < inputArr.length; a++) {
                if(isNumeric(inputArr[a])) {
                    double change = Double.parseDouble(inputArr[a]);    //push num into stacks until there is an operator
                    num.push(change);
                }
                else if (inputArr[a].equals("+") || inputArr[a].equals("-") || inputArr[a].equals("*") || inputArr[a].equals("/") || inputArr[a].equals("^") || inputArr[a].equals("%")){
                    num.push(calculate(num.pop(), num.pop(), inputArr[a])); //when there is an operator pop the last 2 values and calculate
                }
                else if (inputArr[a].equals("!")) {
                    num.push(factorial(num.pop()));
                }
                else{
                    throw new Error("Invalid Syntax");
                }                         
            }
            double ansDouble = num.pop();
            int ans = (int) ansDouble;
            System.out.print(printSoln(inputArr)+ " = " + ans + "\n");
        }
    }
}   

