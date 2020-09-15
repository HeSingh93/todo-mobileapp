package com.example.todomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todomobile.models.Customer;
import com.example.todomobile.models.Employee;
import com.example.todomobile.models.LoginDetails;
import com.example.todomobile.models.WorkOrder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        setContentView(R.layout.activity_main);


    }

    private void getData() {
        // Should get data from database and instantiate objects from the data

        Employee employee1 = new Employee(1, "Henrik", "Henriksson", "070-111111");
        Employee employee2 = new Employee(2, "Anna", "Andersson", "073-777777");
        Employee employee3 = new Employee(3, "Gustav", "Gustavsson", "070-123456");

        LoginDetails loginDetails1 = new LoginDetails("Henrik", "lösenord", 1);
        LoginDetails loginDetails2 = new LoginDetails("Anna", "hejhej", 2);
        LoginDetails loginDetails3 = new LoginDetails("Gustav", "password", 3);

        Customer customer1 = new Customer(1, "Volvo", "Bilgatan 2", "Håkan Samuelsson", "031-555555");
        Customer customer2 = new Customer(2, "AstraZeneca", "Medicingatan 4", "Astrid Zeneca", "070-234567");

        WorkOrder workOrder1 = new WorkOrder(1, "2020-09-20 09:00", "Bilgatan 5", "Byte av fläkt i lagerlokal", "Arne Svensson 070-350000", customer1, 1);
        WorkOrder workOrder2 = new WorkOrder(2, "2020-09-21 13:00", "Bilgatan 2", "Service av skrivare på kontor", "Carina Johansson carina@volvo.com", customer1, 1);
        WorkOrder workOrder3 = new WorkOrder(3, "2020-09-25 10:30", "Medicingatan 4", "Reparation av trasig fläkt", "Marina Martinsson 073-456654", customer2, 1);
        WorkOrder workOrder4 = new WorkOrder(4, "2020-09-21 08:30", "Bilgatan 8", "Service av fläkt i fabrikslokal", "Erik Engdahl 070-474747", customer1, 1);

    }
}