package edu.pitt.cs.as3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * This class uses two stacks to evaluate an infix arithmetic expression from an
 * InputStream. It should not create a full postfix expression along the way; it
 * should convert and evaluate in a pipelined fashion, in a single pass.
 */
public class InfixExpressionEvaluator {
   
    StreamTokenizer tokenizer;

    StackInterface<Character> operatorStack;
    StackInterface<Double> operandStack;

    public InfixExpressionEvaluator(InputStream input) {
      
        tokenizer = new StreamTokenizer(new BufferedReader(
                new InputStreamReader(input)));
        tokenizer.ordinaryChar('-');
        tokenizer.ordinaryChar('/');


        tokenizer.eolIsSignificant(true);

        operatorStack = new ArrayStack<Character>();
        operandStack = new ArrayStack<Double>();
    }

    /**
     * Parses and evaluates the expression read from the provided input stream,
     * then returns the resulting value
     * @return the value of the infix expression that was parsed
     */

    public Double evaluate() throws ExpressionError {
        int previousToken=-1;

        try {
            tokenizer.nextToken();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (tokenizer.ttype != StreamTokenizer.TT_EOL) {
            
            switch (tokenizer.ttype) {
                case StreamTokenizer.TT_NUMBER:
                    // If the token is a number, process it as a double-valued
                    // operand
                    handleOperand((double)tokenizer.nval);
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                case '^':
                    // If the token is any of the above characters, process it
                    // is an operator
                    handleOperator((char)tokenizer.ttype);
                    break;
                case '(':
                case '<':
                    // If the token is open bracket, process it as such. Forms
                    // of bracket are interchangeable but must nest properly.
                    handleOpenBracket((char)tokenizer.ttype);
                    break;
                case ')':
                case '>':
                    // If the token is close bracket, process it as such. Forms
                    // of bracket are interchangeable but must nest properly.
                    handleCloseBracket((char)tokenizer.ttype);
                    break;

                case StreamTokenizer.TT_WORD:
                    
                    throw new ExpressionError("Unrecognized token: " +
                            tokenizer.sval);
                default:
                    
                    throw new ExpressionError("Unrecognized token: " +
                            String.valueOf((char) tokenizer.ttype));
            }

            try {
                tokenizer.nextToken();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        handleRemainingOperators();

        if(!operandStack.isEmpty())
            return operandStack.pop();
        else
            return 0.0;
    }

    /**
     * This method is called when the evaluator encounters an operand. It
     * manipulates operatorStack and/or operandStack to process the operand
     * according to the Infix-to-Postfix and Postfix-evaluation algorithms.
     * @param operand the operand token that was encountered
     */    
    void handleOperand(double operand) {
        operandStack.push(operand);
    }

    /**
     * This method is called when the evaluator encounters an operator. It
     * manipulates operatorStack and/or operandStack to process the operator
     * according to the Infix-to-Postfix and Postfix-evaluation algorithms.
     * @param operator the operator token that was encountered
     */
    void handleOperator(char operator) {
        int value = 0, topValue = 0;
        boolean completed = false;
        char next;
        switch (operator) {
            case '+':
                value = 1;
                break;
            case '-':
                value = 1;
                break;
            case '*':
                value = 2;
                break;
            case '/':
                value = 2;
                break;
            case '^':
                operatorStack.push(operator);
                completed = true;
        }

        while (!completed) {
            if (operatorStack.isEmpty()) {
                operatorStack.push(operator);
                completed = true;
            } else {
                switch (operatorStack.peek()) {
                    case '+':
                        topValue = 1;
                        break;
                    case '-':
                        topValue = 1;
                        break;
                    case '*':
                        topValue = 2;
                        break;
                    case '/':
                        topValue = 2;
                        break;
                    case '^':
                        topValue = 3;
                }
            }

            if (value > topValue && !completed) {
                completed = true;
                operatorStack.push(operator);
            } else if(value<=topValue && ! completed){
                next = operatorStack.pop();

                switch (next) {
                    case '+':
                        operandStack.push(operandStack.pop() + operandStack.pop());
                        break;
                    case '-':
                        operandStack.push((-1 * operandStack.pop()) + operandStack.pop());
                        break;
                    case '*':
                        operandStack.push(operandStack.pop() * operandStack.pop());
                        break;
                    case '/':
                        operandStack.push((1 / operandStack.pop()) * operandStack.pop());
                        break;
                    case '^':
                        double tempPow = operandStack.pop();
                        double tempOperand = operandStack.pop();
                        operandStack.push(Math.pow(tempOperand, tempPow));
                }
            }
        }
    }

    /**
     * This method is called when the evaluator encounters an open bracket. It
     * manipulates operatorStack and/or operandStack to process the open bracket
     * according to the Infix-to-Postfix and Postfix-evaluation algorithms.
     * @param openBracket the open bracket token that was encountered
     */    
    void handleOpenBracket(char openBracket) {
        operatorStack.push(openBracket);
    }

    /**
     * This method is called when the evaluator encounters a close bracket. It
     * manipulates operatorStack and/or operandStack to process the close
     * bracket according to the Infix-to-Postfix and Postfix-evaluation
     * algorithms.
     * @param closeBracket the close bracket token that was encountered
     */
    void handleCloseBracket(char closeBracket) throws ExpressionError {
        int val;
        if (closeBracket==')')
            val=1;
        else
            val=2;

        boolean completed = false;
        char next;
        while (!completed) {
            next = operatorStack.pop();
            switch (next) {
                case '(':
                    if(val != 1)
                        throw new ExpressionError("Error: Parenthesised in incorrect order");
                    else completed=true;
                    break;
                case '[':
                    if(val != 2)
                        throw new ExpressionError("Error: Parenthesised in incorrect order");
                    else completed=true;
                    break;
                case '+':
                    operandStack.push(operandStack.pop() + operandStack.pop());
                    break;
                case '-':
                    operandStack.push((-1 * operandStack.pop()) + operandStack.pop());
                    break;
                case '*':
                    operandStack.push(operandStack.pop() * operandStack.pop());
                    break;
                case '/':
                    operandStack.push((1 / operandStack.pop()) * operandStack.pop());
                    break;
                case '^':
                    double pow = operandStack.pop();
                    double operand = operandStack.pop();
                    operandStack.push(Math.pow(operand, pow));
            }
        }
    }

    /**
     * This method is called when the evaluator encounters the end of an
     * expression. It manipulates operatorStack and/or operandStack to process
     * the operators that remain on the stack, according to the Infix-to-Postfix
     * and Postfix-evaluation algorithms.
     */    
    void handleRemainingOperators() {
        double pop1,pop2;
        while(!operatorStack.isEmpty()){
            char next;

            next = operatorStack.pop();

            if(!operandStack.isEmpty())
                pop1=operandStack.pop();
            else
                throw new ExpressionError("Expression contains too few operands");
            if(!operandStack.isEmpty())
                pop2=operandStack.pop();
            else
                throw new ExpressionError("Expression contains too few operands");


            switch (next) {
                case '+':
                    operandStack.push(pop1 + pop2);
                    break;
                case '-':
                    operandStack.push((-1 * pop1) + pop2);
                    break;
                case '*':
                    operandStack.push(pop1 * pop2);
                    break;
                case '/':
                    operandStack.push((1 / pop1) * pop2);
                    break;
                case '^':
                    operandStack.push(Math.pow(pop2, pop1));
            }
        }
    }

    /**
     * Creates an InfixExpressionEvaluator object to read from System.in, then
     * evaluates its input and prints the result.
     * @param args not used
     */

    public static void main(String[] args) {
        System.out.print("Infix expression: ");
        InfixExpressionEvaluator evaluator =
                new InfixExpressionEvaluator(System.in);
        Double value = null;
        try {
            value = evaluator.evaluate();
        } catch (ExpressionError e) {
            System.out.println("ExpressionError: " + e.getMessage());
        }
        if (value != null) {
            System.out.println(value);
        } else {
            System.out.println("Evaluator returned null");
        }
    }

}