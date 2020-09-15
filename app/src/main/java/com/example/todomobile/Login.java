package com.example.todomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todomobile.models.Customer;
import com.example.todomobile.models.Employee;
import com.example.todomobile.models.LoginDetails;
import com.example.todomobile.models.WorkOrder;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    private EditText userNameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private List<Employee> employees;
    private List<LoginDetails> loginDetails;
    private List<Customer> customers;
    private List<WorkOrder> workOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        employees = new ArrayList<>();
        loginDetails = new ArrayList<>();
        customers = new ArrayList<>();
        workOrders = new ArrayList<>();

        getData();

        userNameEditText = findViewById(R.id.txtUserName);
        passwordEditText = findViewById(R.id.txtPassword);
        loginButton = findViewById(R.id.btnLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if ((username.trim().length() < 1) || (password.trim().length() < 1)) {
                    Toast.makeText(Login.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    int employeeId = validateLogin(username, password);
                    if (employeeId < 1) {
                        Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                        userNameEditText.setText("");
                        passwordEditText.setText("");
                        userNameEditText.requestFocus();
                    } else {
                        ArrayList<WorkOrder> workOrdersForEmployee = getWorkOrders(employeeId);
                        Intent intent = new Intent(Login.this, OrderList.class);
                        intent.putParcelableArrayListExtra("WORKORDERS_LIST_MESSAGE", workOrdersForEmployee);

                        startActivity(intent);
                    }
                }

            }
        });
    }

    private int validateLogin(String username, String password) {

        for (LoginDetails loginDetail : loginDetails) {
            if((username.equals(loginDetail.getUsername())) && (password.equals(loginDetail.getPassword()))) {
                return loginDetail.getEmployeeId();
            }
        }
        return -1;
    }

    private void getData() {
        // Should get data from database and instantiate objects from the data

        Employee employee1 = new Employee(1, "Henrik", "Henriksson", "070-111111");
        Employee employee2 = new Employee(2, "Anna", "Andersson", "073-777777");
        Employee employee3 = new Employee(3, "Gustav", "Gustavsson", "070-123456");

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        LoginDetails loginDetails1 = new LoginDetails("Henrik", "lösenord", 1);
        LoginDetails loginDetails2 = new LoginDetails("Anna", "hejhej", 2);
        LoginDetails loginDetails3 = new LoginDetails("Gustav", "password", 3);

        loginDetails.add(loginDetails1);
        loginDetails.add(loginDetails2);
        loginDetails.add(loginDetails3);

        Customer customer1 = new Customer(1, "Volvo", "Bilgatan 2", "Håkan Samuelsson", "031-555555");
        Customer customer2 = new Customer(2, "AstraZeneca", "Medicingatan 4", "Astrid Zeneca", "070-234567");

        customers.add(customer1);
        customers.add(customer2);

        WorkOrder workOrder1 = new WorkOrder(1, "2020-09-20 09:00", "Bilgatan 5", "Byte av fläkt i lagerlokal", "Arne Svensson 070-350000", customer1, employee1,1);
        WorkOrder workOrder2 = new WorkOrder(2, "2020-09-21 13:00", "Bilgatan 2", "Service av skrivare på kontor", "Carina Johansson carina@volvo.com", customer1, employee2,1);
        WorkOrder workOrder3 = new WorkOrder(3, "2020-09-25 10:30", "Medicingatan 4", "Reparation av trasig fläkt", "Marina Martinsson 073-456654", customer2, employee3, 1);
        WorkOrder workOrder4 = new WorkOrder(4, "2020-09-21 08:30", "Bilgatan 8", "Service av fläkt i fabrikslokal", "Erik Engdahl 070-474747", customer1, employee3, 1);

        workOrders.add(workOrder1);
        workOrders.add(workOrder2);
        workOrders.add(workOrder3);
        workOrders.add(workOrder4);
    }

    private ArrayList<WorkOrder> getWorkOrders(int employeeId) {
        ArrayList<WorkOrder> workOrdersForEmployee = new ArrayList<>();
        for (WorkOrder workOrder : workOrders) {
            if (workOrder.getEmployee().getId() == employeeId) {
                workOrdersForEmployee.add(workOrder);
            }
        }
        return workOrdersForEmployee;
    }
}