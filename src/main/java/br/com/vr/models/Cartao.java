package br.com.vr.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity(name = "cartao")
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "numero_cartao")
    private String numeroCartao;
    @Column(name = "saldo_cartao")
    private Double saldo;
    @Column(name = "senha")
    private String senha;

    @PrePersist
    private void prePersistFunction(){
        this.saldo = 500.0;
    }

}
