package com.yonatankarp.petclinic.services.map;

import java.util.Set;
import com.yonatankarp.petclinic.exceptions.InvalidVisitException;
import com.yonatankarp.petclinic.model.Visit;
import com.yonatankarp.petclinic.services.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile({"default", "map"})
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public Visit findById(final Long id) {
        return super.findById(id);
    }

    @Override
    public Visit save(final Visit visit) {
        if (visit.getPet() == null ||
                visit.getPet().getId() == null ||
                visit.getPet().getOwner() == null ||
                visit.getPet().getOwner().getId() == null) {
            throw new InvalidVisitException();
        }

        return super.save(visit);
    }

    @Override
    public void delete(final Visit visit) {
        super.delete(visit);
    }

    @Override
    public void deleteById(final Long id) {
        super.deleteById(id);
    }
}
