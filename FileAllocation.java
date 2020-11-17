package file.allocation;
import java.util.*;

public class FileAllocation {
    static block[] blockMem;
    static ArrayList<Integer> checkOccupiedIndexes = new ArrayList<>();
    
    static boolean isEmpty(int start, int length) {
        checkOccupiedIndexes.clear();
        boolean end = false;
        for (int i = start; i < start + length; i++) {
            if (blockMem[i].occupied) {
                checkOccupiedIndexes.add(i);
                end = true;
            }
        }
        if (end) {
            return false;
        }
        return true;
    }
    
    static void output(int size) {
        String text = "", name = "";
        int s = 0;
        ArrayList<String> textOutput = new ArrayList<>();
        System.out.println("File Name\tStart\tend\tLength");
        for (int i = 0; i < size; i++) {
            if (blockMem[i].occupied && (!name.equals(blockMem[i].fileName))) {
                text = blockMem[i].fileName + "\t\t"+i;
                name = blockMem[i].fileName;
                s = 0;
                for (int j = i + 1; j < size; j++) {
                    if (blockMem[i].fileName == blockMem[j].fileName) {
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
    static Boolean AllocateMemory(int start, int length, String fileName) {
        if (isEmpty(start, length)) {
            for (int i = start; i < start + length; i++) {
                blockMem[i].blockNo = i;
                blockMem[i].fileName = fileName;
                blockMem[i].occupied = true;
                
            }
            System.out.println("Occupied starting from "+start+" to "+((start+length) - 1)+" and file name is "+fileName);
            return true;
        }
        String output = "";
        for (int i = 0; i < checkOccupiedIndexes.size(); i++) {
            output = output+" "+checkOccupiedIndexes.get(i);
        }
        System.out.println(output+" these blocks are occupied");
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
            start = getRandomNumber(0, (size - 1) - length);
            if (AllocateMemory(start, length, fileName)) {
                System.out.println("do you want to input another file(y/n)");
                reply = input.next().charAt(0);
                if (reply == 'y') {
                    cont = true;
                } else {
                    cont = false;
                }
            } else {
                cont = false;
            }
        } while(cont == true);
        output(size);
    }
}
