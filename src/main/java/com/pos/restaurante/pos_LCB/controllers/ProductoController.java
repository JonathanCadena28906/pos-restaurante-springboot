package com.pos.restaurante.pos_LCB.controllers;

import jakarta.servlet.annotation.HandlesTypes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductoController {

    @GetMapping("/productos")
    public String listarProductos() {
        // Lógica para obtener la lista de productos desde la base de datos
        return "lista_productos"; // Retorna el nombre de la vista (HTML) que mostrará los productos
    }

    public String agregarProducto(Model model) {
        // Lógica para mostrar el formulario de agregar producto
        return "agregar_producto"; // Retorna el nombre de la vista (HTML) para agregar un producto
    }
}
