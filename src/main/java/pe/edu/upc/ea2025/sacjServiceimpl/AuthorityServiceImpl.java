package pe.edu.upc.ea2025.sacjServiceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ea2025.sacjEntities.Authority;
import pe.edu.upc.ea2025.sacjExceptions.IncompleteDataException;
import pe.edu.upc.ea2025.sacjRepositories.AuthorityRepository;
import pe.edu.upc.ea2025.sacjServices.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority addAuthority(Authority authority) {
        if (authority.getName()==null||authority.getName().isBlank()) {
         throw new IncompleteDataException("Authority name can not be empty");
        }
        return authorityRepository.save(authority);
    }

    @Override
    public Authority findByName(String authorityName) {
        return authorityRepository.findByName(authorityName);
    }
}
