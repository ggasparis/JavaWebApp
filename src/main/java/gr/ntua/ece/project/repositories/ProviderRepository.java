package gr.ntua.ece.project.repositories;

import gr.ntua.ece.project.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository  extends JpaRepository<Provider, Long> {
}
