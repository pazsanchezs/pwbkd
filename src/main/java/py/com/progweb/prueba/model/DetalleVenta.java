package py.com.progweb.controller;

import javax.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

//Modelo 
@Entity
class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleVenta;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "idVenta")
    private Venta venta;

    private int cantidad;
    private double precio;
    private double precioTotal;

    @PrePersist
    @PreUpdate
    public void calcularTotal() {
        this.precioTotal = this.precio * this.cantidad;
    }

    // Getters y Setters
    public Long getIdDetalleVenta() { return idDetalleVenta; }
    public void setIdDetalleVenta(Long idDetalleVenta) { this.idDetalleVenta = idDetalleVenta; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public double getPrecioTotal() { return precioTotal; }
}

// Repositorio DetalleVenta
interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
    List<DetalleVenta> findByVentaId(Long idVenta);
}

// Servicio DetalleVenta
@Service
class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    public List<DetalleVenta> obtenerDetallesPorVenta(Long idVenta) {
        return detalleVentaRepository.findByVentaId(idVenta);
    }
}

// Controlador DetalleVenta
@RestController
@RequestMapping("/api/detalle-venta")
class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping("/{idVenta}")
    public List<DetalleVenta> obtenerDetalles(@PathVariable Long idVenta) {
        return detalleVentaService.obtenerDetallesPorVenta(idVenta);
    }
}
