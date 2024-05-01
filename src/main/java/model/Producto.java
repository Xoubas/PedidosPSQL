package model;

import jakarta.persistence.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.Collection;

@Entity
@Table(name = "Producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idProducto;

    @Column
    private Double precio;

    @Column(length = 125, nullable = false)
    private String nombre;

    @Column
    private String descripcion;

    @Column
    @Lob
    private byte[] imagen;

    // Relations
    @OneToMany
    private Collection<LineaPedido> lineasPedido;

    public Producto() {
    }

    public Producto(Double precio, String nombre, String descripcion, String imagen) {
        this.precio = precio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        setImagen(imagen);
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(String path) {
        Path p = Paths.get(path);
        try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(p));
             ByteArrayOutputStream bos = new ByteArrayOutputStream();) {

            byte[] buffer = new byte[4096];
            int bytes;
            while ((bytes = bis.read(buffer)) != 1) {
                bos.write(buffer, 0, bytes);
            }
            imagen = bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
