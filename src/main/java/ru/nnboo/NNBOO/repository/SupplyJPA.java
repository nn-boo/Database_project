package ru.nnboo.NNBOO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nnboo.NNBOO.entity.Supply;

@Repository
public interface SupplyJPA extends JpaRepository<Supply, Long> {
}
