package com.god.study.member.vo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
/*@NoArgsConstructor*/
@Entity
@Table(name="mbMbrRoleMppg")
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)*/
public class MemberRoleMppgVo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mppgSeq;

    /*@Column(nullable = false)
    private Long mbrNo;

    @Column(nullable = false)
    private Long roleNo;*/

    /*@Column(nullable = false)
    private Long roleNo;*/

    @CreationTimestamp
    private Date regTime;

    @Column(nullable = true)
    private Long regMbrNo;

    @UpdateTimestamp
    private Date modTime;

    @Column(nullable = true)
    private Long modMbrNo;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="mbrNo", referencedColumnName = "mbrNo")
    private MemberVo member;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="roleNo", referencedColumnName = "roleNo")
    private MemberRoleVo role;

    public MemberRoleMppgVo(){};

    public MemberRoleMppgVo(MemberVo member, long roleNo){
        this.member = member;
        role = new MemberRoleVo();
        this.role.setRoleNo(roleNo);
    }
}
