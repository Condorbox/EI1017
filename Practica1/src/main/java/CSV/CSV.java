package CSV;

import java.io.File;  // Import the File class
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class to read text files

public class CSV implements CSVInterface{
    private String delimiter;

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
            Table<Row> table = new Table<Row>();
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
                        if (i == table.getHeaders().size() - 1){ //Label
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
}
