/*
 * Ali Hooman
 * 
 * List.java
 */
public class List {

    /*
     * Private inner node class
     */
    private class Node {

        // Fields
        int data;
        Node next;
        Node previous;

        // Constructor
        public Node(int data) {
            this.data = data;
            next = null;
            previous = null;
        }   // End Node() constructor

        // toString()
        public String toString() {
            return String.valueOf(data);
        }   // End toString()

    }   // End Node

    // Fields
    private Node front;
    private Node back;
    private Node cursor;
    private int length;
    private int index;

    /*
     * List() constructor creates empty list.
     */
    public List() {
        front = null;
        back = null;
        cursor = null;
        length = 0;
        index = -1;
    }   // End List() constructork

    /*
     * Checks if List is empty. Returns true if empty,
     * false if not empty.
     */
    boolean isEmpty() {
        return length == 0;
    }   // End isEmpty()

    /*
     * Returns the number of elements in list.
     */
    public int length() {
        return this.length;
    }   // End length()

    /*
     * If cursor is defined, returns index of cursor element, else -1.
     */
    public int index() {
        if( cursor == null ) { return -1; }
        else { return index; }
    }   // End index()

    /*
     * Returns front element. Pre: length() > 0
     */
    public int front() {
        if( this.isEmpty() ) {
            throw new RuntimeException("List Error: front() called an empty list");
        }
        return front.data;
    }   // End front()

    /*
     * Returns back element. Pre: length() > 0
     */
    public int back() {
        if( this.isEmpty() ) {
            throw new RuntimeException("List error: back() called an empty list");
        }
        return back.data;
    }   // End back()

    /*
     * Returns cursor element. Pre: length() > 0, index() >= 0
     */
    public int get() {
        if( this.isEmpty() ) {
            throw new RuntimeException("List error: get() called an empty list");
        }
        if( this.index() < 0 ) {
            throw new RuntimeException("List error: get() called on empty list");
        }
        else {
            return cursor.data;
        } 
    }   // End get()

    /*
     * Returns true IFF List and L are the same integer sequence. The states
     * of the cursors in the two lists are not used in determining equality.
     */
    public boolean equals(List L) {    
        
        boolean result = true;
        Node N = this.front;
        Node M = L.front;
        
        if( this.length == L.length ) {
            while( result && N != null ) {
                result = ( N.data == M.data );
                N = N.next;
                M = M.next;
            }
            return result;
        }
        else {
            return false;
        }
        }   // End equals(List L)

    /*
     * Resets this List to its original empty state
     */
    public void clear() {
        this.front = null;
        this.back = null;
        this.cursor = null;
        this.length = 0;
        this.index = -1;
    }   // End clear()

    /*
     * If list is non-empty, places cursor under front element, else nothing.
     */
    void moveFront() {
        if( !this.isEmpty() ) {
            cursor = this.front;        
            this.index = 0;
        }
    }   // End moveFront()

    /*
     * If list is non-empty, places cursor under back element, else nothing.
     */
    void moveBack() {
        if( !this.isEmpty() ) {
            cursor = this.back;        
            this.index = this.length - 1;
        }
    }   // End moveBack()

    /*
     * If cursor is defined and not front, moves cursor one step toward front,
     * if cursor is defined and at front, cursor becomes undefined,
     * if undefined, do nothing.
     */
    void movePrev() {
        if( cursor != null && cursor != this.front ) {
            cursor = cursor.previous;
            this.index--;
        }
        else if( cursor != null && cursor == this.front ) {
            cursor = null;
            this.index = -1;
        }
    }   // End movePrev()

    /*
     * If cursor is defined and not back, move cursor one step toward
     * back of list, if cursor is defined and at back, cursor becomes undefined.
     * If cursor is undefined, do nothing.
     */
    void moveNext() {
        if( cursor != null && cursor != this.back ) {
            cursor = cursor.next;
            this.index++;
        }
        else if( cursor != null && cursor == this.back ) {
            cursor = null;
            this.index = -1;
        }
    }   // End moveNext()

    /*
     * Insert new element into list. If non-empty, insert before front.
     */
    void prepend(int data) {    

        if( !this.isEmpty() ) {
            Node tmp = new Node(data);
            tmp.previous = null;
            
            this.front.previous = tmp;
            tmp.next = this.front;
            this.front = tmp;
            this.length++;
            if (this.index != -1)
                index++;
        }
        else {
            Node tmp = new Node(data);
            tmp.previous = null;
            tmp.next = null;
            this.front = tmp;
            this.back = tmp;
            this.length = 1;
        }
        
    }   // end prepend()

