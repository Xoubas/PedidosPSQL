package model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer idCliente;

    @Column(length = 12, nullable = false)
    private String dni;

    @Column(length = 128, nullable = false)
    private String nombre;

    // Relations
    @OneToMany(mappedBy = "cliente")
    private Collection<Pedido> pedidos;
}
