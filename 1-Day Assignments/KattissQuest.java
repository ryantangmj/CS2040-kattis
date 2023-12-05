import java.util.*;

class KattissQuest {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        TreeMap<Integer, PriorityQueue<Long>> treeMap = new TreeMap<>();
        
        int num = kattio.getInt();
        for (int i = 0; i < num; i++) {
            String command = kattio.getWord();
            if (command.equals("add")) {
                int energy = kattio.getInt();
                long gold = kattio.getLong();

                if (treeMap.containsKey(energy)) {
                    PriorityQueue<Long> currentGold = treeMap.get(energy);
                    currentGold.add(gold);
                    treeMap.put(energy, currentGold);
                } else {
                    PriorityQueue<Long> currentGold = new PriorityQueue<>(Collections.reverseOrder());
                    currentGold.add(gold);
                    treeMap.put(energy, currentGold);
                }
            } else if (command.equals("query")) {
                int energyLevel = kattio.getInt();
                long goldEarned = 0;

                while (energyLevel > 0) {
                    Integer energy = treeMap.floorKey(energyLevel);

                    if (energy == null) {
                        break;
                    }

                    PriorityQueue<Long> golds = treeMap.get(energy);
                    goldEarned += golds.poll();

                    if (golds.size() == 0) {
                        treeMap.remove(energy);
                    } else {
                        treeMap.put(energy, golds);
                    }

                    energyLevel -= energy;
                }

                kattio.println(goldEarned);
            }
        }

        kattio.close();
    }
}