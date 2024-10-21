package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipo_sala", schema = "public")
public class TipoSala implements Serializable {
    @Id
    @Column(name = "id_tipo_sala", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoSala;


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "idTipoSala")
    private List<SalaCaracteristica> SalaCaracteristicaList;


    @NotBlank(message = "Debe de ingresar un nombre válido")
    @Size(max = 155, min = 3, message="Debe de agregar un campo válido")
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    @Lob
    @Column(name = "comentarios")
    private String comentarios;

    @Lob
    @Column(name = "expresion_regular")
    private String expresionRegular;

    public TipoSala(Integer idTipoSala) {
    this.idTipoSala = idTipoSala;
    }

    public TipoSala() {

    }

    public Integer getIdTipoSala() {
        return idTipoSala;
    }

    public void setIdTipoSala(Integer idTipoSala) {
        this.idTipoSala = idTipoSala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getExpresionRegular() {
        return expresionRegular;
    }

    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }

}