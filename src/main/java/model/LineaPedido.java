package model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "LineaPedido")
public class LineaPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lineapedido_generator")
    @SequenceGenerator(name = "lineapedido_generator", sequenceName = "LineaPedido_idLineaPedido_seq", allocationSize = 1)
    @Column(name = "idLineaPedido")
    private Integer idLineaPedido;

    @Column(nullable = false)
    private Short cantidad;

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    @OneToMany(mappedBy = "lineaPedido")
    private Collection<TagLineaPedido> tags;

    // Getters and Setters
}

