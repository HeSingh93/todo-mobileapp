package com.example.todomobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todomobile.api.entities.EmployeeEntity;
import com.example.todomobile.api.entities.LoginForm;
import com.example.todomobile.api.entities.WorkOrderEntity;
import com.example.todomobile.api.json.APIRequester;
import com.example.todomobile.api.retrofitservices.APIService;
import com.example.todomobile.api.retrofitservices.RetrofitHelper;
import com.example.todomobile.models.Customer;
import com.example.todomobile.models.Employee;
import com.example.todomobile.models.Expense;
import com.example.todomobile.models.LoginDetails;
import com.example.todomobile.models.WorkOrder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends ToDoActivity {

    private static final String TAG = "Login";

    APIService apiService;
    RetrofitHelper retrofitHelper;

    private Bundle apiData;

    private EditText userNameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private List<Employee> employees;
    private List<LoginDetails> loginDetails;
    private List<Customer> customers;
    private List<WorkOrder> workOrders;

    public static final String LOGIN_URL = BASE_URL + "/login";
    public static final String LOGIN = "LOGIN";

    private String requestLogin = "{" +
            "    \"username\": \"%s\"," +
            "    \"password\": \"%s\"" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        apiService = RetrofitHelper.getAPIService();
        apiData = new Bundle();
        employees = new ArrayList<>();
        loginDetails = new ArrayList<>();
        customers = new ArrayList<>();
        workOrders = new ArrayList<>();

        //   getData();
        // loadApi();
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
                    validateLogin(username, password);
                    /*
                    if (employeeId < 1) {
                        Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                        userNameEditText.setText("");
                        passwordEditText.setText("");
                        userNameEditText.requestFocus();
                    } else {
                        ArrayList<WorkOrder> workOrdersForEmployee = getWorkOrders(employeeId);
                        Intent intent = new Intent(Login.this, OrderList.class);
                        intent.putParcelableArrayListExtra(WORKORDER_LIST_MESSAGE, workOrdersForEmployee);

                        startActivity(intent);
                    }

                     */
                }
            }
        });

