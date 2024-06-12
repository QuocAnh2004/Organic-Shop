//package com.asm.entity;
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
//@Table(name = "Users")
//public class Users {
//    @Id
//    @Column(name = "users_id", length = 255)
//    private String userId;
//
//    @Column(name = "username", nullable = false, length = 255)
//    private String username;
//
//    @Column(name = "password_user", nullable = false, length = 255)
//    private String passwordUser;
//
//    @ManyToOne
//    @JoinColumn(name = "role_name_user", nullable = false)
//    private UserRoles userRole;
//
//    // Getters and setters
//}
package com.asm.entity;
import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class Users {
    @Id
    @Column(name = "users_id", length = 255)
    private String userId;
    
    @NotEmpty(message = "NotEmpty.user.username")
    @Column(name = "username", nullable = false, length = 255)
    private String username;
    
	@NotEmpty(message = "NotEmpty.user.passwordUser")
    @Column(name = "password_user", nullable = false, length = 255)
//    private String password;
	private String passwordUser;

    @ManyToOne
    @JoinColumn(name = "role_name_user", nullable = false)
    private UserRoles userRole;
    
//    @NotEmpty(message = "NotEmpty.user.oldPassword")
//    private String oldPassword;
//    
//    @NotEmpty(message = "NotEmpty.user.newPassword")
//    private String newPassword;
    
//    @Transient
//    @NotEmpty(message = "NotEmpty.user.confirmPassword")
//    private String confirmPassword;
}

