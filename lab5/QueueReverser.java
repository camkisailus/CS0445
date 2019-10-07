package cs445.lab5;

public class QueueReverser {
	public static <T> void reverseQueue(QueueInterface<T> queue) {
		// TODO: Make the stack to use for reversing the queue
		LinkedStack<T> stack = new LinkedStack<>();

		
		// TODO: Move all the elements from the queue to the stack
		while(!queue.isEmpty())
		{
			T item = queue.dequeue();
			stack.push(item);
		}
		
		// TODO: Move all of the elements back into the queue, reversing the original order
		while(!stack.isEmpty())
		{
			T item = stack.pop();
			queue.enqueue(item);
		}
		
		
	}
}