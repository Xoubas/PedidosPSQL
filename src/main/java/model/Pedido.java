package model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "Pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_generator")
    @SequenceGenerator(name = "pedido_generator", sequenceName = "Pedido_idPedido_seq", allocationSize = 1)
    @Column
    private Integer idPedido;

    @Column(nullable = false)
    private LocalDateTime fecha;

    // Relations
    @OneToMany(mappedBy = "pedido")
    private Collection<Comentario> comentarios;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @OneToMany
    private Collection<LineaPedido> lineasPedido;

    // Getters and Setters
}

