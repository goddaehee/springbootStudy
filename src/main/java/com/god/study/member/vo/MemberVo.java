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
@Entity(name="mbMbrBase")
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)*/
public class MemberVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mbrNo;

    @Column(length = 100, unique = true, nullable = false)
    private String mbrId;

    @Column(length = 30, nullable = true)
    private String mbrNm;

    @Column(length = 300, nullable = true)
    private String mbrPwd;

    @CreationTimestamp
    private Date regTime;

    @Column(nullable = true)
    private Long regMbrNo;

    @UpdateTimestamp
    private Date modTime;

    @Column(nullable = true)
    private Long modMbrNo;

    /*@JoinColumn(name="roleNo")*/
    @OneToMany(cascade=CascadeType.ALL,  fetch = FetchType.EAGER, mappedBy = "member")
    private List<MemberRoleMppgVo> MemberRoleMppgVos;

    /* N:M 풀때 */
    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name="mbMbrRole",
        joinColumns = @JoinColumn(name="mbrNo"),
        inverseJoinColumns = @JoinColumn(name="roleNo")
    )
    private List<MemberRoleVo> roles;*/

    /*@UpdateTimestamp
    private Date updatedate;*/

    /*@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="mbrNo")
    private List<MemberRole> roles;*/
    /*
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER) 설정은,
    cascade의 경우에는 엔티티들의 영속관계를 한번에 처리하지 못하기 때문에 이에 대한 cascade 설정을 추가.
    member와 member_role을 둘다 동시에 조회하기 위해서 fetch 설정을 즉시 로딩으로 EAGER 설정을 주어야 에러가 발생하지 않느다.
     */


    /*private String firstName;

    private String lastName;

    public String getFullName(){
        return String.format("%s %s", this.lastName, this.firstName);
    }*/

    @Builder
    public MemberVo(String mbrId, String mbrNm, String mbrPwd) {
        this.mbrId = mbrId;
        this.mbrNm = mbrNm;
        this.mbrPwd = mbrPwd;
    }

    public void setRoles(List<MemberRoleVo> asList) {
    }
}
