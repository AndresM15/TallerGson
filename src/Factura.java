/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.Serializable;

/**
 *
 * @author thoma
 */
public class Factura implements Serializable{
     private String codigoProducto;
    private String nombreProducto;
    private int cantidad;
    private double precio;
    private double impuesto;
    private double total;

    public Factura(String codigoProducto, String nombreProducto, int cantidad, double precio) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.impuesto = calcularImpuesto(precio, cantidad);
        this.total = calcularTotal(precio, cantidad, impuesto);
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        actualizarTotales();
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
        actualizarTotales();
    }

    public double getImpuesto() {
        return impuesto;
    }

    public double getTotal() {
        return total;
    }

    private double calcularImpuesto(double precio, int cantidad) {
        return precio * cantidad * 0.15; // 15% de impuesto
    }

    private double calcularTotal(double precio, int cantidad, double impuesto) {
        return (precio * cantidad) + impuesto;
    }

    private void actualizarTotales() {
        this.impuesto = calcularImpuesto(this.precio, this.cantidad);
        this.total = calcularTotal(this.precio, this.cantidad, this.impuesto);
    }
    
}
