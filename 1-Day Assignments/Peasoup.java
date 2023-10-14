import java.util.Scanner;

class Peasoup {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String havename = "";
        int restaurants = sc.nextInt();
        
        for (int i = 0; i < restaurants; i++) {
            int dishes = sc.nextInt();
            sc.nextLine();
            String name = sc.nextLine();

            if (havedishes(dishes, sc) && havename.length() == 0) {
                havename = name;
            }
        }
        
        if (havename.length() != 0) {
            System.out.println(havename);
        } else {
            System.out.println("Anywhere is fine I guess");
        }
    }
    
    public static boolean havedishes(int dishes, Scanner sc) {
        boolean peasoup = false;
        boolean pancakes = false;

        for (int i = 0; i < dishes; i++) {
            String dish = sc.nextLine();

            if (dish.equals("pea soup")) {
                peasoup = true;
            } else if (dish.equals("pancakes")) {
                pancakes = true;
            }
        }
        
        return peasoup && pancakes;
    }
    
}