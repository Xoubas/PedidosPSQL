package model;

import jakarta.persistence.*;

@Entity
@Table(name = "TagLineaPedido")
public class TagLineaPedido {
    @Id
    @ManyToOne
    @JoinColumn(name = "idLineaPedido")
    private LineaPedido lineaPedido;

    @Column(length = 32)
    private String tag;

    // Getters and Setters
}

