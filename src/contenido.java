import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class contenido {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Car Sales Data");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            String[] columnNames = {"ID", "First Name", "Last Name", "Car", "Price", "State"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            try (FileReader reader = new FileReader("car_sales.json")) {
                Type listType = new TypeToken<List<Car>>() {}.getType();
                List<Car> cars = new Gson().fromJson(reader, listType);

                for (Car car : cars) {
                    String[] row = {String.valueOf(car.getId()), car.getFirstName(), car.getLastName(),
                            car.getCar(), car.getPrice(), car.getState()};
                    tableModel.addRow(row);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            JTable table = new JTable(tableModel);
            frame.add(new JScrollPane(table), BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
