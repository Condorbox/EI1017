import java.io.File;  // Import the File class
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class to read text files

public class CSV implements CSVInterface{
    private static String csvFile = "C:\\Users\\manue\\IdeaProjects\\EI1017\\Practica1\\numbers.csv";
    private static String delimiter = ",";
    @Override
    public Table readTable(String csvFile) {
        System.out.println("Reading....");
        try{
            Scanner myReader = new Scanner(new File(csvFile));
            Table table = new Table();
            boolean headersRead = false;
            while (myReader.hasNextLine()){
                String[] line = myReader.nextLine().split(delimiter);
                if(!headersRead){ //Header
                    for (String header : line){
                        table.addHeader(header);
                    }
                    headersRead = true;
                }else{
                    Row newRow = new Row();
                    for (String data : line){
                        try {
                            Double dataToAdd = Double.parseDouble(data);
                            newRow.addData(dataToAdd);
                        }catch (Exception exception){
                            System.out.println("Wrong csv format, data can be only numbers");
                            exception.printStackTrace();
                            return null;
                        }
                    }
                    table.addRow(newRow);
                }
            }

            table.printTable(4,0);
            return table;
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void readTableWithLabels(String tableWithLabels) {

    }

    /*private void readCSV(){
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
    }*/ //Last CSVReader
}
