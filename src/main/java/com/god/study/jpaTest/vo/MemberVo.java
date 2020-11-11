package com.god.study.jpaTest.vo;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="member")
public class MemberVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mbrNo;

    private String id;

    private String name;

    /*private String firstName;

    private String lastName;

    public String getFullName(){
        return String.format("%s %s", this.lastName, this.firstName);
    }*/

    @Builder
    public MemberVo(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
