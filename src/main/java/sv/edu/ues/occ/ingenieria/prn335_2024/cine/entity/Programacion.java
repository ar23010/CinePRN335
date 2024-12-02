package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.inject.Named;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "programacion", schema = "public")
@NamedQueries({
        @NamedQuery(name = "Programacion.findAll", query = "SELECT pr FROM Programacion pr ORDER BY pr.idProgramacion ASC"),
        @NamedQuery(name = "Programacion.findByDate", query ="SELECT pr FROM Programacion pr WHERE pr.desde >=:desde AND pr.hasta <:hasta")
})
public class Programacion {
    @Id
    @Column(name = "id_programacion", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProgramacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private Sala idSala;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pelicula")
    private Pelicula idPelicula;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "idProgramacion")
    private List<Reserva> ReservaList;

    @Column(name = "desde")
    private OffsetDateTime desde;

    @Column(name = "hasta")
    private OffsetDateTime hasta;

    @Lob
    @Column(name = "comentarios")
    private String comentarios;

    public Programacion(Long idProgramacion, Sala idSala, Pelicula idPelicula, List<Reserva> reservaList, OffsetDateTime desde, OffsetDateTime hasta, String comentarios) {
        this.idProgramacion = idProgramacion;
        this.idSala = idSala;
        this.idPelicula = idPelicula;
        ReservaList = reservaList;
        this.desde = desde;
        this.hasta = hasta;
        this.comentarios = comentarios;
    }

    public Programacion() {
    }

    public Long getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Long idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
    }

    public Pelicula getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Pelicula idPelicula) {
        this.idPelicula = idPelicula;
    }

    public OffsetDateTime getDesde() {
        return desde;
    }

    public void setDesde(OffsetDateTime desde) {
        this.desde = desde;
    }

    public OffsetDateTime getHasta() {
        return hasta;
    }

    public void setHasta(OffsetDateTime hasta) {
        this.hasta = hasta;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

}