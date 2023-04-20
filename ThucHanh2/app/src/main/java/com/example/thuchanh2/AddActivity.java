package com.example.thuchanh2;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thuchanh2.dal.SQLiteHelper;
import com.example.thuchanh2.model.Item;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    public EditText ten,noidung,ngaydenhan;
    public RadioButton ck_chuathuchien,ck_dangthuchien,ck_hoanthanh,ck_1minh,ck_lamchung;

    public Button btCancel,btUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initView();
        btCancel.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        ngaydenhan.setOnClickListener(this);
    }
    private void initView(){
        ten = findViewById(R.id.edTenCongViec);
        noidung = findViewById(R.id.edNoiDung);
        ngaydenhan = findViewById(R.id.edNgayDenHan);

        ck_chuathuchien = findViewById(R.id.ck1);
        ck_dangthuchien = findViewById(R.id.ck2);
        ck_hoanthanh = findViewById(R.id.ck3);

        ck_1minh = findViewById(R.id.ck4);
        ck_lamchung = findViewById(R.id.ck5);

        btCancel = findViewById(R.id.btCancel);
        btUpdate = findViewById(R.id.btUpdate);

    }

    @Override
    public void onClick(View v) {
        if(v == ngaydenhan){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            String date = "";
                            String s = day+"";
                            if(day<10) s = "0" + s;
                            if(month>8){
                                date = year+"-" +(month+1)+"-"+s;
                            }
                            else{
                                date = year+"-0" +(month+1)+"-"+s;
                            }
                            ngaydenhan.setText(date);
                        }
                    },year,month,day);
            dialog.show();
        }
        if(v == btCancel){
            finish();
        }
        if(v ==btUpdate){
            String t = ten.getText().toString();
            String nd = noidung.getText().toString();

            int tt = -1;
            if(ck_chuathuchien.isChecked()) tt=0;
            if(ck_dangthuchien.isChecked()) tt=1;
            if(ck_hoanthanh.isChecked()) tt=2;

            String ndh = String.format(ngaydenhan.getText().toString() );

            int ht =-1 ;
            if(ck_1minh.isChecked()) ht=0;
            if(ck_lamchung.isChecked()) ht=1;

            if(!t.isEmpty() && !nd.isEmpty() && ! ndh.isEmpty() && tt!=-1 && ht!=-1 ){
                Item i = new Item(t,nd,tt,ndh,ht);
                SQLiteHelper db = new SQLiteHelper(this);
                db.addItem(i);
                finish();
            }
        }

    }
}