/*
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
                        intent.putParcelableArrayListExtra(WORKORDER_LIST_MESSAGE, workOrdersForEmployee);

                        startActivity(intent);
                    }
                }

            }
        });

 */
    }

    private void validateLogin(String username, String password) {

        String loginRequest = String.format(requestLogin, username, password);
        APIRequester loginRequester = new APIRequester(Login.this, LOGIN);
        loginRequester.execute(LOGIN_URL, loginRequest);

        /*final LoginForm loginForm = new LoginForm(username, password);
        Call<LoginForm> call = apiService.login(loginForm);
        call.enqueue(new Callback<LoginForm>() {
            @Override
            public void onResponse(Call<LoginForm> call, Response<LoginForm> response) {
                LoginForm loginForm1 = response.body();

                if(loginForm1 != null) {
                  String userName = loginForm1.getUserName();
                  Log.i("Username: " , "" +userName);
                  EmployeeEntity employee = loginForm.getEmployeeEntity();
                  String firstName = employee.getFirstName();
                  String lastName = employee.getLastName();
                    System.out.println(firstName + lastName);
                    Log.i("LOGIN", "SUCCESSFUL LOGIN");
                }
            }

            @Override
            public void onFailure(Call<LoginForm> call, Throwable t) {
                Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });*/

                /*for (LoginDetails loginDetail : loginDetails) {
            if ((username.equals(loginDetail.getUsername())) && (password.equals(loginDetail.getPassword()))) {
                return loginDetail.getEmployeeId();
            }
        }
        return -1;
         */
    }

    private void getData() {
        // Should get data from database and instantiate objects from the data

        Employee employee1 = new Employee(1, "Henrik", "Henriksson", "070-111111");
        Employee employee2 = new Employee(2, "Anna", "Andersson", "073-777777");
        Employee employee3 = new Employee(3, "Gustav", "Gustavsson", "070-123456");
        Employee employee4 = new Employee(4, "Sofia", "Larsson", "070-3456750");
        Employee employee5 = new Employee(5, "Andreas", "Lindsten", "070-8856450");
        Employee employee6 = new Employee(6, "Mikael", "Ludvigsson", "070-7398633");
        Employee employee7 = new Employee(7, "David", "Andersson", "070-9045553");
        Employee employee8 = new Employee(8, "Bo", "Adaktusson", "070-1232178");
        Employee employee9 = new Employee(9, "Isabell", "Löv", "076-9976345");
        Employee employee10 = new Employee(10, "Emelie", "Johanson", "070-7503487");

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
        employees.add(employee6);
        employees.add(employee7);
        employees.add(employee8);
        employees.add(employee9);
        employees.add(employee10);

        LoginDetails loginDetails1 = new LoginDetails("Henrik", "lösenord", 1);
        LoginDetails loginDetails2 = new LoginDetails("Anna", "hejhej", 2);
        LoginDetails loginDetails3 = new LoginDetails("Gustav", "password", 3);
        LoginDetails loginDetails4 = new LoginDetails("Sofia", "äpple", 4);
        LoginDetails loginDetails5 = new LoginDetails("Andreas", "Cykelsadel", 5);
        LoginDetails loginDetails6 = new LoginDetails("Mikael", "skogen", 6);
        LoginDetails loginDetails7 = new LoginDetails("David", "barnen", 7);
        LoginDetails loginDetails8 = new LoginDetails("Bo", "jobbet", 8);
        LoginDetails loginDetails9 = new LoginDetails("Isabell", "skolan", 9);
        LoginDetails loginDetails10 = new LoginDetails("Emelie", "granskog", 10);

        loginDetails.add(loginDetails1);
        loginDetails.add(loginDetails2);
        loginDetails.add(loginDetails3);
        loginDetails.add(loginDetails4);
        loginDetails.add(loginDetails5);
        loginDetails.add(loginDetails6);
        loginDetails.add(loginDetails7);
        loginDetails.add(loginDetails8);
        loginDetails.add(loginDetails9);
        loginDetails.add(loginDetails10);

        Customer customer1 = new Customer(1, "Volvo", "Bilgatan 2, Göteborg", "Håkan Samuelsson", "031-555555");
        Customer customer2 = new Customer(2, "AstraZeneca", "Medicingatan 4, Göteborg", "Astrid Zeneca", "070-234567");
        Customer customer3 = new Customer(3, "Newton", "Magasingatan 5, Göteborg", "Håkan Sigvardsson", "070-2234534");
        Customer customer4 = new Customer(4, "Åhlens", "Hamngatan 43, Stockholm", "Linnea Hurtig", "070-3456755");
        Customer customer5 = new Customer(5, "Skanska", "Fabriksvägen 1, Borlänge", "Anders Bernardsson", "070-9982834");
        Customer customer6 = new Customer(6, "Håkans bilar", "Skogsstigen 11, Askim", "Håkan Andersson", "070-2827363");
        Customer customer7 = new Customer(7, "Sony Eriksson", "Gustav Adolfs Torg 4, Malmö", "Sofia Bertilsson", "070-8694553");
        Customer customer8 = new Customer(8, "HSB huvudkontor", "Trägatan 48, Göteborg", "Rolf Radius", "070-4566541");
        Customer customer9 = new Customer(9, "Årstaskolan", "Skolgatan 1, Umeå", "Sigrid Pettersson", "070-0987444");
        Customer customer10 = new Customer(10, "Saab Space Center", "Allegatan 76, Karlstad", "Sune Damgard", "070-45456689");

        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);
        customers.add(customer5);
        customers.add(customer6);
        customers.add(customer7);
        customers.add(customer8);
        customers.add(customer9);
        customers.add(customer10);

        WorkOrder workOrder1 = new WorkOrder(1, "2020-09-20 09:00", "Bilgatan 5, Göteborg", "Byte av fläkt i lagerlokal", "Arne Svensson 070-350000", customer1, 1, STATUS_ASSIGNED);
        WorkOrder workOrder2 = new WorkOrder(2, "2020-09-21 13:00", "Bilgatan 2, Skövde", "Service av skrivare på kontor", "Carina Johansson carina@volvo.com", customer1, 2, STATUS_ASSIGNED);
        WorkOrder workOrder3 = new WorkOrder(3, "2021-03-25 10:30", "Medicingatan 4, Hässleholm", "Reparation av trasig fläkt", "Marina Martinsson 073-456654", customer2, 3, STATUS_ASSIGNED);
        WorkOrder workOrder4 = new WorkOrder(4, "2021-01-07 08:30", "Hamnvägen 8, Skellefteå", "Service av fläkt i fabrikslokal", "Erik Engdahl 070-474747", customer1, 3, STATUS_ACCEPTED);
        WorkOrder workOrder5 = new WorkOrder(5, "2020-10-01 08:30", "Stora torget 8, Lysekil", "Service av bandmaskin", "Sven Andersson 076-4758847", customer1, 3, STATUS_ACCEPTED);
        WorkOrder workOrder6 = new WorkOrder(6, "2020-09-21 13:00", "Mogatan 44, Anderstorp", "Årlig service av 4 entredörrar", "Stig Axelsson 070-4598777", customer1, 3, STATUS_ACCEPTED);
        WorkOrder workOrder7 = new WorkOrder(7, "2020-11-08 07:30", "Storgatan 8, Vimmerby", "Installation av armaturer i serverrum", "Anna Bergendahl 070-4323456", customer1, 3, STATUS_ASSIGNED);
        WorkOrder workOrder8 = new WorkOrder(8, "2020-09-29 11:00", "Lilla vägen 2, Stockholm", "Nybyggnation av serverskåp", "Ann-Louice Eriksson 076-0477666", customer1, 3, STATUS_ASSIGNED);
        //WorkOrder workOrder9 = new WorkOrder(9, "2021-02-21 14:00", "Hjortronstigen 34, Sundsvall", "Service av transportbana för paket", "Sten Wolter 070-9889765", customer1, employee3, STATUS_ASSIGNED);
        //WorkOrder workOrder10 = new WorkOrder(10, "2020-12-13 07:00", "Bertil Larssons Gata 1, Kiruna", "Service av AC i serverrum", "Anders Linné 070-3434222", customer1, employee3, STATUS_ASSIGNED);

        workOrders.add(workOrder1);
        workOrders.add(workOrder2);
        workOrders.add(workOrder3);
        workOrders.add(workOrder4);
        workOrders.add(workOrder5);
        workOrders.add(workOrder6);
        workOrders.add(workOrder7);
        workOrders.add(workOrder8);
        //workOrders.add(workOrder9);
        //workOrders.add(workOrder10);
    }

    private ArrayList<WorkOrder> getWorkOrders(int employeeId) {
        ArrayList<WorkOrder> workOrdersForEmployee = new ArrayList<>();
        for (WorkOrder workOrder : workOrders) {
            if (workOrder.getEmployeeId() == employeeId) {
                workOrdersForEmployee.add(workOrder);
            }
        }
        return workOrdersForEmployee;
    }

    private void loadApi() {
        getCurrentWorkOrders();
    }

    private void getCurrentWorkOrders() {
        final ArrayList<WorkOrderEntity> currentWorkOrders = new ArrayList<>();
        apiData.putParcelableArrayList("Workorders", currentWorkOrders);

        RetrofitHelper.getAPIService().getCurrentWorkOrders().enqueue(new Callback<List<WorkOrderEntity>>() {
            @Override
            public void onResponse(Call<List<WorkOrderEntity>> call, Response<List<WorkOrderEntity>> response) {
                if (response.body() != null) {
                    currentWorkOrders.addAll(response.body());
                    Log.i("Load", "WORKORDERS COLLECTED SUCCESSFULLY");
                }
            }

            @Override
            public void onFailure(Call<List<WorkOrderEntity>> call, Throwable t) {
                Log.i("FailedLoad", "WORK ORDERS FAILED TO COLLECT");
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onDownloadComplete(String results, String message) throws JSONException {

        if(message.equals(LOGIN)) {
            try {
                JSONObject jsonEmployee = new JSONObject(results);
                JSONArray workOrders = jsonEmployee.getJSONArray("workOrders");

                ArrayList<WorkOrder> workOrdersForEmployee = new ArrayList<>();

                for (int i = 0; i < workOrders.length(); i++) {
                    JSONObject workOrder = workOrders.getJSONObject(i);
                    int id = workOrder.getInt("id");
                    String dateTimeInfo = workOrder.getString("date") + " " + workOrder.getString("time");
                    String address = workOrder.getString("address");
                    String workDescription = workOrder.getString("workDescription");
                    String contactInfo = workOrder.getString("contactInfo");
                    Customer customer = null;
                    int employeeId = jsonEmployee.getInt("id");
                    int status = workOrder.getInt("status");

                    WorkOrder newWorkOrder = new WorkOrder(id, dateTimeInfo, address, workDescription,
                            contactInfo, customer, employeeId, status);
                    workOrdersForEmployee.add(newWorkOrder);
                }

                Intent intent = new Intent(Login.this, OrderList.class);
                intent.putParcelableArrayListExtra(WORKORDER_LIST_MESSAGE, workOrdersForEmployee);

                startActivity(intent);

            } catch (JSONException e) {
                Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "parseFoodInfo: JSONException: " + e.getMessage());
            }
        }


    }
}