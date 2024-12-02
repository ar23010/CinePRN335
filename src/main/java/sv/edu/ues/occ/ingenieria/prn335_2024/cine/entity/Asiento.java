package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "asiento", schema = "public")
@NamedQueries({
        @NamedQuery(name = "Asiento.findByIdSala", query = "SELECT asiento FROM Asiento asiento WHERE asiento.idSala.idSala=:idSala ORDER BY asiento.idAsiento ASC"),
        @NamedQuery(name = "Asiento.findDisponiblesByIdProgramacion", query = "SELECT a FROM Asiento a WHERE a.idSala.idSala IN (SELECT s.idSala FROM Programacion pr JOIN pr.idSala s WHERE pr.idProgramacion =:idProgramacion) AND a.idAsiento NOT IN (SELECT rd.idAsiento.idAsiento FROM ReservaDetalle rd WHERE rd.idReserva.idProgramacion.idProgramacion= :idProgramacion) ORDER BY a.idAsiento ASC" )
})

public class Asiento {
    @Id
    @Column(name = "id_asiento", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private Sala idSala;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "idAsiento")
    private List<AsientoCaracteristica> AsientoCaracteristicaList;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "idAsiento")
    private List<ReservaDetalle> ReservaDetalleList;

    public Asiento(Long idAsiento) {
     this.idAsiento=idAsiento;
    }

    public Asiento() {
    }

    @Size(max = 155)
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    public Long getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Long idAsiento) {
        this.idAsiento = idAsiento;
    }

    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Asiento asiento = (Asiento) obj;
        return idAsiento != null && idAsiento.equals(asiento.idAsiento);
    }

    @Override
    public int hashCode() {
        return idAsiento != null ? idAsiento.hashCode() : 0;
    }


}