package edu.pitt.cs.as1;
/**
 * ShelfInterface is an interface that describes the operations of the ADT Shelf. A Shelf is a
 * 3 D collection of objects. The first two dimensions are the rows and column. The last dimension is the quantity
 */
public interface ShelfInterface<T> {

    /**
     * Determines the entry.
     *
     * @param row  The row of the object to be added.
     * @param column  The column of the object to be added
     * @param position  The position of the object to be added
     * @return T item the vending item in this position
     */
    public T get(int row, int column, int position);

    /**
     * Determines the name of the entry.
     *
     * @param row  The row of the object to be added.
     * @param column  The column of the object to be added
     * @param position  The position of the object to be added
     * @return  The String of name for this entry
     */
    public String getName(int row, int column, int position);

    /**
     * Adds a new entry to this shelf
     *
     * <p> If newEntry is not null then add the newEntry to the corresponding row, column and position.
     *
     * <p> If newEntry is null, then the entry is ignored
     * @param row  The row of the object to be added.
     * @param column  The column of the object to be added
     * @param position  The position of the object to be added
     * @param o  The object to be added as a new entry
     */
    public void add(int row, int column, int position, T o);


    /**
     * Removes an entry from this shelf
     *<p>
     * You need to remove the item on the position 0 every time and shift the values one position left.
     *</p>
     *
     * @param row  The row of the object to be added.
     * @param column  The column of the object to be added
     */
    public void remove(int row, int column);

    /**
     * Tests whether this shelf contains a given entry. Equality is determined
     * using the .equals() method.
     *
     * <p> If this shelf contains entry, then contains returns true. Otherwise
     * (including if this shelf is empty), contains returns false. If entry is
     * null, then remove throws IllegalArgumentException. The method never
     * modifies this set.
     *
     * @param entry  The entry to locate
     * @return  true if this shelf contains entry; false if not
     * @throws  false  If entry is null
     */
    public boolean contains(T entry);


    /**
     * Determines whether this shelf is empty for a specific item.
     *
     * @return true if this it is empty; false if not
     */
    public boolean isEmpty(int row, int column, int position);

    /**
     * Retrieves all entries that are in this Shelf.
     *
     * <p> A 3D array is returned that contains a reference to each of the entries
     * in this shelf.
     *
     * <p> If the implementation of set is array-backed, toArray will not return
     * the private backing array. Instead, a new array will be allocated with
     * the appropriate capacity.
     *
     * @return  A newly-allocated array of all the entries in this shelf
     */
    public T[][][] toArray();
}
