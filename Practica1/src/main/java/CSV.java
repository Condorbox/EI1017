import java.io.File;  // Import the File class
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class to read text files

public class CSV implements CSVInterface{
    private static String csvFile = "addresses.csv";
    private static String delimiter = ",";
    @Override
    public void readTable(String table) {

    }

    @Override
    public void readTableWithLabels(String tableWithLabels) {

    }

    public static void main(String[] args) {
        try {
            Scanner myReader = new Scanner(new File(csvFile));
            Table table = new Table();
            int column = -1;
            while (myReader.hasNextLine()) {
                String[] line = myReader.nextLine().split(delimiter);
                if(column != -1){ //not header
                    table.addColum();
                }
                for(String newElemnt: line){ //newElemnt?
                    if(column == -1){ //Headers
                        table.addHeader(newElemnt);
                    }else{
                        Row rowToAdd = new Row(newElemnt);
                        table.addRow(column,rowToAdd);
                    }
                }
                column++;
            }
            myReader.close();

            table.printTable();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
