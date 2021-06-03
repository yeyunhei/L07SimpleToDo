package sg.edu.rp.c346.id20011806.l07simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText newActivity;
    Button btnAdd;
    Button btnClear;
    ListView tdList;
    ArrayList<String> activityList;
    Spinner spnAddRemove;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newActivity = findViewById(R.id.editTextInput);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        tdList = findViewById(R.id.todoList);
        spnAddRemove = findViewById(R.id.spinnerAddRemove);
        btnDelete = findViewById(R.id.buttonDel);

        activityList = new ArrayList<>();

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, activityList);
        tdList.setAdapter(adapter);

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spnAddRemove.getSelectedItemPosition() == 0) {
                    btnAdd.setTextColor(Color.BLACK);
                    btnDelete.setTextColor(Color.GRAY);
                    newActivity.setHint(R.string.enteractivity);
                    newActivity.setInputType(InputType.TYPE_CLASS_TEXT);
                    newActivity.setText("");
                } else {
                    btnAdd.setTextColor(Color.GRAY);
                    btnDelete.setTextColor(Color.BLACK);
                    newActivity.setHint(R.string.enterpos);
                    newActivity.setInputType(InputType.TYPE_CLASS_NUMBER);
                    newActivity.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spnAddRemove.getSelectedItemPosition() == 0) {
                    String newTask = newActivity.getText().toString();
                    if (newTask.isEmpty()) {
                        Toast emptyInput = Toast.makeText(getApplicationContext(), "No input given", Toast.LENGTH_SHORT);
                        emptyInput.show();
                    } else {
                    activityList.add(newTask);
                    }
                } else {
                    Toast invalid = Toast.makeText(getApplicationContext(), "Button disabled for this mode", Toast.LENGTH_SHORT);
                    invalid.show();
                }
                adapter.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spnAddRemove.getSelectedItemPosition() == 1) {
                    if (newActivity.getText().toString().isEmpty()) {
                        Toast emptyInput = Toast.makeText(getApplicationContext(), "No input given", Toast.LENGTH_SHORT);
                        emptyInput.show();
                    } else {
                        if (activityList.isEmpty()) {
                            Toast emptyList = Toast.makeText(getApplicationContext(), "You don't have any tasks to remove", Toast.LENGTH_SHORT);
                            emptyList.show();
                        }
                        int taskPos = Integer.parseInt(newActivity.getText().toString());
                        if (taskPos < activityList.size()) {
                            activityList.remove(taskPos);
                        } else if (!activityList.isEmpty() && taskPos >= activityList.size()) {
                            Toast invalidpos = Toast.makeText(getApplicationContext(), "Wrong index number", Toast.LENGTH_SHORT);
                            invalidpos.show();
                        }
                    }
                } else {
                    Toast invalid = Toast.makeText(getApplicationContext(), "Button disabled for this mode", Toast.LENGTH_SHORT);
                    invalid.show();
                }
                adapter.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityList.clear();
                adapter.notifyDataSetChanged();
            }
        });

    }
}