import java.io.*;
import java.util.*;

class Joinstrings {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        int num = Integer.parseInt(br.readLine());
        ListNode[] list = new ListNode[num + 1];

        for (int i = 0; i < num; i++) {
            list[i] = new ListNode(br.readLine());
        }

        int a = 0;
        for (int i = 0; i < num - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            list[a].getLast().setNext(list[b]);
            list[a].setLast(list[b].getLast());
        }

        ListNode element = list[a];
        while (element != null) {
            pw.print(element.getItem());
            element = element.getNext();
        }

        pw.println();
        pw.flush();     
    }
}

class ListNode {
    public String str;
    public ListNode next;
    public ListNode last;

    public ListNode(String val) {
        this.str = val;
        this.next = null;
        this.last = this;
    }

    public ListNode(String val, ListNode n, ListNode last) {
        this.str = val;
        this.next = n;
        this.last = last;
    }

    public ListNode getNext() {
        return next;
    }

    public ListNode getLast() {
        return last;
    }

    public void setNext(ListNode n) {
        next = n;
    }

    public void setLast(ListNode n) {
        last = n;
    }

    public String getItem() {
        return str;
    }
}