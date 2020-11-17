package file.allocation;
import java.util.*;

public class FileAllocation {
    static block[] blockMem;
    static ArrayList<Integer> ints = new ArrayList<>();
    static int IDS = 0;
    
    static void output(int size) {
        String text = "";
        Integer id = 0;
        int s = 0;
        ArrayList<String> textOutput = new ArrayList<>();
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
    

    static void init(int length) {
        blockMem = new block[length];
        for (int i = 0; i < length; i++) {
            blockMem[i] = new block();
        }
    }
    static void AllocateMemory(int start, int length, String fileName) {
        //if (isEmpty(start, length)) {
            IDS = IDS + 1 ;
            for (int i = start; i < start + length; i++) {
                blockMem[i].fileID = IDS;
                blockMem[i].fileName = fileName;
                blockMem[i].occupied = true;
                ints.add(i);
                
            }
            System.out.println("Occupied starting from "+(start+1)+" to "+((start+length))+" and file name is "+fileName);
            
        //}
    }
    
    public static Boolean existInMemory(int start) {
        for (int i = 0; i < ints.size(); i++) {
            if (start == ints.get(i)) {
                return true;
            }
        }
        return false;
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
    
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the block size");
        int size = input.nextInt();
        boolean cont = false;
        String fileName;
        int start, length;
        char reply;
        init(size);
        do {
            System.out.println("Enter the file name and length");
            fileName = input.next();
            length = input.nextInt();
            if (!enoughMemorySpace(length, size)) {
                System.out.println("Not enough memory space");
                output(size);
                return;
            }
            start = getRandomNumber(0, (size - 1) - length);
            while (existInMemory(start)) {
                start = getRandomNumber(0, (size - 1) - length);
                
            }
            AllocateMemory(start, length, fileName);
            System.out.println("do you want to input another file(y/n)");
            reply = input.next().charAt(0);
            if (reply == 'y') {
                cont = true;
            } else {
                cont = false;
            }
        } while(cont == true);
        output(size);
    }
}
