import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

//Stacks and Queues
public class ChapterThree {
    //3.1 - Three in One
    /* Describe how you could use a single array to implement three stacks:
        I don't understand why you would do this, instead of just have 3 separate stacks
        the problem seems to want me to split the array so that there is room for each stack, but why...?
     */

    //3.2 - Stack Min


    //3.3 - Stack of Plates
    class SetOfStacks<T>{
        int stackCapacity;
        ArrayList<Stack<T>> stacks;

        public SetOfStacks(int capacity){
            stackCapacity = capacity;
            stacks = new ArrayList<>();
            stacks.add(new Stack<>());
        }

        public T pop(){
            Stack<T> currentStack = stacks.get(0);
            T result = currentStack.pop();
            if(currentStack.isEmpty())
                stacks.remove(0);

            return result;
        }

        public void push(T item){
            int i = 0;
            while(i < stacks.size() && stacks.get(i).size() == stackCapacity)
                i++;

            if(stacks.size() == i) {
                Stack<T> newStack = new Stack<>();
                newStack.add(item);
                stacks.add(newStack);
            } else {
                stacks.get(i).add(item);
            }
        }

        public T peek(){
            return stacks.get(0).peek();
        }



    }

    //3.4 - Queue via Stacks
    class MyQueue<T>{
        Stack<T> stack1, stack2;

        public MyQueue(){
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void add(T thing){
            stack1.add(thing);
        }

        public T remove(){
            if(stack2.isEmpty()){
                while(!stack1.isEmpty())
                    stack2.add(stack1.pop());
            }

            return stack2.pop();
        }

        public T peek(){
            if(stack2.isEmpty()){
                while(!stack1.isEmpty())
                    stack2.add(stack1.pop());
            }

            return stack2.peek();
        }


        public boolean isEmpty(){
            if(stack1.isEmpty() && stack2.isEmpty())
                return true;
            else
                return false;
        }

    }

    //3.5 - Sort Stack

    //3.6 - Animal Shelter
    class AnimalShelter{
        Queue<String> dogs, cats; //really I should make classes for these, but strings are good enough for this problem


        //enqueueDog

        //enqueueCat

        //dequeueAny

        //dequeueDog

        //dequeueCat
    }

}
