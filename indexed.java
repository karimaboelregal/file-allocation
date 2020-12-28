package file.allocation;

import java.util.ArrayList;


public class indexed {
    static block[] blockMem;
    static int max;
    
    indexed(int length) {
        blockMem = new block[length];
        max = length;
        for (int i = 0; i < length; i++) {
            blockMem[i] = new block();
        }
    }
    
    static int getIndexedRandomNumber(int length) {
        int rand = FileAllocation.getRandomNumber(0, length);
        while(blockMem[rand].occupied) {
            rand = FileAllocation.getRandomNumber(0, length);
        }
        return rand;
    }
    
    static void output() {
        String text = "";
        Integer id = 0;
        String s = "";
        System.out.println("File Name\tindex Block\tBlock");
        for (int i = 0; i < max; i++) {
            if (blockMem[i].indexedBlock) {
                text += blockMem[i].fileName+"\t\t"+i+"\t\t";
                for (int j = 0; j < blockMem[i].indexes.size(); j++) {
                    text += blockMem[i].indexes.get(j) + ", ";
                }
                text+="\n";
            }
        }
        System.out.println(text);
    }
    
    static void AllocateMemory(int length, String fileName) {
        FileAllocation.IDS = FileAllocation.IDS + 1 ;
        int start = getIndexedRandomNumber(max);
        blockMem[start].fileID = FileAllocation.IDS;
        blockMem[start].fileName = fileName;
        blockMem[start].occupied = true;
        blockMem[start].indexedBlock = true;
        int rand;
        String text = "";
        for (int i = 0; i < length; i++) {
            rand = getIndexedRandomNumber(max);
            blockMem[rand].fileID = FileAllocation.IDS;
            blockMem[rand].fileName = fileName;
            blockMem[rand].occupied = true;
            blockMem[rand].indexedBlock = false;
            blockMem[start].indexes.add(rand);
            text += rand+", ";
        }
        System.out.println("Occupied "+text+" and file name is "+fileName);     
    }

}
