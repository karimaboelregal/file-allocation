package file.allocation;

import java.util.ArrayList;


public class indexed {
    block[] blockMem;
    int max;
    
    indexed(int length) {
        blockMem = new block[length];
        max = length;
        for (int i = 0; i < length; i++) {
            blockMem[i] = new block();
        }
    }
    
    int getIndexedRandomNumber(int length) {
        int rand = FileAllocation.getRandomNumber(0, length);
        while(blockMem[rand].occupied) {
            rand = FileAllocation.getRandomNumber(0, length);
        }
        return rand;
    }

    Boolean enoughBlocks(int length) {
        int current = 0;
        for (int i = 0; i < max; i++) {
            if (!blockMem[i].occupied) {
                current++;
                if (current >= (length+1)) {
                    return true;
                }
            }   
        }
       return false;
    }

    
    void output() {
        String text = "";
        System.out.println("File Name\tindex Block\tBlock");
        for (int i = 0; i < max; i++) {
            if (blockMem[i].root) {
                text += blockMem[i].fileName+"\t\t"+i+"\t\t";
                for (int j = 0; j < blockMem[i].indexes.size(); j++) {
                    text += blockMem[i].indexes.get(j) + ", ";
                }
                text+="\n";
            }
        }
        System.out.println(text);
    }
    
    void AllocateMemory(int length, String fileName) {
        if (!enoughBlocks(length)) {
            System.out.println("not enough space");
            return;
        }
        FileAllocation.IDS = FileAllocation.IDS + 1 ;
        int start = getIndexedRandomNumber(max);
        blockMem[start].fileID = FileAllocation.IDS;
        blockMem[start].fileName = fileName;
        blockMem[start].occupied = true;
        blockMem[start].root = true;
        int rand;
        String text = "";
        for (int i = 0; i < length; i++) {
            rand = getIndexedRandomNumber(max);
            blockMem[rand].fileID = FileAllocation.IDS;
            blockMem[rand].fileName = fileName;
            blockMem[rand].occupied = true;
            blockMem[rand].root = false;
            blockMem[start].indexes.add(rand);
            text += rand+", ";
        }
        System.out.println("Occupied "+text+" and file name is "+fileName);     
    }

}
