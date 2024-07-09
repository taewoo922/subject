package com.cod.market.member.form;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
public class MemberForm {
    @Size(min = 3, max = 25)
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String nickname;

    @NotBlank
    @Email
    private String email;
}
