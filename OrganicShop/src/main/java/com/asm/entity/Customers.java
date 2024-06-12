////package com.asm.entity;
////
////import java.util.Date;
////import java.util.List;
////
////import jakarta.persistence.*;
////import lombok.AllArgsConstructor;
////import lombok.Data;
////import lombok.NoArgsConstructor;
////@Data
////@AllArgsConstructor
////@NoArgsConstructor
////@Entity
////@Table(name = "Customers")
////public class Customers {
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    @Column(name = "customer_id")
////    private int customerId;
////
////    @OneToOne
////    @JoinColumn(name = "users_id_customer", nullable = false)
////    private Users user;
////
////    @Column(name = "first_name_customer", nullable = false, length = 255)
////    private String firstName;
////
////    @Column(name = "last_name_customer", nullable = false, length = 255)
////    private String lastName;
////
////    @Column(name = "email_customer", nullable = false, length = 255)
////    private String email;
////
////    @Column(name = "address_customer", nullable = false, length = 255)
////    private String address;
////
////    @Column(name = "phone_number_customer", nullable = false, length = 20)
////    private String phoneNumber;
////
////    @Column(name = "date_sign_up_customer", nullable = false)
////    private Date dateSignUp;
////
////    @Column(name = "date_of_birth_customer", nullable = false)
////    private Date dateOfBirth;
////
////    @Column(name = "genders_customer", nullable = false)
////    private boolean gender;
////
////    @Column(name = "is_active_customer", nullable = false)
////    private boolean isActive;
////
////    @OneToMany(mappedBy = "customer")
////    private List<Reviews> reviews;
////    // Getters and setters
////}
////
//package com.asm.entity;
//
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.format.annotation.DateTimeFormat;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "Customers")
//public class Customers {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "customer_id")
//    private int customerId;
//
//    @OneToOne
//    @JoinColumn(name = "users_id_customer", nullable = false)
//    private Users user;
//
//    @Column(name = "first_name_customer", nullable = false, length = 255)
//    private String firstName;
//
//    @Column(name = "last_name_customer", nullable = false, length = 255)
//    private String lastName;
//
//    @Column(name = "email_customer", nullable = false, length = 255)
//    private String email;
//
//    @Column(name = "address_customer", nullable = false, length = 255)
//    private String address;
//
//    @Column(name = "phone_number_customer", nullable = false, length = 20)
//    private String phoneNumber;
//
//    @Column(name = "date_sign_up_customer", nullable = false)
//    private Date dateSignUp;
//
//    @Column(name = "date_of_birth_customer", nullable = false)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date dateOfBirth;
//
//    @Column(name = "genders_customer", nullable = false)
//    private boolean gender;
//
//    @Column(name = "is_active_customer", nullable = false)
//    private boolean isActive;
//
//    @OneToMany(mappedBy = "customer")
//    private List<Reviews> reviews;
//    
// // Method to check if the customer is active
//    public boolean isIsActive() {
//        return isActive;
//    }
//    public void setIsActive(boolean isActive) {
//        this.isActive = isActive;
//    }
//}
//
//
package com.asm.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Customers")
public class Customers {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "customer_id")
	    private int customerId;
//dmmmm
	    @OneToOne
	    @JoinColumn(name = "users_id_customer", nullable = false)
	    private Users user;

	    @NotBlank(message = "{NotEmpty.customer.firstName}")
	    @Column(name = "first_name_customer", nullable = false, length = 255)
	    private String firstName;

	    @NotBlank(message = "{NotEmpty.customer.lastName}")
	    @Column(name = "last_name_customer", nullable = false, length = 255)
	    private String lastName;

	    @NotBlank(message = "{NotEmpty.customer.email}")
	    @Email(message = "{Email.cus.email}")
	    @Column(name = "email_customer", nullable = false, length = 255)
	    private String email;

	    @NotBlank(message = "{NotEmpty.customer.address}")
	    @Column(name = "address_customer", nullable = false, length = 255)
	    private String address;

	    @NotBlank(message = "{NotEmpty.customer.phoneNumber}")
	    @Pattern(regexp = "\\d{10,11}", message = "{Pattern.cus.phoneNumber}")
	    @Column(name = "phone_number_customer", nullable = false, length = 20)
	    private String phoneNumber;

	    @NotNull(message = "{NotNull.customer.dateOfBirth}")
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    @Column(name = "date_of_birth_customer", nullable = false)
	    private Date dateOfBirth;
	    
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    @Column(name = "date_sign_up_customer", nullable = false)
	    private Date dateSignUp;


	    @NotNull(message = "{NotNull.customer.gender}")
	    @Column(name = "genders_customer", nullable = false)
	    private boolean gender;

	    @Column(name = "is_active_customer", nullable = false)
	    private boolean isActive;

	    @OneToMany(mappedBy = "customer")
	    private List<Reviews> reviews;

	    // Getters and setters
	 // // Method to check if the customer is active
	    public boolean isIsActive() {
	        return isActive;
	    }
	    public void setIsActive(boolean isActive) {
	        this.isActive = isActive;
	    }
}
