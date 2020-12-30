package file.allocation;
import java.util.*;

public class FileAllocation {
    static int IDS = 0;

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the block size");
        int size = input.nextInt();
        System.out.println("1- for contigous, 2- for indexed, 3- for linked");
        int algo = input.nextInt();
        boolean cont = false;
        String fileName;
        int start, length;
        char reply;
        if (algo == 1) {

            contiguos contAllocation = new contiguos(size);
            do {
                System.out.println("Enter the file name and length");
                fileName = input.next();
                length = input.nextInt();
                if (!contAllocation.enoughMemorySpace(length, size)) {
                    System.out.println("Not enough memory space");
                    contAllocation.output(size);
                    contAllocation.output2(size);
                    return;
                }
                start = getRandomNumber(0, (size - 1) - length);
                while (contAllocation.existInMemory(start) || !contAllocation.enoughContigiousMemory(start, length)) {
                    start = getRandomNumber(0, (size - 1) - length);

                }
                contAllocation.AllocateMemory(start, length, fileName);
                System.out.println("do you want to input another file(y/n)");
                reply = input.next().charAt(0);
                if (reply == 'y') {
                    cont = true;
                } else {
                    cont = false;
                }
            } while(cont == true);
            contAllocation.output(size);
            contAllocation.output2(size);
        } else if (algo == 2) {
            indexed indexMethod = new indexed(size);
            do {
                System.out.println("Enter the file name and length");
                fileName = input.next();
                length = input.nextInt();
                indexMethod.AllocateMemory(length, fileName);
                System.out.println("do you want to input another file(y/n)");
                reply = input.next().charAt(0);
                if (reply == 'y') {
                    cont = true;
                } else {
                    cont = false;
                }                
            } while(cont == true);
            indexMethod.output();
        } else if (algo == 3) {
             linked linkedMethod = new linked(size);
            do {
                System.out.println("Enter the file name and length");
                fileName = input.next();
                length = input.nextInt();
                linkedMethod.AllocateMemory(length, fileName);
                System.out.println("do you want to input another file(y/n)");
                reply = input.next().charAt(0);
                if (reply == 'y') {
                    cont = true;
                } else {
                    cont = false;
                }                
            } while(cont == true);
            linkedMethod.output();
           
        }
    }
}
