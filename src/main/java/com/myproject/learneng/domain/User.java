package com.myproject.learneng.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    @Column(name = "USER_ID")
    private Long id;

    @NotNull(message = "Name is required")
    @Column(name = "NAME",unique = true)
    private String name;

    @NotNull(message = "password is required")
    @Column(name = "PASSWORD")
    private String password;

    private String createdDate = LocalDateTime.now((ZoneId.of("Europe/Moscow"))).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    private String lastModified;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_ROLES", joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "USERS_PHRASE", joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PHRASE_ID"))
    private Set<Phrase> setPhrases;

    @ElementCollection(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @CollectionTable(name = "USER_RESULT", joinColumns = @JoinColumn(name = "USER_ID"))
    private List<Result> setResults;

    public User() {}

    public Set<Phrase> getSetPhrases() {
        return setPhrases;
    }

    public void setSetPhrases(Set<Phrase> setPhrases) {
        this.setPhrases = setPhrases;
    }

    public List<Result> getSetResults() {
        return setResults;
    }

    public void setSetResults(List<Result> setResults) {
        this.setResults = setResults;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public Long getId() {
        return id;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}