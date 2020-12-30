package file.allocation;
import java.util.ArrayList;

public class block {
    String fileName;
    int fileID;
    boolean occupied = false;
    boolean root;
    int next = Integer.MIN_VALUE;
    ArrayList<Integer> indexes = new ArrayList<>();
}
