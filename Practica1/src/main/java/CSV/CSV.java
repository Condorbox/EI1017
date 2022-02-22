package CSV;

import java.io.File;  // Import the File class
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class to read text files

public class CSV implements CSVInterface{
    private String delimiter = ",";

    public CSV(){
        this.delimiter = ",";
    }

    public CSV(String delimiter){
        this.delimiter = delimiter;
    }

    public Table<Row> readTable(String csvFile) {
        System.out.println("Reading....");
        try{
            Scanner myReader = new Scanner(new File(csvFile));
            Table<Row> table = new Table();
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
            return table;
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    public TableWithLabel readTableWithLabels(String csvFileWithLabels) {
        System.out.println("Reading....");
        try{
            Scanner myReader = new Scanner(new File(csvFileWithLabels));
            TableWithLabel table = new TableWithLabel();
            boolean headersRead = false;
            while (myReader.hasNextLine()){
                String[] line = myReader.nextLine().split(delimiter);
                if(!headersRead){ //Header
                    for (String header : line){
                        table.addHeader(header);
                    }
                    headersRead = true;
                }else{
                    RowWithLabel newRow = new RowWithLabel();
                    for (int i = 0; i<line.length; i++){
                        if (i == table.getHeaders().size() - 1){
                            newRow.setLabel(line[i]);
                        }else{
                            try {
                                Double dataToAdd = Double.parseDouble(line[i]);
                                newRow.addData(dataToAdd);
                            }catch (Exception exception){
                                System.out.println("Wrong csv format, data can be only numbers except for the label");
                                exception.printStackTrace();
                                return null;
                            }
                        }
                    }
                    table.addRow(newRow);
                }
            }
            return table;
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }

    }

    /*private void readCSV(){
        try {
            Scanner myReader = new Scanner(new File(csvFile));
            CSV.Table table = new CSV.Table();
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
                        CSV.Row rowToAdd = new CSV.Row(newElemnt);
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
