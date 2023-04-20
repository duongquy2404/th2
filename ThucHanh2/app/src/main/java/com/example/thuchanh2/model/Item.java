package com.example.thuchanh2.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id,hinhthuc,trangthai;
    private  String ten,noidung,ngaydenhan;

    public Item() {
    }

    public Item(String ten, String noidung, int trangthai, String ngaydenhan, int hinhthuc) {
        this.hinhthuc = hinhthuc;
        this.ten = ten;
        this.noidung = noidung;
        this.ngaydenhan = ngaydenhan;
        this.trangthai = trangthai;
    }

    public Item(int id, String ten, String noidung, int trangthai, String ngaydenhan, int hinhthuc) {
        this.id = id;
        this.hinhthuc = hinhthuc;
        this.ten = ten;
        this.noidung = noidung;
        this.ngaydenhan = ngaydenhan;
        this.trangthai = trangthai;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHinhthuc() {
        return hinhthuc;
    }

    public void setHinhthuc(int hinhthuc) {
        this.hinhthuc = hinhthuc;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgaydenhan() {
        return ngaydenhan;
    }

    public void setNgaydenhan(String ngaydenhan) {
        this.ngaydenhan = ngaydenhan;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
