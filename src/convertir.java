import org.json.JSONObject;
import org.json.XML;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class convertir {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("car_sales.xml")) {
            StringBuilder xmlContent = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                xmlContent.append((char) i);
            }

            JSONObject jsonObject = XML.toJSONObject(xmlContent.toString());

            try (FileWriter file = new FileWriter("car_sales_converted.json")) {
                file.write(jsonObject.toString(4)); // 4 is for pretty-printing the JSON
                System.out.println("JSON output saved to car_sales_converted.json");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