    /*
     * Insert new element into list, if non-empty, insert after back.
     */
    void append(int data) {
        
        if( !this.isEmpty() ) {
            Node tmp = new Node(data);
            tmp.next = null;
            
            this.back.next = tmp;
            tmp.previous = this.back;
            this.back = tmp;
            this.length++;
        }
        else {
            Node tmp = new Node(data);
            tmp.previous = null;
            tmp.next = null;
            this.front = tmp;
            this.back = tmp;
            this.length = 1;           
        }
    }   // End append()

    /*
     * Insert before cursor. Pre: length() > 0, index() >= 0.
     */
    void insertBefore(int data) {
        if( this.isEmpty() ) {
            throw new RuntimeException("List Error: Called an empty list");
        }
        else if( this.index() < 0 ) {
            throw new RuntimeException("List Error: Cursor is not in list.");
        }
        else if( this.index() == 0 ) {
            this.prepend(data);
        }
        else {
            Node tmp = new Node(data);
            
            tmp.previous = cursor.previous;
            tmp.next = cursor;
            cursor.previous.next = tmp;
            cursor.previous = tmp;
            this.length++;
            this.index++;
        }
    }   // End insertBefore()

    /*
     * Insert after cursor. Pre; length() > 0, index() >= 0.
     */
    void insertAfter(int data) {
        if( this.isEmpty() ) {
            throw new RuntimeException("List Error: Called an empty list");
        }
        else if( this.index() < 0 ) {
            throw new RuntimeException("List Error: Cursor is not in list.");
        }
        else if( this.index() == (this.length() - 1) ) {
            this.append(data);
        }
        else {
            Node tmp = new Node(data);

            tmp.next = cursor.next;
            tmp.previous = cursor;
            cursor.next.previous = tmp;
            cursor.next = tmp;
            this.length++;
        }
    }   // End insertAfter()

    /*
     * Deletes front element, Pre: length() > 0
     */
    void deleteFront() {
        if( this.isEmpty() ) {
            throw new RuntimeException("List Error: Called an empty list");
        }
        else if( this.length() == 1 ) {
            this.clear();
        }
        else {
            front.next.previous = null;
            front = front.next;
            this.length--;
            if (this.index > -1)
                this.index--;
        }
    }   // End deleteFront()

    /*
     * Deletes back element, Pre: length() > 0
     */
    void deleteBack () {
        if( this.isEmpty() ) {
            throw new RuntimeException("List Error: Called an empty list");
        }
        else if (back == front) {
            deleteFront();
        } else {
            if (cursor == back)
                index = -1;
            back.previous.next = null;
            back = back.previous;
            this.length--;
        }
    }   // End deleteBack()

    /*
     * Deletes cursor element, making cursor undefined.
     * Pre: length() > 0, index() >= 0
     */
    void delete() {
        if( this.isEmpty() ) {
            throw new RuntimeException("List Error: Called an empty list");
        }
        else if( this.index() < 0 ) {
            throw new RuntimeException("List Error: Cursor is not in list.");
        }
        else if( this.length() == 1 ) {
            this.clear();
        }
        else if( this.index() == 0 ) {
            this.deleteFront();
        }
        else if( this.index() == ( this.length() - 1 )) {
            this.deleteBack();
        }
        else {
            cursor.next.previous = cursor.previous;
            cursor.previous.next = cursor.next;
            this.length--; 
            this.index = -1;
            cursor = null;
        }
    }   // End delete()

    /*
     * Overrides Object's toString method. Returns a String representation
     * of this List consisting of a space separated sequence of integers,
     * with front on left.
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node N = front;
        if (length() > 0)
            sb.append(N.toString());
        while(N != null) {
            N = N.next;
            if (N != null) {
               sb.append(" ");
               sb.append(N.toString());
            }
        }
        return new String(sb);
    }   // End toString()

    /*
     * Returns a new list representing the same integer sequence as this List.
     * Cursor in the new list is undefined, regardless of state of cursor
     * in current list. List is unchanged.
     */
    List copy() {
        List Q = new List();
        Node N = this.front;

        while( N != null ) {
            Q.append(N.data);
            N = N.next;
        }
        
        return Q;
    }   // End copy()

}   // End class list
