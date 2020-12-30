package file.allocation;

public class linked {
    block[] blockMem;
    int max;
    
    linked(int length) {
        blockMem = new block[length];
        max = length;
        for (int i = 0; i < length; i++) {
            blockMem[i] = new block();
            blockMem[i].root = false;
        }
    }
    int getRandomLinkedBlock(int length) {
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
                if (current >= length) {
                    return true;
                }
            }   
        }
       return false;
    }
    
    void output() {
        String text = "";
        block current;
        System.out.println("File Name\tblocks");
        for (int i = 0; i < max; i++) {
            if (blockMem[i].root) {
                current = blockMem[i];
                text += blockMem[i].fileName+"\t\t"+i+" > ";
                while (current.next != Integer.MIN_VALUE) {
                    text += current.next;
                    current = blockMem[current.next];
                    if (current.next != Integer.MIN_VALUE) {
                        text += " > ";
                    }
                }
                text += "\n";
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
        int j = 0;
        int temp;
        int rand[] = new int[2];
        rand[j] = getRandomLinkedBlock(max);
        String text = "";
        for (int i = 0; i < length; i++) {
            
            if (i == 0) {
                blockMem[rand[j]].root = true;
            }
            blockMem[rand[j]].fileID = FileAllocation.IDS;
            blockMem[rand[j]].fileName = fileName;
            blockMem[rand[j]].occupied = true;
            text += rand[j]+", ";
            if (i != (length-1)) {
                temp = rand[j];
                if (j == 0) {
                    rand[1] = getRandomLinkedBlock(max);
                    j = 1;
                } else {
                    rand[0] = getRandomLinkedBlock(max);
                    j = 0;
                }
                blockMem[temp].next = rand[j];
            } 
        }
        System.out.println("Occupied "+text+" and file name is "+fileName);     
    }

}
