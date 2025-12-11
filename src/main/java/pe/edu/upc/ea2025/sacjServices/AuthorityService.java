package pe.edu.upc.ea2025.sacjServices;

import pe.edu.upc.ea2025.sacjEntities.Authority;

public interface AuthorityService {

    public Authority addAuthority(Authority authority);

    public Authority findByName(String authorityName);

}
