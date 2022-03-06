package com.yonatankarp.petclinic.services.map;

import java.util.Set;
import com.yonatankarp.petclinic.model.PetType;
import com.yonatankarp.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class PetTypeMapService extends AbstractMapService<PetType, Long>  implements PetTypeService{
    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(final Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(final PetType petType) {
        super.delete(petType);
    }

    @Override
    public PetType save(final PetType petType) {
        return super.save(petType);
    }

    @Override
    public PetType findById(final Long id) {
        return super.findById(id);
    }
}
