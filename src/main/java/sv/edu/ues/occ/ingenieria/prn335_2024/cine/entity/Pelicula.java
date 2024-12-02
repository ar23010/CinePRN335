package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "pelicula", schema="public")
@NamedQueries({
@NamedQuery(name = "Pelicula.findAll", query = "SELECT pl FROM Pelicula pl ORDER BY pl.idPelicula ASC")
})
public class Pelicula {
    @Id
    @Column(name = "id_pelicula", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPelicula;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "idPelicula")
    private List<PeliculaCaracteristica> PeliculaCaracteristicaList;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "idPelicula")
    private List<Programacion> ProgramacionList;

    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;

    @Lob
    @Column(name = "sinopsis")
    private String sinopsis;


    public Pelicula(Long idPelicula) {
    this.idPelicula=idPelicula;
    }

    public Pelicula() {
    }

    public Long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Long idPelicula) {this.idPelicula = idPelicula;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public List<PeliculaCaracteristica> getPeliculaCaracteristicaList() {
        return PeliculaCaracteristicaList;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public List<PeliculaCaracteristica> getPeliculaCaracteristicas() {
        return PeliculaCaracteristicaList;
    }

    public void setPeliculaCaracteristica(List<PeliculaCaracteristica> peliculaCaracteristicas) {
        this.PeliculaCaracteristicaList = PeliculaCaracteristicaList;
    }
}