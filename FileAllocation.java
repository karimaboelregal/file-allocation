package file.allocation;
import java.util.*;

public class FileAllocation {
    static block[] blockMem;

    static void init(int length) {
        blockMem = new block[length];
        for (int i = 0; i < length; i++) {
            blockMem[i] = new block();
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the block size");
        int size = input.nextInt();
        boolean cont = false;
        String fileName;
        int start, length;
        init(size);
        do {
            System.out.println("Enter the file name and length");
            fileName = input.next();
            length = input.nextInt();
            start = getRandomNumber(0, (size - 1) - length);
        } while(cont == true);
    }
}
