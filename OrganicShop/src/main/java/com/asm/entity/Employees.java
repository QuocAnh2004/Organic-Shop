//package com.asm.entity;
//
//import java.util.Date;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "Employees")
//public class Employees {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "employee_id")
//    private int employeeId;
//
//    @OneToOne
//    @JoinColumn(name = "users_id_employee", nullable = false)
//    private Users user;
//
//    @Column(name = "first_name_employee", nullable = false, length = 255)
//    private String firstName;
//
//    @Column(name = "last_name_employee", nullable = false, length = 255)
//    private String lastName;
//
//    @Column(name = "email_employee", nullable = false, length = 255)
//    private String email;
//
//    @Column(name = "phone_number_employee", nullable = false, length = 50)
//    private String phoneNumber;
//
//    @Column(name = "address_employee", nullable = false, length = 255)
//    private String address;
//
//    @Column(name = "picture_employee", length = 255)
//    private String picture;
//
//    @Column(name = "date_of_birth_employee", nullable = false)
//    private Date dateOfBirth;
//
//    @Column(name = "genders_employee", nullable = false)
//    private boolean gender;
//
//    @Column(name = "is_active__employee", nullable = false)
//    private boolean isActive;
//
//    // Getters and setters
//}
//
package com.asm.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employees")
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;

    @OneToOne
    @JoinColumn(name = "users_id_employee", nullable = false)
    private Users user;

    @Column(name = "first_name_employee", nullable = false, length = 255)
    @NotEmpty(message = "{NotEmpty.employees_form.firstName}")
    private String firstName;

    @Column(name = "last_name_employee", nullable = false, length = 255)
    @NotEmpty(message = "{NotEmpty.employees_form.lastName}")
    private String lastName;

    @Column(name = "email_employee", nullable = false, length = 255)
    @NotEmpty(message = "{NotEmpty.employees_form.email}")
    @Email(message = "{Email.employees_form.email}")
    private String email;

    @Column(name = "phone_number_employee", nullable = false, length = 50)
    @NotEmpty(message = "{NotEmpty.employees_form.phoneNumber}")
    @Size(max = 50, message = "{Size.employees_form.phoneNumber}")
    private String phoneNumber;

    @Column(name = "address_employee", nullable = false, length = 255)
    @NotEmpty(message = "{NotEmpty.employees_form.address}")
    @Size(max = 255, message = "{Size.employees_form.address}")
    private String address;

    @Column(name = "picture_employee", length = 255)
    private String picture;

    @Column(name = "date_of_birth_employee", nullable = false)
    @NotNull(message = "{NotNull.employees_form.dateOfBirth}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Column(name = "genders_employee", nullable = false)
    @NotNull(message = "{NotNull.employees_form.gender}")
    private boolean gender;

    @Column(name = "is_active__employee", nullable = false)
    private boolean isActive;

 // Getters and setters
    public boolean getisActive() {
        return isActive;
    }

    public void setisActive(boolean isActive) {
        this.isActive = isActive;
    }
    public boolean getisGender() {
        return gender;
    }

    public void setisGender(boolean gender) {
        this.gender = gender;
    }

    
}