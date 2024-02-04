package model;
import com.opencsv.*;
import java.io.*;
import java.util.*;


public class InfrastructureDepartment {
    public String BILLBOARD_FILE_NAME="C:\\Users\\KAROLD\\Documents\\uni\\apo2\\BillboardManagement\\src\\main\\java\\model\\data\\BillboardDataExported.csv";
    private String BILLBOARD_BINARY_FILE_NAME = "C:\\Users\\KAROLD\\Documents\\uni\\apo2\\BillboardManagement\\src\\main\\java\\model\\data\\BillboardDataExported.bin";
    private String BILLBOARD_DANGER_REPORT = "C:\\Users\\KAROLD\\Documents\\uni\\apo2\\BillboardManagement\\src\\main\\java\\model\\data\\report.txt";

    ArrayList<Billboard> billboards;


    //load file info on an arraylist
    public InfrastructureDepartment(){
        billboards = new ArrayList<Billboard>();
        importData();
    }

    //add a new billboard to list
    public void addBillboard(String billboardDetails){
        String[] variables=billboardDetails.split("\\++");
        double width = Double.parseDouble(variables[0]);
        double height = Double.parseDouble(variables[1]);
        boolean inUse = Boolean.parseBoolean(variables[2]);
        String brand = variables[3];
        Billboard newBillboard = new Billboard(width, height, inUse, brand);
        billboards.add(newBillboard);
        saveBillboards();
    }

    //write new billboards on file
    private void saveBillboards(){
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(BILLBOARD_FILE_NAME))) {
            for (Billboard billboard : billboards) {
                String[] data = {String.valueOf(billboard.getWidth()), String.valueOf(billboard.getHeight()),
                        String.valueOf(billboard.getInUse()), billboard.getBrand()};
                csvWriter.writeNext(data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BILLBOARD_BINARY_FILE_NAME))) {
            oos.writeObject(billboards);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //load billboards as a list (when program is starting)
    public void loadBillboards() {
        System.out.println("W\t\tH\t\tinUse\tBrand");
        for (Billboard billboard : billboards) {
            System.out.printf("%.2f\t%.2f\t%s\t%s%n", billboard.getWidth(), billboard.getHeight(),
                    billboard.getInUse(), billboard.getBrand());
        }
        System.out.println("\nTOTAL: " + billboards.size() + " vallas");
    }

    //creates or overwrite on a report.txt file. also shows it to the user
    public void exportDangerousBillboardReport() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BILLBOARD_DANGER_REPORT))) {
            writer.println("===========================");
            writer.println("DANGEROUS BILLBOARD REPORT");
            writer.println("===========================");
            System.out.println("===========================\nDANGEROUS BILLBOARD REPORT\n===========================");

            for (int i = 0; i < billboards.size(); i++) {
                if (billboards.get(i).calculateArea() > 160000) {
                    String dangerMessage = i + ". Billboard " + billboards.get(i).getBrand() +
                            " with area " + billboards.get(i).calculateArea() + "cm2";
                    writer.println(dangerMessage);
                    System.out.println(dangerMessage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importData(){
        try (CSVReader csvReader = new CSVReader(new FileReader(BILLBOARD_FILE_NAME))) {
            String[] variables;
            while ((variables = csvReader.readNext()) != null) {
                    double width = Double.parseDouble(variables[0]);
                    double height = Double.parseDouble(variables[1]);
                    boolean inUse = Boolean.parseBoolean(variables[2]);
                    String brand = variables[3];
                    Billboard billboard = new Billboard(width, height, inUse, brand);
                    billboards.add(billboard);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

    }
}
