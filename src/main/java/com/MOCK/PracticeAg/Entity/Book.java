package com.MOCK.PracticeAg.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="book_tab")
public class Book {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name="BookId")

    //@NotBlank
    private Long Id;

   @NotBlank
    @Size(min = 1,max = 5 ,message = "shouldnt exceed 5 letters")
    @Column(name="BookName")
    private String bookName;


}
