package com.emranhss.usesqlitedatabase59;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.emranhss.usesqlitedatabase59.databasehelper.DatabaseHelper;
import com.emranhss.usesqlitedatabase59.model.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    DatabaseHelper db;
    EditText editTextName, editTextEmail, editTextId;
    Button buttonAdd, buttonView, buttonUpdate, buttonDelete;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db=new DatabaseHelper(this, null, null, 1);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextId = findViewById(R.id.editTextId);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonView = findViewById(R.id.buttonView);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        textViewResult = findViewById(R.id.textViewResult);




        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                Student student = new Student(name, email);
                boolean inserted = db.insertStuident(student);
                if (inserted) {
                    Toast.makeText(getApplicationContext(), "Student Added", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Insertion Failed", Toast.LENGTH_SHORT).show();
                }
            }


        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = editTextId.getText().toString();
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();

                Student student = new Student(Integer.parseInt(id), name, email);

                boolean isUpdated = db.updateStudent(student);
                if (isUpdated)
                    Toast.makeText(MainActivity.this, "Student Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();



            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String id = editTextId.getText().toString();
                boolean isDeleted = db.deleteStudent(Integer.parseInt(id));
                if (isDeleted)
                    Toast.makeText(MainActivity.this, "Student Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Delete Failed", Toast.LENGTH_SHORT).show();

            }
        });

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> students = db.getAllStudnts();
                if (students.size() == 0) {
                    textViewResult.setText("No Student Found");
                    return;
                }

                StringBuilder buffer = new StringBuilder();
                for (Student student : students) {
                    buffer.append("ID: ").append(student.getId()).append("\n");
                    buffer.append("Name: ").append(student.getName()).append("\n");
                    buffer.append("Email: ").append(student.getEmail()).append("\n\n");
                }

                textViewResult.setText(buffer.toString());
            }

        });



    }
}