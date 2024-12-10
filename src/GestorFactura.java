/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author thoma
 */
public class GestorFactura {
     private final String archivoJSON = "facturas.json";
    private List<Factura> facturas;

    public GestorFactura() {
        facturas = new ArrayList<>();
        cargarFacturas();
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void agregarFactura(Factura factura) {
        facturas.add(factura);
        guardarFacturas();
    }

    public void modificarFactura(int index, Factura nuevaFactura) {
        if (index >= 0 && index < facturas.size()) {
            facturas.set(index, nuevaFactura);
            guardarFacturas();
        }
    }

    public void eliminarFactura(int index) {
        if (index >= 0 && index < facturas.size()) {
            facturas.remove(index);
            guardarFacturas();
        }
    }

    private void cargarFacturas() {
        try (Reader reader = new FileReader(archivoJSON)) {
            facturas = new Gson().fromJson(reader, new TypeToken<List<Factura>>() {}.getType());
            if (facturas == null) {
                facturas = new ArrayList<>();
            }
        } catch (IOException e) {
            facturas = new ArrayList<>();
        }
    }

    private void guardarFacturas() {
        try (Writer writer = new FileWriter(archivoJSON)) {
            new Gson().toJson(facturas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
