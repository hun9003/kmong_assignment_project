package com.parkjinhun.kmong.kmong_assignment_project.infrastructure.item.option;


import com.parkjinhun.kmong.kmong_assignment_project.domain.item.option.ItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionRepository extends JpaRepository<ItemOption, Long> {
}
