/** Implementation of a simple non-empty parametric linked list type */
public class LinkedList <E> {
    /** Node's value */
    private E value;
    /** Next node */
    private LinkedList <E> next;

    /** Construct a list with a single element v */
    LinkedList(E v) {
        value = v;
        next = null;
    }

    /** Return a list with element n added to it */
    public LinkedList <E> add(E v) {
        LinkedList <E> n = new LinkedList <E>(v);
        n.next = this;
        return n;
    }

    /** Return the element at the list's head */
    public E getHead() {
        return value;
    }

    /** Return the list's tail */
    public LinkedList<E> getTail() {
        return next;
    }

    /** Return a string representation of the list */
    public String toString() {
        String me = value.toString();

        /* Recursive implementation */
        if (next == null)
            return me;
        else
            return me + " -> " + next;
    }

    /** Return the number of elements in the list */
    public int size() {
        /* Recursive implementation */
        if (next == null)
            return 1;
        else
            return next.size() + 1;
    }

    /** Return true if the specified element exists in the list */
    public boolean contains(E e) {
        /* Iterative implementation */
        for (LinkedList <E> i = this; i != null; i = i.next)
            if (i.value.equals(e))
                return true;
        return false;
    }

    /** Test harness */
    static public void main(String args[]) {
        LinkedList <Integer> ilst = new LinkedList <Integer>(0);

        ilst = ilst.add(1);
        ilst = ilst.add(18);
        ilst = ilst.add(45);

        for (int i = 0; i < 5; i++)
            ilst = ilst.add(i * 10);
        System.out.println(ilst);
    }
}