package br.com.cassol.prova.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;


@NoArgsConstructor
@Data
@Entity
@Table(name = "prova")
public class Prova {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer ID;

    @NonNull
    @Column(name = "nome")
    private String NOME;


    @NonNull
    @Column(name = "nota")
    private  Integer NOTA;

}
