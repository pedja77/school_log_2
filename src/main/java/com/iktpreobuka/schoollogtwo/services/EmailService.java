package com.iktpreobuka.schoollogtwo.services;

import com.iktpreobuka.schoollogtwo.model.EmailObject;

public interface EmailService {

	void sendTemplateMessage (EmailObject object) throws Exception;
}
