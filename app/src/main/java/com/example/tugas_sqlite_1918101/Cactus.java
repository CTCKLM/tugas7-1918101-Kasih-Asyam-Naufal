package com.example.tugas_sqlite_1918101;

public class Cactus {
    private String _id, _nama, _jenis;

    public Cactus (String id, String nama, String jenis) {
        this._id = id;
        this._nama = nama;
        this._jenis = jenis;
    }
    public Cactus() {
    }
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String get_nama() {
        return _nama;
    }
    public void set_nama(String _nama) {
        this._nama = _nama;
    }
    public String get_jenis() {
        return _jenis;
    }
    public void set_jenis(String _jenis) {
        this._jenis = _jenis;
    }
}
