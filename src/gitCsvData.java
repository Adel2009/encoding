
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static java.nio.charset.StandardCharsets.US_ASCII;


public class gitCsvData
{
    public static void main(String[] args)
    {
        readCsv();
    }

    private static void readCsv()
    {

        try (CSVReader reader = new CSVReader(new FileReader("StudensName.csv"), ','); Connection connection = startCommuncation.databaseConn();)
        {
            String insert = "Insert into score (id, studentname) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(insert);
            String[] rowData = null;
            int i = 0;
            while((rowData = reader.readNext()) != null){
                for (String data : rowData)
                {
                    MessageDigest messagedigest = MessageDigest.getInstance("SHA-256");


                    byte[] hash = messagedigest.digest(data.getBytes(US_ASCII));

                    //statement.setString((i % 2) + 1, String.valueOf(hash));
                    statement.setBytes((i % 2) + 1, hash);

                    if (++i % 2 == 0)
                        statement.addBatch();// add batch
                        statement.toString();
                    if (i % 20 == 0)// insert when the batch size is 10
                        statement.executeBatch();
                        statement.toString();
                }}
            System.out.println("The process is successful ");




        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }








}