package com.nhnacademy.minidooray.task.backend.entity;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Member")
public class ProjectMember {

    @EmbeddedId
    @NotNull
    private Pk pk;


    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Project project;


    @Getter
    @Setter
    @Embeddable
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pk implements Serializable {

        @NotNull
        @Column(name = "account_id")
        private String accountId;

        @NotNull
        @Column(name = "project_id")
        private Long projectId;
    }
}
