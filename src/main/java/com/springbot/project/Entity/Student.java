package com.springbot.project.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long stu_id;
    @Column(name = "Name")
    private String stu_name;
    @Column(name = "Email")
    private String stu_email;
    @Column(name = "MobileNo")
    private long stu_mob_no;
}
