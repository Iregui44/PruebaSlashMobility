package com.slashmobility.UserProoviders.domain.valueobject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResetPassword {

    private String oldPassword;

    private String newPassword;
}
