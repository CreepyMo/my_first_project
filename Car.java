public class Car {
    String brand;
    String color;
    double engineVolume;
    int maxSpeed;

    public static int counter = 0;

    Car(String brand) {
        this.brand = brand;
        counter++;
    }

    Car(String brand, String color) {
        this(brand);
        this.color = color;
    }

    Car(String brand, String color, double engiveVolume) {
        this(brand, color);
        this.engineVolume = engiveVolume;
    }

    Car(String brand, String color, double engiveVolume, int maxSpeed) {
        this(brand, color, engiveVolume);
        this.maxSpeed = maxSpeed;
    }

    void changeColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "New Car is created: " +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", engineVolume=" + engineVolume +
                ", maxSpeed=" + maxSpeed;
    }

    static void printCars(Car... car) {
        for (int i = 0; i < car.length; i++) {
            System.out.println(car[i]);
        }

    }
}
