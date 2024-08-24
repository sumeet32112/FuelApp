package com.noxfilers.fuelApp.repositories;

import com.noxfilers.fuelApp.entities.NotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTemplateRepositry extends JpaRepository<NotificationTemplate, Long> {
}