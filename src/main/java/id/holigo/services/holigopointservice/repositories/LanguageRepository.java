package id.holigo.services.holigopointservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.holigo.services.holigopointservice.domain.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Language findByMessageKeyAndLocale(String messageKey, String locale);
}
