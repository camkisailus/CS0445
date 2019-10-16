package edu.pitt.cs.as3;

import java.util.EmptyStackException;

public interface StackInterface<E> {
    //Adds a new entry to the top of this stack.
    public void push(E newEntry);

    //Removes and returns this stack's top entry.
  
    public E pop() throws EmptyStackException;

    // Retrieves this stack's top entry.
    public E peek() throws EmptyStackException;

    // Detects whether this stack is empty.
    public boolean isEmpty();

    //Removes all entries from this stack.
    public void clear();
}

