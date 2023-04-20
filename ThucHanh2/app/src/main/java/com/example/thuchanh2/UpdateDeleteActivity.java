package com.example.thuchanh2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class UpdateDeleteActivity extends AppCompatActivity
        implements View.OnClickListener {
    public EditText ten,noidung,ngaydenhan;
    public RadioButton ck_chuathuchien,ck_dangthuchien,ck_hoanthanh,ck_1minh,ck_lamchung;
    private Button btUpdate,btBack,btRemove;

    private Item item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();


        btBack.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        ngaydenhan.setOnClickListener(this);
        btRemove.setOnClickListener(this);

        Intent intent = getIntent();
        item =(Item) intent.getSerializableExtra("item");

        ten.setText(item.getTen());
        noidung.setText(item.getNoidung());
        ngaydenhan.setText(item.getNgaydenhan());

        int tt = item.getTrangthai();
        if(tt==0) ck_chuathuchien.setChecked(true);
        if(tt==1) ck_dangthuchien.setChecked(true);
        if(tt==2) ck_hoanthanh.setChecked(true);

        int ht = item.getHinhthuc();
        if(ht==0) ck_1minh.setChecked(true);
        if(ht==1) ck_lamchung.setChecked(true);
    }

    private void initView(){
        ten = findViewById(R.id.medTenCongViec);
        noidung = findViewById(R.id.medNoiDung);
        ngaydenhan = findViewById(R.id.medNgayDenHan);

        ck_chuathuchien = findViewById(R.id.mck1);
        ck_dangthuchien = findViewById(R.id.mck2);
        ck_hoanthanh = findViewById(R.id.mck3);

        ck_1minh = findViewById(R.id.mck4);
        ck_lamchung = findViewById(R.id.mck5);


        btUpdate = findViewById(R.id.mbtUpdate);
        btBack = findViewById(R.id.mbtBack);
        btRemove = findViewById(R.id.mbtRemove);



    }

    @Override
    public void onClick(View v) {
        SQLiteHelper db = new SQLiteHelper(this);
        if(v == ngaydenhan){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteActivity.this,
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

        if(v==btBack){
            finish();
        }
        if(v ==btUpdate){
            String t = ten.getText().toString();
            String nd = noidung.getText().toString();
            int tt = 0;
            if(ck_chuathuchien.isChecked()) tt=0;
            if(ck_dangthuchien.isChecked()) tt=1;
            if(ck_hoanthanh.isChecked()) tt=2;

            String ndh = ngaydenhan.getText().toString();

            int ht =0 ;
            if(ck_1minh.isChecked()) ht=0;
            if(ck_lamchung.isChecked()) ht=1;

            if(!t.isEmpty() && !nd.isEmpty() && ! ndh.isEmpty() ){
                Item i = new Item(item.getId(),t,nd,tt,ndh,ht);
                db.update(i);
                finish();
            }
        }
        if(v==btRemove){
            int id = item.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Thong bao xoa");
            builder.setMessage("Ban co chac muon xoa"+ item.getTen()+" khong?");
            builder.setIcon(R.drawable.remove);
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   SQLiteHelper db1 = new SQLiteHelper(getApplicationContext());
                    db1.delete(id);
                    finish();
                }
            });
            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}