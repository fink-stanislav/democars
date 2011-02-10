package com.exadel.democars.application;

import com.exadel.democars.beans.Car;

public class DataManager {
    public static Car[] getCarList() {
        Car[] cars = new Car[]{
                new Car("Ford", "Mustang", 2125f, 146829, "ZXCEWRFFEWEERFDSDF", Car.Condition.Used),
                new Car("Ford", "Mustang GT", 8999f, 48650, "DFSGHEER8454FF", Car.Condition.Used),
                new Car("Nissan", "Scorpio", 20100f, 17235, "YTDEWWDF5477RER", Car.Condition.New),
                new Car("Ford", "Mustang GT", 2125f, 83509, "BNDKLEL45477FEED", Car.Condition.New),
                new Car("Ford", "Mustang Pace Car", 15900f, 25473, "UIPSKFSEFEE5452", Car.Condition.Used),
                new Car("Volvo", "XC90T6", 10000f, 80821, "985FDCCVEWQQ1124", Car.Condition.Used),
                new Car("BMW", "7-Series 750 Li", 16400f, 80994, "NMNFDLJIEF5564", Car.Condition.Used),
                new Car("Ford", "Mustang GT 500", 48100f, 1, "JLJKDFF985FFD5", Car.Condition.New),
                new Car("Ford", "Mustang Convertible", 2995f, 105874, "DSED569SGPIWOEHF", Car.Condition.Used),
                new Car("Ford", "Cobra", 4550f, 169000, "EFDS4EFE56WWFED6", Car.Condition.Used),
                new Car("Dodge", "Charger", 4150f, 10973, "JLKAJIEIWO45ERE", Car.Condition.New),
                new Car("Lincoln", "Town Car", 11990f, 50000, "KLASDJKVSLKEE65", Car.Condition.Used),
                new Car("Dodge", "Magnum SE", 2999f, 95700, "WEIOUTIO117KLFWER", Car.Condition.Used),
                new Car("Ford", "F-250", 6200f, 103864, "WEURIWEIOR89785DD", Car.Condition.Used),
                new Car("Chevrolet", "Silverado", 16100f, 144280, "NNCNVKDfgh6897DFE", Car.Condition.Used),
                new Car("BMW", "7-Series", 12900f, 97509, "OODFIOEJDKF550005", Car.Condition.Used),
                new Car("Porshe", "914", 6225f, 77498, "NVCDKKEKDD12125477FD", Car.Condition.Used),
                new Car("Ford", "Taurus", 2125f, 74261, "EIWDFESSFDCXKLJ4", Car.Condition.Used),
                new Car("Acura", "MDX SH-AWD", 18884f, 50345, "POIKJ41DFJN62EKFD", Car.Condition.Used),
                new Car("Land Rover", "Range Rover Sport", 26100f, 79145, "IERD88S95UE2WFS", Car.Condition.Used),
                new Car("Jaguar", "XJ XJ12", 2795f, 108000, "NCVMNDSE2124EWE84F", Car.Condition.Used),
                new Car("Cadillac", "Seville", 2850f, 999999, "WERQWEFSD1DFEGT", Car.Condition.Used),
                new Car("Ford", "Crown Victoria", 2900f, 154275, "IOUWOPEEFD41255FEW", Car.Condition.Used),
                new Car("Dodge", "Neon", 2991f, 210223, "VCVBDSKWWEDF7477DWE", Car.Condition.Used),
                new Car("Land Rover", "Discovery", 4550f, 100900, "AFW4FEFES2FEW4", Car.Condition.Used),
                new Car("Saab", "9-3 SE", 6100f, 52600, "ASDFVVEVEEEW44EWE4", Car.Condition.Used),
                new Car("Saturn", "LS2", 2991f, 173131, "D5SF484FW8S8F4WE", Car.Condition.Used),
                new Car("Chevrolet", "Blazer", 3150f, 163438, "YE7TJ8GS4DF8GE8", Car.Condition.Used),
                new Car("Jaguar", "X-Type", 2995f, 112538, "HS4DF56G4EA6R5GA", Car.Condition.Used),
                new Car("BMW", "325", 4845f, 108948, "G4SDF65G4A6WEGA4AS5", Car.Condition.Used),
                new Car("Dodge", "Polara 440", 2995f, 114420, "T8H4DF8S46DRG4AWE", Car.Condition.Used),
                new Car("BMW", "7-Series iL", 2990f, 107551, "8EWRG46AS464S6DFG", Car.Condition.Used),
                new Car("Toyota", "Camry", 5000f, 12060, "DFG4SDF68G4E6AS86E", Car.Condition.Used)
        };

        return cars;
    }
}
