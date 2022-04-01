package org.zerock.partThree.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(exclude = "board")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String text;

    private String replier;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    public void changeText(String text){
        this.text=text;
    }
    public void changeReplier(String replier){
        this.replier=replier;
    }
}
