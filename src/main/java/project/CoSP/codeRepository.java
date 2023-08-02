package project.CoSP;


import org.springframework.data.jpa.repository.JpaRepository;
import project.CoSP.Model.Code;

import java.util.List;
import java.util.UUID;

public interface codeRepository extends JpaRepository<Code, UUID> {
    /***
     *Spring Data JPA
     ***/
    List<Code> findTop10ByRestrictionTypeOrderByTimeDesc(int restrictionType);

    Code findTop1ByOrderByTime();
}

