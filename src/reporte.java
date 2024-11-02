import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class reporte {
    public static void main(String[] args) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("car_sales.json")) {
            Type listType = new TypeToken<List<Car>>() {}.getType();
            List<Car> cars = gson.fromJson(reader, listType);

            Map<String, Double> averagePrices = cars.stream()
                    .collect(Collectors.groupingBy(Car::getCar,
                            Collectors.averagingDouble(car -> Double.parseDouble(car.getPrice().replace("$", "").replace(",", "")))));

            averagePrices.forEach((brand, avgPrice) ->
                    System.out.printf("Marca: %s - Precio promedio: $%.2f%n", brand, avgPrice));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
