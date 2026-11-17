package com.example.mssesiones;

public class Sesion {

    private Long id;
    private String curso;
    private String fecha;   // dd/MM/yyyy, igual que en la app Android
    private double horas;
    private String tipo;    // Teoria, Practica o Laboratorio

    public Sesion() {
    }

    public Sesion(Long id, String curso, String fecha, double horas, String tipo) {
        this.id = id;
        this.curso = curso;
        this.fecha = fecha;
        this.horas = horas;
        this.tipo = tipo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public double getHoras() { return horas; }
    public void setHoras(double horas) { this.horas = horas; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}