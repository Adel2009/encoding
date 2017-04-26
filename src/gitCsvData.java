import com.opencsv.CSVReader;

import java.io.FileReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static java.nio.charset.StandardCharsets.UTF_8;


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
            String[] csvRow = null;
            int i = 0;
            while((csvRow = reader.readNext()) != null){
                for (String data : csvRow)
                {

                    //Create MessageDigest object for MD5

                    MessageDigest messagedigest = MessageDigest.getInstance("MD5");
                    //update
                    messagedigest .update(data.getBytes(), 0, data.length());
                    byte[] Datahash = messagedigest.digest(data.getBytes(UTF_8));

                    //Converts message digest value in base 16 (hex)
                    insert = new BigInteger(1, messagedigest.digest()).toString(16);

                    //statement.setString((i % 2) + 1, String.valueOf(hash));
                    statement.setBytes((i % 2) + 1, Datahash);

                    if (++i % 2 == 0)
                        statement.addBatch();// add batch
                        //statement.toString();
                    if (i % 20 == 0)// insert when the batch size is 10
                        statement.executeBatch();
                       // statement.toString();
                }}
            System.out.println("The process is successful ");




        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }








}