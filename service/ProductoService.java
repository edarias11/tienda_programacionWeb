/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.service;

import com.tienda.domain.Producto;
import com.tienda.repository.ProductoRepository;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository; //CRUD 

    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activo) { //READ -> ingresa a la bd puede leer toda la inforacion de la producto
        var lista = productoRepository.findAll();
        //procto 1 = monito dell
        //procto 2 = monito samsung
        //procto 3 = monito hp
        //procto 4 = monito Lenovo
        //*todo se ve reflejado con findAll

        if (activo) {
            lista.removeIf(e -> !e.getActivo());
        }
        return lista;
    }

    @Transactional //este metodo funciona para guardar y actualizar
    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    @Transactional
    public boolean delete(Producto producto) { //Eliminar un producto 
        try {
            productoRepository.delete(producto);
            productoRepository.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoRepository.findById(producto.getIdProducto()).orElse(null);
    }

    public Object getMessage(String productoerror03, Object object, Locale aDefault) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //Lista de productos con precio inferio y superio para consulta JPA ampliada.
    @Transactional(readOnly = true)
    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup) {
        return productoRepository.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup);
    }
    
    
    
    //Lista de productos con precio inferio y superio para consulta jpql.   
    @Transactional(readOnly = true)    
    public List<Producto> metodoJPQL(double precioInf, double precioSup) {
        return productoRepository.metodoJPQL(precioInf, precioSup);
    }
    
    //Lista de productos con precio inferio y superio para consulta Nativa.   
    @Transactional(readOnly = true)    
    public List<Producto> metodoNaList(double precioInf, double precioSup) {
        return productoRepository.metodoNativoList(precioInf, precioSup);
    }
    
    
    
    
          
}
