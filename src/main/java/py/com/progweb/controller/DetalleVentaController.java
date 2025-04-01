package py.com.progweb.controller;

import py.com.progweb.model.DetalleVenta;
import py.com.progweb.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/detalle-venta")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping("/{idVenta}")
    public List<DetalleVenta> obtenerDetalles(@PathVariable Long idVenta) {
        return detalleVentaService.obtenerDetallesPorVenta(idVenta);
    }
}
