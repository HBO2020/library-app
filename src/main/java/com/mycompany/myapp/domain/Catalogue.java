package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Catalogue.
 */
@Entity
@Table(name = "catalogue")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Catalogue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name_of_author")
    private String nameOfAuthor;

    @Column(name = "nom_of_copies")
    private Integer nomOfCopies;

    @ManyToMany
    @JoinTable(
        name = "rel_catalogue__book",
        joinColumns = @JoinColumn(name = "catalogue_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "staff", "images", "members", "catalogues" }, allowSetters = true)
    private Set<Book> books = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Catalogue id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfAuthor() {
        return this.nameOfAuthor;
    }

    public Catalogue nameOfAuthor(String nameOfAuthor) {
        this.setNameOfAuthor(nameOfAuthor);
        return this;
    }

    public void setNameOfAuthor(String nameOfAuthor) {
        this.nameOfAuthor = nameOfAuthor;
    }

    public Integer getNomOfCopies() {
        return this.nomOfCopies;
    }

    public Catalogue nomOfCopies(Integer nomOfCopies) {
        this.setNomOfCopies(nomOfCopies);
        return this;
    }

    public void setNomOfCopies(Integer nomOfCopies) {
        this.nomOfCopies = nomOfCopies;
    }

    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Catalogue books(Set<Book> books) {
        this.setBooks(books);
        return this;
    }

    public Catalogue addBook(Book book) {
        this.books.add(book);
        book.getCatalogues().add(this);
        return this;
    }

    public Catalogue removeBook(Book book) {
        this.books.remove(book);
        book.getCatalogues().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Catalogue)) {
            return false;
        }
        return id != null && id.equals(((Catalogue) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Catalogue{" +
            "id=" + getId() +
            ", nameOfAuthor='" + getNameOfAuthor() + "'" +
            ", nomOfCopies=" + getNomOfCopies() +
            "}";
    }
}
