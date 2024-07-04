package test.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.task.models.entity.CouponEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CouponEntityRepository extends JpaRepository<CouponEntity, UUID> {
    Optional<CouponEntity> findByCode(String code);
}