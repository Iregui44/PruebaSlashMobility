package com.slashmobility.UserProoviders.domain.service;

import com.slashmobility.UserProoviders.domain.model.User;

public interface IEmailService {

    public boolean sendConfirmationEmail(User user);
}
