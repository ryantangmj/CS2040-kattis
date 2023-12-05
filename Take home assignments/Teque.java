import java.util.*;
import java.io.*;


class Teque {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        ConsolidatedListNode list = new ConsolidatedListNode();
        int num = Integer.parseInt(br.readLine());

        for (int i = 0; i < num; i++) {
            String[] input = br.readLine().split(" "); 

            if (!input[0].equals("get")) {
                if (input[0].equals("push_front")) {
                    list.pushFront(Integer.parseInt(input[1]));
                } else if (input[0].equals("push_back")) {
                    list.pushBack(Integer.parseInt(input[1]));
                } else {
                    list.pushMiddle(Integer.parseInt(input[1]));
                }
            } else {
                pw.println(list.getElement(Integer.parseInt(input[1])).item);
            }
        }

        pw.flush();
    }
}


class ConsolidatedListNode {
    int size;
    int middle_index;
    ListNode head;
    ListNode middle;
    ListNode tail;
    
    public ConsolidatedListNode() {
        this.size = 0;
        this.middle_index = 0;
        this.head = null;
        this.middle = null;
        this.tail = null;
    }
    
    public ConsolidatedListNode(int size, int middle_index, ListNode head, ListNode middle, ListNode tail) {
        this.size = size;
        this.middle_index = middle_index;
        this.head = head;
        this.middle = middle;
        this.tail = tail;
    }

    public void pushFront(int item) {
        ListNode element = new ListNode(item);
        
        if (size == 0) {
            head = element;
            middle = element;
            tail = element;
            size++;
        } else {
            head.setPrev(element);
            element.setNext(head);
            head = element;
            size++;
            middle_index++;

            while (middle_index - (size - middle_index) > 1) {
                middle = middle.prev;
                middle_index--;
            }
        }
        
    }

    public void pushBack(int item) {
        ListNode element = new ListNode(item);

        if (size == 0) {
                head = element;
                middle = element;
                tail = element;
                size++;
            } else {
                tail.setNext(element);
                element.setPrev(tail);
                tail = element;
                tail.setNext(tail);
                size++;
                middle = this.getElement(size / 2);
                middle_index = size / 2;
            }
    }

    public void pushMiddle(int item) {
        ListNode element = new ListNode(item);

        if (size == 0) {
           pushFront(item);
        } else if (size == 1) {
            pushBack(item);
        } else {
            ListNode inFront = this.getElement((size + 1) / 2 - 1);
            ListNode behind = inFront.next;
            element.setPrev(inFront);
            inFront.setNext(element);
            element.setNext(behind);
            behind.setPrev(element);
            middle = element;
            size++;
            middle_index = size / 2;
        }
    }


    public ListNode getElement(int index) {
        ListNode temp;

        if (index == middle_index) {
            return middle;
        } else if (index < middle_index) {
            if (index < middle_index / 2) {
                temp = head;
                for (int i = 0; i < index; i++) {
                    temp = temp.next;
                }
            } else {
                temp = middle;
                for (int i = middle_index - index; i > 0; i--) {
                    temp = temp.prev;
                }
            }   
        } else {
            if (index < size *  3 / 4) {
                temp = middle;
                for (int i = 0; i < index - middle_index; i++) {
                    temp = temp.next;
                }
            } else {
                temp = tail;
                for (int i = size - index - 1; i > 0; i--) {
                    temp = temp.prev;
                }
            }    
        }

        return temp;
    }
}


class ListNode {
    int item;
    ListNode prev;
    ListNode next;
    
    public ListNode(int item) {
        this.item = item;
        this.prev = null;
        this.next = null;
    }
    
    public void setPrev(ListNode prev) {
        this.prev = prev;
    }
   
    public void setNext(ListNode next) {
        this.next = next;
    }
}