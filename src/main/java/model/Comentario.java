package model;

import jakarta.persistence.*;

@Entity
@Table(name = "Comentario")
public class Comentario {
    @Id
    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    @Column
    private String comentario;
}

