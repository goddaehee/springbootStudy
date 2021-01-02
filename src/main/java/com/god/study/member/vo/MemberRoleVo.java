package com.god.study.member.vo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="mbRoleBase")
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)*/
public class MemberRoleVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleNo;

    @Column(length = 20, nullable = false)
    private String roleNm;

    @CreationTimestamp
    private Date regTime;

    @Column(nullable = true)
    private Long regMbrNo;

    @UpdateTimestamp
    private Date modTime;

    @Column(nullable = true)
    private Long modMbrNo;

    @OneToMany(mappedBy = "role")
    private List<MemberRoleMppgVo> MemberRoleMppgVos;

    /* N:M 풀때 */
    /*@ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="mbMbrRole",
            joinColumns = {@JoinColumn(name = "roleNo", referencedColumnName = "roleNo")},
            inverseJoinColumns = {@JoinColumn(name = "mbrNo", referencedColumnName = "mbrNo")}
    )
    private List<MemberVo> members;*/



    /*private List<MemberRoleVo> roles;*/
}


