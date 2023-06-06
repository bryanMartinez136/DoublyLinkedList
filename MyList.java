public interface MyList{
    //insert 'item' at 'index'. Must rearrange pointers.
    public void insert(int index, Object item);
    
    //insert 'item' at the end of the list.
    public void append(Object item);
    
    //clear the entire list.
    public void clear();
    
    // return true if list is empty, false otherwise.
    public boolean isEmpty();
    
    // return the size of the list, else -1. Must require O(1) ops.
    public int size();
    
    // replaces the existing Object  at 'index' with 'item'.
    // return true, if replace succeeds.
    // return false, otherwise.
    public boolean replace(int index, Object item);

    // removes the element at 'index'.
    public void remove(int index);

    // return the element at 'index', if it exists.
    // return null otherwise.
    // Do not remove the item at 'index'.
    public Object get(int index);
}