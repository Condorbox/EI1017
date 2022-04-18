package MVC.Model;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class WriteFile {
    private WriteFile(){}

    public static void saveFile(File file, Map<List<Double>, String> data){
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);

            for (Map.Entry<List<Double>, String> entry : data.entrySet()){
                String line = entry.getKey().stream().map(String::valueOf).collect(Collectors.joining(",", "", "," + entry.getValue()));
                out.print("\n"+line);
            }
            out.close();

        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
