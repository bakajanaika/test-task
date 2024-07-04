package test.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.task.models.entity.ProductEntity;

import java.util.UUID;

@Repository
public interface ProductEntityRepository extends JpaRepository<ProductEntity, UUID> {
}