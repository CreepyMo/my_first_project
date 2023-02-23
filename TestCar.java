public class TestCar {
    public static void main(String[] args) {

        Car car1 = new Car("Audi");
        Car car2 = new Car("BMW", "white");
        Car car3 = new Car("Mercedes", "red", 2.0);
        Car car4 = new Car("KIA", "blue", 1.6, 175);
        Car car5 = new Car("Subaru");
        Car car6 = new Car("Lexus", "purple");
        Car car7 = new Car("Mazda", "yellow", 3.5);
        Car car8 = new Car("Honda", "pink", 3.0, 260);
        Car[] cars = {car1, car2, car3, car4, car5, car6, car7, car8};

        for (int i=0; i<cars.length; i++) {
            cars[i].changeColor("white");
        }

        Car.printCars(car1, car2, car3, car4, car5, car6, car7, car8);

        System.out.println("Total cars created: " + Car.counter);
    }
}
