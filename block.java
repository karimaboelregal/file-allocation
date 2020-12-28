package file.allocation;
import java.util.ArrayList;

public class block {
    String fileName;
    int fileID;
    boolean occupied = false;
    boolean indexedBlock;
    ArrayList<Integer> indexes = new ArrayList<>();
}
