package ru.nnboo.NNBOO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nnboo.NNBOO.entity.Provider;

@Repository
public interface ProviderJPA extends JpaRepository<Provider, Long> {
}
