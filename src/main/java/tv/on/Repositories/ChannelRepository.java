package tv.on.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tv.on.models.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {


}
