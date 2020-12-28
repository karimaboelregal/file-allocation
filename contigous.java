package file.allocation;

import java.util.ArrayList;

public class contiguos {
    static block[] blockMem;
    static ArrayList<Integer> ints = new ArrayList<>();

    contiguos(int length) {
        blockMem = new block[length];
        for (int i = 0; i < length; i++) {
            blockMem[i] = new block();
        }
    }

    static void output(int size) {
        String text = "";
        Integer id = 0;
        int s = 0;
        System.out.println("File Name\tStart\tend\tLength");
        for (int i = 0; i < size; i++) {
            if (blockMem[i].occupied && (blockMem[i].fileID != id)) {
                text = blockMem[i].fileName + "\t\t"+(i+1);
                id = blockMem[i].fileID;
                s = 0;
                for (int j = i; j < size; j++) {
                    if (blockMem[i].fileID == blockMem[j].fileID) {
                        s++;
                    } else {
                        text = text + "\t"+(i+s)+"\t"+s;
                        System.out.println(text);
                        text = "";
                        break;
                    }
                }
            }
        }
    }
    
    static void output2(int size) {
        String text = "";
        Integer id = 0;
        String s = "";
        System.out.println("File Name\t\tBlocks");
        for (int i = 0; i < size; i++) {
            if (blockMem[i].occupied && (blockMem[i].fileID != id)) {
                text = blockMem[i].fileName + "\t\t\t";
                id = blockMem[i].fileID;
                s = "";
                for (int j = i; j < size; j++) {
                    if (blockMem[i].fileID == blockMem[j].fileID) {
                        s += (j+1)+" ,";
                    } else {
                        text = text + ""+s;
                        System.out.println(text);
                        text = "";
                        break;
                    }
                }
            }
        }
   }
    
    static void AllocateMemory(int start, int length, String fileName) {
        FileAllocation.IDS = FileAllocation.IDS + 1 ;
        for (int i = start; i < start + length; i++) {
            blockMem[i].fileID = FileAllocation. IDS;
            blockMem[i].fileName = fileName;
            blockMem[i].occupied = true;
            ints.add(i);
        }
        System.out.println("Occupied starting from "+(start+1)+" to "+((start+length))+" and file name is "+fileName);     
    }
    
    public static Boolean existInMemory(int start) {
        for (int i = 0; i < ints.size(); i++) {
            if (start == ints.get(i)) {
                return true;
            }
        }
        return false;
    }
    
    
    public static Boolean enoughContigiousMemory(int start, int length) {
        for(int i = start; i < start +length; i++) {
            if (blockMem[i].occupied) {
                return false;
            }
        }
        return true;
    }
    
    public static Boolean enoughMemorySpace(int length, int size) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (!blockMem[i].occupied) {
                count++;
                if (count == length) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

}
